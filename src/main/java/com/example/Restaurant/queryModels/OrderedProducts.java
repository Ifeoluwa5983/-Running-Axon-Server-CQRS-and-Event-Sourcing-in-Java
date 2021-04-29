package com.example.Restaurant.queryModels;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
public class OrderedProducts {

    private final String orderId;
    private final String product;
    private OrderStatus orderStatus;
}
