package com.example.Restaurant;

import com.example.Restaurant.commands.PlaceOrderCommand;
import com.example.Restaurant.commands.ShipOrderCommand;
import com.example.Restaurant.events.OrderConfirmedEvent;
import com.example.Restaurant.events.OrderPlacedEvent;
import com.example.Restaurant.events.OrderShippedEvent;
import com.example.Restaurant.exception.UnconfirmedOrderException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class OrderAggregateTest {

    private FixtureConfiguration<OrderAggregate> fixture;

    @BeforeEach
    void setUp() {
        fixture = new AggregateTestFixture<>(OrderAggregate.class);
    }

    @Test
    void  testThatWhenTheAggregateHandlesThePlaceOrderCommand_ItShouldProduceAnOrderPlacedEvent(){
        String orderId = UUID.randomUUID().toString();
        String product = "Deluxe Chair";
        String quantity = "5";
        fixture.givenNoPriorActivity()
                .when(new PlaceOrderCommand(orderId, product, quantity))
                .expectEvents(new OrderPlacedEvent(orderId, product));
    }

    @Test
    void testThatAnOrderThatHasNotBeenConfirmedCannotBeShipped (){
        String orderId = UUID.randomUUID().toString();
        String product = "Deluxe Chair";
        fixture.given(new OrderPlacedEvent(orderId, product))
                .when(new ShipOrderCommand(orderId))
                .expectException(UnconfirmedOrderException.class);
    }

    @Test
    void testThatAnOrderThatHasBeenConfirmedCanBeShipped(){
        String orderId = UUID.randomUUID().toString();
        String product = "Deluxe Chair";
        fixture.given(new OrderPlacedEvent(orderId, product), new OrderConfirmedEvent(orderId))
                .when(new ShipOrderCommand(orderId))
                .expectEvents(new OrderShippedEvent(orderId));
    }

}