package com.example.Restaurant.controller;

import com.example.Restaurant.commands.ConfirmOrderCommand;
import com.example.Restaurant.commands.PlaceOrderCommand;
import com.example.Restaurant.commands.ShipOrderCommand;
import com.example.Restaurant.queryModels.FindAllOrderedProductsQuery;
import com.example.Restaurant.queryModels.OrderedProducts;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class OrderRestEndpoint {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public OrderRestEndpoint(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/ship-order")
    public void shipOrder(@RequestBody String product, String quantity) {
        String orderId = UUID.randomUUID().toString();
        commandGateway.send(new PlaceOrderCommand(orderId, product, quantity));
        commandGateway.send(new ConfirmOrderCommand(orderId));
        commandGateway.send(new ShipOrderCommand(orderId));
    }

    @PostMapping("/place-order")
    public void placeOrder(@RequestBody String product, String quantity){
        String orderId = UUID.randomUUID().toString();
        commandGateway.send(new PlaceOrderCommand(orderId, product, quantity));
    }

    @GetMapping("/all-orders")
    public List<OrderedProducts> findAllOrderedProducts() {
        return queryGateway.query(new FindAllOrderedProductsQuery(),
                ResponseTypes.multipleInstancesOf(OrderedProducts.class)).join();
    }

}
