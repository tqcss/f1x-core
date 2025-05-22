package com.app.f1x.controller;

import com.app.f1x.model.Laundromat;
import com.app.f1x.model.Product;
import com.app.f1x.payload.response.LaundromatDetailsResponse;
import com.app.f1x.payload.response.UserDetailsResponse;
import com.app.f1x.repository.LaundromatRepository;
import com.app.f1x.repository.ProductRepository;
import com.app.f1x.service.AppUserService;
import com.app.f1x.service.LaundromatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/app")
public class InventoryController {

    private final AppUserService appUserService;
    private final LaundromatService laundromatService;
    private final LaundromatRepository laundromatRepository;
    private final ProductRepository productRepository;

    @Autowired
    public InventoryController(AppUserService appUserService, LaundromatService laundromatService, LaundromatRepository laundromatRepository, ProductRepository productRepository) {
        this.appUserService = appUserService;
        this.laundromatService = laundromatService;
        this.laundromatRepository = laundromatRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/inventory")
    public String inventory(Authentication authentication, Model model) {
        UserDetailsResponse userDetails = appUserService.getUserDetails(authentication.getName());
        LaundromatDetailsResponse laundromatDetails = laundromatService.getLaundromatDetails(authentication.getName());
        List<Product> products = appUserService.findAppUserByEmail(authentication.getName()).get().getLaundromat().getProducts();

        products.sort(Comparator.comparing(Product::getId));

        model.addAttribute("userDetails", userDetails);
        model.addAttribute("laundromatDetails", laundromatDetails);
        model.addAttribute("products", products);

        return "inventory";
    }

    @PostMapping("/inventory/add")
    public String addProduct(Authentication authentication, @RequestParam String productName, @RequestParam Integer quantity) {
        if (productName == null || productName.isEmpty() || quantity == null || quantity < 0 || productRepository.existsByName(productName)) { return "redirect:/app/inventory"; }

        Laundromat laundromat = appUserService.findAppUserByEmail(authentication.getName()).get().getLaundromat();
        Product product = Product.builder()
                .name(productName)
                .quantity(quantity)
                .build();

        laundromat.getProducts().add(product);
        product.setLaundromat(laundromat);
        laundromatRepository.save(laundromat);
        productRepository.save(product);
        return "redirect:/app/inventory";
    }

    @PostMapping("/inventory/increment")
    public String incrementProduct(Authentication authentication, @RequestParam Integer productId) {
        Product product = productRepository.findById(productId).get();
        product.setQuantity(product.getQuantity() + 1);
        productRepository.save(product);

        return "redirect:/app/inventory";
    }

    @PostMapping("/inventory/decrement")
    public String decrementProduct(Authentication authentication, @RequestParam Integer productId) {
        Product product = productRepository.findById(productId).get();
        product.setQuantity(product.getQuantity() - 1);
        productRepository.save(product);

        return "redirect:/app/inventory";
    }

    @PostMapping("/inventory/editQuantity")
    public String editProductQuantity(@RequestParam Integer quantity, @RequestParam Integer productId) {
        if (quantity == null || quantity < 0) { return "redirect:/app/inventory"; }

        Product product = productRepository.findById(productId).get();
        product.setQuantity(quantity);
        productRepository.save(product);

        return "redirect:/app/inventory";
    }

    @PostMapping("/inventory/remove")
    public String decrementProduct(@RequestParam Integer productId) {
        productRepository.deleteById(productId);

        return "redirect:/app/inventory";
    }

}
