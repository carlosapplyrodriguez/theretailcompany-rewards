package com.example.retailcorewards.repositories;

import com.example.retailcorewards.web.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<CustomerOrder, String> {
    List<CustomerOrder> findAll();
}
