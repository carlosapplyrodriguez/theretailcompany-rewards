package com.example.retailcorewards.repositories;

import com.example.retailcorewards.web.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
