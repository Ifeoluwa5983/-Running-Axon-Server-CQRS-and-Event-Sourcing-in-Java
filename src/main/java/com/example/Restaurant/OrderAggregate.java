package com.example.Restaurant;

import com.example.Restaurant.commands.ConfirmOrderCommand;
import com.example.Restaurant.commands.PlaceOrderCommand;
import com.example.Restaurant.commands.ShipOrderCommand;
import com.example.Restaurant.events.OrderConfirmedEvent;
import com.example.Restaurant.events.OrderPlacedEvent;
import com.example.Restaurant.events.OrderShippedEvent;
import com.example.Restaurant.exception.UnconfirmedOrderException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Aggregate

public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;

    private  Boolean orderConfirmed;

    @CommandHandler
    public OrderAggregate(PlaceOrderCommand command) {
        apply(new OrderPlacedEvent(command.getOrderId(), command.getProduct()));
    }

    @EventSourcingHandler
    public void on(OrderPlacedEvent event){
        this.orderId = event.getOrderId();
        orderConfirmed = false;
    }

    @CommandHandler
    public void handle (ConfirmOrderCommand command) {
        apply(new OrderConfirmedEvent(command.getOrderId()));
    }

    @EventSourcingHandler
    public void on(OrderConfirmedEvent event) {
        log.info("id -> {}", event.getOrderId());
    }

    @CommandHandler
    public void handle(ShipOrderCommand command) throws UnconfirmedOrderException {
//        if (!orderConfirmed) {
//            throw new UnconfirmedOrderException();
//        }
        apply(new OrderShippedEvent(command.getOrderId()));
    }

    @EventSourcingHandler
    public void on(OrderShippedEvent event){
        log.info("Got here {}", event);
    }

    public OrderAggregate(){}
}
