package com.example.retailcorewards.services;


import com.example.retailcorewards.web.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    List<Customer> getAllCustomers();

    void addCustomer(Customer customer);

    void deleteCustomer(Customer customer);
}
