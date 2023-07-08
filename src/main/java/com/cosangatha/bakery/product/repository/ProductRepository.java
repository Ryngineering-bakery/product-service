package com.cosangatha.bakery.product.repository;

import com.cosangatha.bakery.product.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product,String> {
    public Product findByCategory(String category);
}
