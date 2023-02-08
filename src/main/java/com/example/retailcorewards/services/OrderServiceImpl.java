package com.example.retailcorewards.services;

import com.example.retailcorewards.repositories.OrderRepository;
import com.example.retailcorewards.web.model.CustomerOrder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A service in charge of performing basic operations in Order objects.
 * <p>
 * Supports: Getting all orders, adding an order (requires passing the order to add.), deleting an order (requires the order to delete).
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Method to return all persisted objects of type Order.
     *
     * @return a list of the persisted OrderDto objects.
     */
    @Override
    public List<CustomerOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Method to add a new order
     *
     * @param customerOrder Object representing the order to add.
     */
    @Override
    public void addOrder(CustomerOrder customerOrder) {
        orderRepository.save(customerOrder);
    }

    /**
     * Method to delete a specific order.
     *
     * @param customerOrder Object representing the Order to delete.
     */
    @Override
    public void deleteOrder(CustomerOrder customerOrder) {
        orderRepository.delete(customerOrder);
    }

}
