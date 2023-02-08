package com.example.retailcorewards.services;

import com.example.retailcorewards.repositories.OrderRepository;
import com.example.retailcorewards.web.model.Customer;
import com.example.retailcorewards.web.model.CustomerOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    Customer delita = Customer.builder()
            .id("Delral")
            .firstName("Delita")
            .lastName("Heiral")
            .email("delita.heiral@fftexample.com")
            .build();
    CustomerOrder order = CustomerOrder.builder()
            .id("order1")
            .description("Razor x 5, Brush x 1, Labrador x 1, Black Mail Armor x 1")
            .creationDate(LocalDate.of(2022, 12, 21))
            .total(new BigDecimal("70"))
            .customer(delita)
            .build();
    @Mock
    private OrderRepository orderRepository;
    private OrderServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new OrderServiceImpl(orderRepository);
    }

    @Test
    void checkItCanGetAllOrders() {
        // when
        underTest.getAllOrders();

        // then
        verify(orderRepository).findAll();
    }

    @Test
    void checkThatCanAddOrder() {
        // when
        underTest.addOrder(order);

        // then
        ArgumentCaptor<CustomerOrder> orderDtoArgumentCaptor = ArgumentCaptor.forClass(CustomerOrder.class);

        verify(orderRepository).save(orderDtoArgumentCaptor.capture());

        CustomerOrder capturedCustomerOrder = orderDtoArgumentCaptor.getValue();

        assertThat(capturedCustomerOrder).isEqualTo(order);
    }

    @Test
    void checkThatCanDeleteOrder() {
        // when
        underTest.deleteOrder(order);

        // then
        ArgumentCaptor<CustomerOrder> orderDtoArgumentCaptor = ArgumentCaptor.forClass(CustomerOrder.class);

        verify(orderRepository).delete(orderDtoArgumentCaptor.capture());

        CustomerOrder capturedOrder = orderDtoArgumentCaptor.getValue();

        assertThat(order).isEqualTo(capturedOrder);
    }

}