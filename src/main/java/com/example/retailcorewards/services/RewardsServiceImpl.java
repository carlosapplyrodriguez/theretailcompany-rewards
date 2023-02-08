package com.example.retailcorewards.services;

import com.example.retailcorewards.web.model.CustomerOrder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A service to calculate reward points through order processing.
 */
@Service
public class RewardsServiceImpl implements RewardsService {

    private static final int lowerRewardsThreshold = 50;
    private static final int upperRewardsThreshold = 100;
    private static final int rewardPointsForLowerThreshold = 1;
    private static final int rewardPointsForUpperThreshold = 2;

    /**
     * A method to process a list of OrderDto objects to summarize the total of reward points a customer accrued by month based in purchase amounts.
     *
     * @param orders A list of OrderDto objects used to calculate reward points.
     * @return A map where the key is the customer name, and where the value is a map where the key is a month and they value is the points for that month.
     * @throws Exception Exception is thrown when the rewards thresholds have negative values.
     */
    @Override
    public Map<String, Map<String, Integer>> getRewardPoints(List<CustomerOrder> orders) throws Exception {

        int orderTotal;
        String customerFullName;
        String monthOfOrder;

        if (lowerRewardsThreshold <= 0 || upperRewardsThreshold <= 0 || rewardPointsForLowerThreshold <= 0 || rewardPointsForUpperThreshold <= 0) {
            throw new Exception("Invalid configuration value for rewards points calculation, negative values are not accepted.");
        }

        Map<String, Map<String, Integer>> result = new HashMap<>();

        if (null != orders && !orders.isEmpty()) {
            for (CustomerOrder order : orders) {
                orderTotal = order.getTotal().intValue();
                customerFullName = order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName();
                monthOfOrder = order.getCreationDate().getMonth().toString();

                if (orderTotal >= lowerRewardsThreshold && orderTotal < upperRewardsThreshold) {
                    if (result.containsKey(customerFullName)) {
                        Map<String, Integer> map = result.get(customerFullName);
                        if (map.containsKey(monthOfOrder)) {
                            map.replace(monthOfOrder, map.get(monthOfOrder) + calculatePointsInLowerThreshold(orderTotal, lowerRewardsThreshold, rewardPointsForLowerThreshold));
                        } else {
                            map.put(monthOfOrder, calculatePointsInLowerThreshold(orderTotal, lowerRewardsThreshold, rewardPointsForLowerThreshold));
                        }
                    } else {
                        String tempMonthOfOrder = monthOfOrder;
                        int tempOrderTotal = orderTotal;
                        result.put(customerFullName, new HashMap<>() {{
                            put(tempMonthOfOrder, calculatePointsInLowerThreshold(tempOrderTotal, lowerRewardsThreshold, rewardPointsForLowerThreshold));
                        }});
                    }
                } else if (orderTotal >= upperRewardsThreshold) {
                    if (result.containsKey(customerFullName)) {
                        Map<String, Integer> map = result.get(customerFullName);
                        if (map.containsKey(monthOfOrder)) {
                            map.replace(monthOfOrder, map.get(monthOfOrder) + calculatePointsInUpperThreshold(orderTotal, lowerRewardsThreshold, upperRewardsThreshold, rewardPointsForUpperThreshold));
                        } else {
                            map.put(monthOfOrder, calculatePointsInUpperThreshold(orderTotal, lowerRewardsThreshold, upperRewardsThreshold, rewardPointsForUpperThreshold));
                        }
                    } else {
                        String tempMonthOfOrder = monthOfOrder;
                        int tempOrderTotal = orderTotal;
                        result.put(customerFullName, new HashMap<>() {{
                            put(tempMonthOfOrder, calculatePointsInUpperThreshold(tempOrderTotal, lowerRewardsThreshold, upperRewardsThreshold, rewardPointsForUpperThreshold));
                        }});
                    }
                }
            }
        }
        return result;
    }

    /**
     * A method to calculate reward points when the purchase amount did pass the lower reward amount threshold, but not the upper threshold.
     *
     * @param orderTotal                    The total amount spent in the order.
     * @param lowerRewardsThreshold         The lower threshold configured to determine when a customer starts accruing reward points.
     * @param rewardPointsForLowerThreshold The amount of rewards points per dollar a customer can accrue when spending above the lower order threshold.
     * @return An integer for the total points accrued in the lower threshold.
     */
    public Integer calculatePointsInLowerThreshold(int orderTotal, int lowerRewardsThreshold, int rewardPointsForLowerThreshold) {
        int result = 0;

        if (orderTotal >= lowerRewardsThreshold) {
            result = (orderTotal - lowerRewardsThreshold) * rewardPointsForLowerThreshold;
        }
        return result;
    }

    /**
     * A method to calculate reward points when the purchase amount surpassed the upper amount threshold.
     *
     * @param orderTotal:                   The total amount spent in the order.
     * @param lowerRewardsThreshold         The lower threshold configured to determine when a customer starts accruing reward points.
     * @param upperRewardsThreshold         The upper threshold configured to determine when a customer starts earning reward points.
     * @param rewardPointsForUpperThreshold The amount of rewards points per dollar a customer can accrue when spending above the upper order threshold.
     * @return An integer for the total points accrued in the upper threshold.
     */
    public Integer calculatePointsInUpperThreshold(int orderTotal, int lowerRewardsThreshold, int upperRewardsThreshold, int rewardPointsForUpperThreshold) {
        int result = 0;

        if (orderTotal >= upperRewardsThreshold) {
            result = lowerRewardsThreshold + (orderTotal - upperRewardsThreshold) * rewardPointsForUpperThreshold;
        } else if (orderTotal >= lowerRewardsThreshold && orderTotal <= upperRewardsThreshold) {
            result = (orderTotal - lowerRewardsThreshold) * rewardPointsForLowerThreshold;
        }
        return result;
    }
}
