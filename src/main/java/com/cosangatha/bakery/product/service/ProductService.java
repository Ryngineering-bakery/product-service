package com.cosangatha.bakery.product.service;

import com.cosangatha.bakery.product.config.ProductConfig;
import com.cosangatha.bakery.product.model.Product;

import com.cosangatha.bakery.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductConfig config;

//    Supplier<Customer> newDummy = () -> Customer.builder().id("1").name("Test").emailAddress("Test@Gmail.com").address("XYZ Street").build();


    public Product getProduct(String productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("No Product Found"));

    }

    public String createProduct(Product product) {
        log.info("Config Source : {0} , Creating a product", config.getConfigSource());
        product.setId(UUID.randomUUID().toString());
        productRepository.save(product);
        return product.getId();
    }

    public Product updateProduct(String productId, Product toUpdate) {
        log.info("[ ] Updating a product for productId ");
        Product modifiedProduct = productRepository.findById(productId)
                .map(product -> {
                    product.setName(toUpdate.getName());
                    product.setCategory(toUpdate.getCategory());
                    product.setImg_link(toUpdate.getImg_link());
                    return product;
                }).orElseThrow(() -> new IllegalArgumentException("No Product Found"));
        Product productFromDB = productRepository.save(modifiedProduct);
        return productFromDB;
    }


    public void deleteProduct(String productId) {
        Product toDelete = new Product();
        toDelete.setId(productId);
        productRepository.delete(toDelete);
        log.info("[ ] Deleting a product");
    }

}
