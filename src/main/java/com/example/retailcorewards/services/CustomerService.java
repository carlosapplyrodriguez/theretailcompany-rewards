package com.example.retailcorewards.services;


import com.example.retailcorewards.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    List<CustomerDto> getAllCustomers();

    void addCustomer(CustomerDto customer);

    void deleteCustomer(CustomerDto customer);
}
