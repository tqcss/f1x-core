package com.app.f1x.controller;

import com.app.f1x.dto.product.CreateProductRequest;
import com.app.f1x.dto.product.ProductResponse;
import com.app.f1x.dto.product.UpdateProductRequest;
import com.app.f1x.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<ProductResponse> getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Optional<ProductResponse> createProduct(@Valid @RequestBody CreateProductRequest createProductRequest) {
        return productService.createProduct(createProductRequest);
    }

    @PutMapping("/{id}")
    public Optional<ProductResponse> updateProduct(@PathVariable int id, @Valid @RequestBody UpdateProductRequest updateProductRequest) {
        return productService.updateProduct(id, updateProductRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }

}
