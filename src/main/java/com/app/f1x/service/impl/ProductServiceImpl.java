package com.app.f1x.service.impl;

import com.app.f1x.dto.product.CreateProductRequest;
import com.app.f1x.dto.product.ProductResponse;
import com.app.f1x.dto.product.UpdateProductRequest;
import com.app.f1x.model.Product;
import com.app.f1x.repository.ProductRepository;
import com.app.f1x.service.ProductService;
import com.app.f1x.util.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::toProductResponse).toList();
    }

    @Override
    public Optional<ProductResponse> getProductById(Integer id) {
        return productRepository.findById(id).map(productMapper::toProductResponse);
    }

    @Override
    public ProductResponse createProduct(CreateProductRequest createProductRequest) {
        Product product = productMapper.toProduct(createProductRequest);
        productRepository.save(product);
        return productMapper.toProductResponse(product);
    }

    @Override
    public Optional<ProductResponse> updateProduct(Integer id, UpdateProductRequest updateProductRequest) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) { return Optional.empty(); }

        Product product = optionalProduct.get();
        product.setName(updateProductRequest.getName());
        product.setInventoryCost(updateProductRequest.getInventoryCost());
        product.setUsageCost(updateProductRequest.getUsageCost());
        productRepository.save(product);

        return optionalProduct.map(productMapper::toProductResponse);
    }

    @Override
    public void deleteProduct(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            log.info("Product with id {} not found", id);
            return;
        }
        log.info("Deleting product with id {}", id);
        productRepository.deleteById(id);
    }
}
