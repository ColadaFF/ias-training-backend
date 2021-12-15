package com.example.demo.domain;

import java.util.Objects;

public class ShoppingCarProduct {
    String productId;
    ProductQuantity quantity;

    public ShoppingCarProduct(String productId, ProductQuantity quantity) {
        Objects.requireNonNull(quantity, "product quantity can not be null");


        this.productId = productId;
        this.quantity = quantity;
    }


}
