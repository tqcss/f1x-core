package com.app.f1x.service;

import com.app.f1x.dto.product.CreateProductRequest;
import com.app.f1x.dto.product.ProductResponse;
import com.app.f1x.dto.product.UpdateProductRequest;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductResponse> getAllProducts();
    Optional<ProductResponse> getProductById(Integer id);
    Optional<ProductResponse> createProduct(CreateProductRequest createProductRequest);
    Optional<ProductResponse> updateProduct(Integer id, UpdateProductRequest updateProductRequest);
    void deleteProduct(Integer id);

}
