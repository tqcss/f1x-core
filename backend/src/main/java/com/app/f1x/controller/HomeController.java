package com.app.f1x.controller;

import com.app.f1x.model.AppUser;
import com.app.f1x.model.Laundromat;
import com.app.f1x.model.Order;
import com.app.f1x.model.ServiceProduct;
import com.app.f1x.payload.request.CreateLaundromatRequest;
import com.app.f1x.payload.request.JoinLaundromatRequest;
import com.app.f1x.payload.response.LaundromatDetailsResponse;
import com.app.f1x.payload.response.UserDetailsResponse;
import com.app.f1x.repository.AppUserRepository;
import com.app.f1x.repository.OrderRepository;
import com.app.f1x.service.AppUserService;
import com.app.f1x.service.LaundromatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/app")
public class HomeController {

    private final AppUserService appUserService;
    private final LaundromatService laundromatService;
    private final OrderRepository orderRepository;
    private final AppUserRepository appUserRepository;

    @Autowired
    public HomeController(AppUserService appUserService, LaundromatService laundromatService, OrderRepository orderRepository, AppUserRepository appUserRepository) {
        this.appUserService = appUserService;
        this.laundromatService = laundromatService;
        this.orderRepository = orderRepository;
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/home")
    public String home(Authentication authentication, Model model, @RequestParam(required = false) String clearCurrentOrder) {
        UserDetailsResponse userDetails = appUserService.getUserDetails(authentication.getName());
        LaundromatDetailsResponse laundromatDetails = laundromatService.getLaundromatDetails(authentication.getName());

        Laundromat laundromat = appUserService.findAppUserByEmail(authentication.getName()).get().getLaundromat();
        List<ServiceProduct> servicesOffered = laundromat.getServices();
        Order currentOrder = appUserService.findAppUserByEmail(authentication.getName()).get().getCurrentOrder();

        if (clearCurrentOrder != null) {
            AppUser user = appUserService.findAppUserByEmail(authentication.getName()).get();
            Order currentOrderToDelete = user.getCurrentOrder();

            if (currentOrderToDelete != null) {
                user.setCurrentOrder(null);
                appUserRepository.save(user);

                orderRepository.delete(currentOrderToDelete);
            }
        }
        currentOrder = appUserService.findAppUserByEmail(authentication.getName()).get().getCurrentOrder();

        servicesOffered.sort(Comparator.comparing(ServiceProduct::getId));

        LocalDateTime dateTime = LocalDateTime.now();
        String dateString = dateTime.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));

        model.addAttribute(new CreateLaundromatRequest());
        model.addAttribute(new JoinLaundromatRequest());

        model.addAttribute("userDetails", userDetails);
        model.addAttribute("laundromatDetails", laundromatDetails);
        model.addAttribute("dateTime", dateTime);
        model.addAttribute("dateString", dateString);

        model.addAttribute("servicesOffered", servicesOffered);
        model.addAttribute("currentOrder", currentOrder);

        return "home";
    }

}
