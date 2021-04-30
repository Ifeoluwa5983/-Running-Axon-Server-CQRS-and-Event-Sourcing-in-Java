package com.example.Restaurant.events;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class OrderConfirmedEvent {

    @TargetAggregateIdentifier
    private final String orderId;
}
