package com.example.Restaurant.commands;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class ConfirmOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;
}
