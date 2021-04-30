package com.example.Restaurant.events;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class OrderPlacedEvent {

    @TargetAggregateIdentifier
    private final String orderId;

    private final String product;

    private String quantity;

}
