package com.app.f1x.service;

import com.app.f1x.model.AppUser;
import com.app.f1x.model.Laundromat;
import com.app.f1x.payload.request.CreateLaundromatRequest;
import com.app.f1x.payload.request.JoinLaundromatRequest;
import com.app.f1x.payload.response.LaundromatDetailsResponse;
import com.app.f1x.repository.AppUserRepository;
import com.app.f1x.repository.LaundromatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LaundromatService {

    private static final Logger logger = LoggerFactory.getLogger(LaundromatService.class);

    private final AppUserRepository appUserRepository;
    private final LaundromatRepository laundromatRepository;

    private static final Integer INVITE_DURATION = 1440; // in minutes

    @Autowired
    public LaundromatService(AppUserRepository appUserRepository, LaundromatRepository laundromatRepository) {
        this.appUserRepository = appUserRepository;
        this.laundromatRepository = laundromatRepository;
    }

    private Optional<Laundromat> findLaundromat(String inviteCode) {
        return laundromatRepository.findLaundromatByInviteCodeAndInviteExpiryIsAfter(inviteCode, LocalDateTime.now());
    }

    private String generateInviteCode() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public Boolean createLaundromat(String userIdentity, CreateLaundromatRequest request) {
        Optional<AppUser> optionalAppUser = appUserRepository.findAppUserByEmail(userIdentity);

        if (optionalAppUser.isEmpty()) {
            logger.error("Requester not found with identity: {}", userIdentity);
            return false;
        }

        try {
            Laundromat laundromat = new Laundromat();
            AppUser appUser = optionalAppUser.get();

            laundromat.setName(request.getLaundromatName());
            laundromat.setCreator(appUser);
            laundromat.setUsers(List.of(appUser));
            appUser.setLaundromat(laundromat);

            laundromatRepository.save(laundromat);
            appUserRepository.save(appUser);

            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public LaundromatDetailsResponse getLaundromatDetails(String identity) {
        Optional<AppUser> optionalAppUser = appUserRepository.findAppUserByEmail(identity);
        if (optionalAppUser.isEmpty()) { return LaundromatDetailsResponse.builder().build(); }

        Laundromat laundromat = optionalAppUser.get().getLaundromat();
        if (laundromat == null) { return LaundromatDetailsResponse.builder()
                .inLaundromat(false)
                .isLaundromatCreator(false)
                .build();
        }

        logger.info("{} {}", laundromat.getCreator().getId(), optionalAppUser.get().getId());

        return LaundromatDetailsResponse.builder()
                .inLaundromat(true)
                .isLaundromatCreator(laundromat.getCreator().getId().equals(optionalAppUser.get().getId()))
                .laundromatName(laundromat.getName())
                .laundromatInviteCode(getValidInviteCode(laundromat))
                .build();
    }

    public void generateLaundromatInvite(String identity) {
        Optional<AppUser> optionalAppUser = appUserRepository.findAppUserByEmail(identity);
        if (optionalAppUser.isEmpty()) { return; }

        Laundromat laundromat = optionalAppUser.get().getLaundromat();
        LocalDateTime inviteExpiry = LocalDateTime.now().plusMinutes(INVITE_DURATION);
        String inviteCode = generateInviteCode();

        while (isInviteAvailable(inviteCode)) {
            inviteCode = generateInviteCode();
        }

        laundromat.setInviteCode(inviteCode);
        laundromat.setInviteExpiry(inviteExpiry);
        laundromatRepository.save(laundromat);
    }

    private Boolean isInviteAvailable(String inviteCode) { return findLaundromat(inviteCode).isPresent(); }

    private String getValidInviteCode(Laundromat laundromat) {
        String inviteCode = laundromat.getInviteCode();
        LocalDateTime inviteExpiry = laundromat.getInviteExpiry();

        return inviteExpiry.isAfter(LocalDateTime.now()) ? inviteCode : "code-expired";
    }

    public Boolean joinLaundromat(String identity, JoinLaundromatRequest request) {
        Optional<AppUser> optionalAppUser = appUserRepository.findAppUserByEmail(identity);
        if (optionalAppUser.isEmpty()) { return false; }

        Optional<Laundromat> optionalLaundromat = findLaundromat(request.getInviteCode().toUpperCase().strip());
        if (optionalLaundromat.isEmpty()) {
            logger.error("laundromat not found with code: {}", request.getInviteCode().toUpperCase().strip());
            return false;
        }

        AppUser appUser = optionalAppUser.get();
        Laundromat laundromat = optionalLaundromat.get();

        appUser.setLaundromat(laundromat);
        appUserRepository.save(appUser);

        logger.info(laundromat.getUsers().stream().map(AppUser::getEmail).toString());

        return true;
    }

}
