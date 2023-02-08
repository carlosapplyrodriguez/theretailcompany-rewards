package com.example.retailcorewards.services;

import com.example.retailcorewards.web.model.CustomerOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    List<CustomerOrder> getAllOrders();

    void addOrder(CustomerOrder order);

    void deleteOrder(CustomerOrder orderId);

}
