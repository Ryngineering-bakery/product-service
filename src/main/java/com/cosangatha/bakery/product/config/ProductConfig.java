package com.cosangatha.bakery.product.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

// NOTE: @Configuration is required to register the class as a bean.
@Configuration
@ConfigurationProperties(prefix = "example")
@Getter
@Setter
public class ProductConfig {

    private String configSource;
}
