package com.example.Restaurant.queryModels;

import lombok.Data;

@Data
public class OrderedProducts {

    private final String orderId;

    private final String product;

    private String quantity;

    private OrderStatus orderStatus;
}
