package com.app.f1x.controller;

import com.app.f1x.model.AppUser;
import com.app.f1x.model.Laundromat;
import com.app.f1x.model.Product;
import com.app.f1x.model.ServiceProduct;
import com.app.f1x.repository.AppUserRepository;
import com.app.f1x.repository.ServiceProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/app/service")
public class ServiceProductController {

    private final ServiceProductRepository serviceProductRepository;
    private final AppUserRepository userRepository;

    @Autowired
    public ServiceProductController(ServiceProductRepository serviceProductRepository, AppUserRepository userRepository) {
        this.serviceProductRepository = serviceProductRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/add")
    public String addProduct(Authentication authentication, @RequestParam String serviceName, @RequestParam Float servicePrice) {
        AppUser appUser = userRepository.findAppUserByEmail(authentication.getName()).get();
        Laundromat laundromat = appUser.getLaundromat();
        ServiceProduct serviceProduct = ServiceProduct.builder()
                .name(serviceName)
                .price(servicePrice)
                .build();

        serviceProduct.setLaundromat(laundromat);
        serviceProductRepository.save(serviceProduct);

        return "redirect:/app/home";
    }

    @PostMapping("/remove")
    public String removeProduct(@RequestParam Integer serviceProductId) {
        serviceProductRepository.deleteById(serviceProductId);

        return "redirect:/app/home";
    }


}
