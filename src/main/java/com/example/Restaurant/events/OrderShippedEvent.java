package com.example.Restaurant.events;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class OrderShippedEvent {

    @TargetAggregateIdentifier
    private final String orderId;
}
