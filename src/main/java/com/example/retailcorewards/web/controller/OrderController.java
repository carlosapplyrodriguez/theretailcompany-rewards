package com.example.retailcorewards.web.controller;

import com.example.retailcorewards.services.OrderService;
import com.example.retailcorewards.web.model.CustomerOrder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/orders")
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * @return
     */
    @RequestMapping
    public List<CustomerOrder> getOrders() {
        return orderService.getAllOrders();
    }

    /**
     * @param customerOrder
     */
    @PostMapping
    public void createNewOrder(@RequestBody CustomerOrder customerOrder) {
        orderService.addOrder(customerOrder);
    }

}
