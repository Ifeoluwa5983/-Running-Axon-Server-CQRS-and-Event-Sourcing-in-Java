package com.example.Restaurant.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class PlaceOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;

    private final String product;

    private String quantity;
}
