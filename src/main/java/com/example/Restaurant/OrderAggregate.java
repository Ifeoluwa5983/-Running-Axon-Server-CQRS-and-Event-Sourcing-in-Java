package com.example.Restaurant;

import com.example.Restaurant.commands.ConfirmOrderCommand;
import com.example.Restaurant.commands.PlaceOrderCommand;
import com.example.Restaurant.commands.ShipOrderCommand;
import com.example.Restaurant.events.OrderConfirmedEvent;
import com.example.Restaurant.events.OrderPlacedEvent;
import com.example.Restaurant.events.OrderShippedEvent;
import com.example.Restaurant.exception.UnconfirmedOrderException;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Data
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
    public void handle(ConfirmOrderCommand command) {
        apply(new OrderConfirmedEvent(orderId));
    }

    @CommandHandler
    public void handle(ShipOrderCommand command) throws UnconfirmedOrderException {
        if (!orderConfirmed) {
            throw new UnconfirmedOrderException();
        }
        apply(new OrderShippedEvent(orderId));
    }

    @EventSourcingHandler
    public void on(OrderConfirmedEvent event) {
        orderConfirmed = true;
    }

    protected OrderAggregate(){}
}
