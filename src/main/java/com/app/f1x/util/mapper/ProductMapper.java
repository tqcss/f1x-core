package com.app.f1x.util.mapper;

import com.app.f1x.dto.product.CreateProductRequest;
import com.app.f1x.dto.product.ProductResponse;
import com.app.f1x.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .inventoryCost(product.getInventoryCost())
                .usageCost(product.getUsageCost())
                .build();
    }

    public Product toProduct(CreateProductRequest createProductRequest) {
        Product product = new Product();
        product.setName(createProductRequest.getName());
        product.setInventoryCost(createProductRequest.getInventoryCost());
        product.setUsageCost(createProductRequest.getUsageCost());
        return product;
    }
}
