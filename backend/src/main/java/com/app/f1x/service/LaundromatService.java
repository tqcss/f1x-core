package com.app.f1x.service;

import com.app.f1x.model.AppUser;
import com.app.f1x.model.Laundromat;
import com.app.f1x.payload.request.CreateLaundromatRequest;
import com.app.f1x.repository.AppUserRepository;
import com.app.f1x.repository.LaundromatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaundromatService {

    private static final Logger logger = LoggerFactory.getLogger(LaundromatService.class);

    private final AppUserRepository appUserRepository;
    private final LaundromatRepository laundromatRepository;

    @Autowired
    public LaundromatService(AppUserRepository appUserRepository, LaundromatRepository laundromatRepository) {
        this.appUserRepository = appUserRepository;
        this.laundromatRepository = laundromatRepository;
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

}
