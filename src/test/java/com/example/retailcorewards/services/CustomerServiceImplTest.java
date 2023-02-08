package com.example.retailcorewards.services;

import com.example.retailcorewards.repositories.CustomerRepository;
import com.example.retailcorewards.web.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    Customer ramza = Customer.builder()
            .id("Ramlve")
            .firstName("Ramza")
            .lastName("Beoulve")
            .email("ramza.beoulve@fftexample.com")
            .build();
    @Mock
    private CustomerRepository customerRepository;
    private CustomerServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new CustomerServiceImpl(customerRepository);
    }

    @Test
    void checkItCanGetAllCustomers() {
        // when
        underTest.getAllCustomers();

        // then
        verify(customerRepository).findAll();

    }

    @Test
    void checkThatCanAddCustomer() {

        // when
        underTest.addCustomer(ramza);

        // then

        ArgumentCaptor<Customer> customerDtoArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

        verify(customerRepository).save(customerDtoArgumentCaptor.capture());

        Customer capturedCustomer = customerDtoArgumentCaptor.getValue();

        assertThat(capturedCustomer).isEqualTo(ramza);

    }

    @Test
    void deleteCustomer() {

        // when
        underTest.deleteCustomer(ramza);

        // then
        ArgumentCaptor<Customer> customerDtoArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

        verify(customerRepository).delete(customerDtoArgumentCaptor.capture());

        Customer capturedCustomerToDelete = customerDtoArgumentCaptor.getValue();

        assertThat(capturedCustomerToDelete).isEqualTo(ramza);

    }
}