package com.example.demo.domain;

import java.util.Objects;

public class ProductQuantity {
    private final Integer value;

    public ProductQuantity increment() {
        return new ProductQuantity(value + 1);
    }

    public ProductQuantity(Integer value) {
        Objects.requireNonNull(value, "Product quantity can not be null");
        if(value <= 0 || value > 100) {
            throw new IllegalArgumentException("Product quantity can not be less than 0 or greater than 100");
        }
        this.value = value;
    }

    public Integer asInteger() {
        return value;
    }
}
