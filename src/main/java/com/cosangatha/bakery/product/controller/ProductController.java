package com.cosangatha.bakery.product.controller;

import com.cosangatha.bakery.product.model.Product;
import com.cosangatha.bakery.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value="v1/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable("productId") String productId, @RequestHeader(value="correlationId") String correlationId) {
        log.info("Correlation Id : {0} " , correlationId);
        Product product = productService.getProduct(productId);
//        Create HATEOS links
        product.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                .methodOn(ProductController.class)
                .getProduct(productId,correlationId)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                        .methodOn(ProductController.class)
                        .createProduct(null)).withRel("createProduct"));
        return ResponseEntity.ok(product);
    }


    @PutMapping(value = "/{productId}")
    public ResponseEntity<Product> updateCustomer(@PathVariable("productId") String productId, @RequestBody Product toUpdate) {
        Product updatedProduct = productService.updateProduct(productId, toUpdate);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<Product> deleteCustomer(@PathVariable("productId") String productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product toCreate) {
        String productId = productService.createProduct(toCreate);
        return ResponseEntity.created(URI.create("v1/Product/" + productId)).build();
    }
}
