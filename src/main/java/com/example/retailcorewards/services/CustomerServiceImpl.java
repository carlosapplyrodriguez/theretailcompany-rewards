package com.example.retailcorewards.services;

import com.example.retailcorewards.repositories.CustomerRepository;
import com.example.retailcorewards.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Method to return all persisted objects of type Customer.
     *
     * @return A list of the persisted CustomerDto objects.
     */
    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * Method to add a new Customer.
     *
     * @param customer  Object representing the customer to add.
     */
    @Override
    public void addCustomer(CustomerDto customer) {
        customerRepository.save(customer);
    }

    /**
     * Method to delete a specific Customer.
     *
     * @param customer  Object representing the Customer to delete.
     */
    public void deleteCustomer(CustomerDto customer) {
        customerRepository.delete(customer);
    }
}
