package com.example.retailcorewards.services;

import com.example.retailcorewards.web.model.CustomerOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RewardsService {
    Map<String, Map<String, Integer>> getRewardPoints(List<CustomerOrder> orders) throws Exception;
}
