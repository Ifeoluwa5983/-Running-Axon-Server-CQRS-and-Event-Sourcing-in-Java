package com.example.Restaurant.events;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class OrderPlacedEvent {

    @TargetAggregateIdentifier
    private final String orderId;

    private final String product;

}
