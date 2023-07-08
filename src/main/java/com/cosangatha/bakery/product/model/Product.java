package com.cosangatha.bakery.product.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

//@Builder
@Getter
@Setter
@ToString
@Entity
//@NoArgsConstructor
@Table(name = "Product")
public class Product extends RepresentationModel<Product> {

//        TODO : automated UUID generator
        @Id
        @Column(name="product_id")
        private String id;

        private String name;
        // TODO: 5/7/23 : update to enum
        @Column(name="category")
        private String category;

        private String img_link;

}
