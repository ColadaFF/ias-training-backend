package com.example.demo.domain;

import java.time.Instant;
import java.util.List;

public class ShoppingCar {
    String code;
    String clientId;
    List<ShoppingCarProduct> products;
    Instant createdAt;
    Instant updatedAt;
}
