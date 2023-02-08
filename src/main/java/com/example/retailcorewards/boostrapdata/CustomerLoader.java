package com.example.retailcorewards.boostrapdata;

import com.example.retailcorewards.repositories.CustomerRepository;
import com.example.retailcorewards.repositories.OrderRepository;
import com.example.retailcorewards.web.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerLoader implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public CustomerLoader(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public void run(String... args) throws Exception {
        loadCustomers();
    }

    private void loadCustomers() {
        if (customerRepository.count() == 0) {

            // Creating Ramza

            customerRepository.save(Customer.builder()
                    .id("ramlve")
                    .firstName("Ramza")
                    .lastName("Beoulve")
                    .email("ramza.beoulve@fftexample.com")
                    .build());

            // Creating Delita

            customerRepository.save(Customer.builder()
                    .id("delral")
                    .firstName("Delita")
                    .lastName("Heiral")
                    .email("delita.heiral@fftexample.com")
                    .build());

            customerRepository.save(Customer.builder()
                    .id("musnsa")
                    .firstName("Mustadio")
                    .lastName("Bunansa")
                    .email("mustadio.bunansa@fftexample.com")
                    .build());

            customerRepository.save(Customer.builder()
                    .id("agraks")
                    .firstName("Agrias")
                    .lastName("Oaks")
                    .email("agrias.oaks@fftexample.com\n")
                    .build());

            customerRepository.save(Customer.builder()
                    .id("rapena")
                    .firstName("Rapha")
                    .lastName("Galthena")
                    .email("rapha.galthena@fftexample.com\n")
                    .build());

            customerRepository.save(Customer.builder()
                    .id("marena")
                    .firstName("Marach")
                    .lastName("Galthena")
                    .email("marach.galthena@fftexample.com")
                    .build());

            customerRepository.save(Customer.builder()
                    .id("ciddeu")
                    .firstName("Cidolfus")
                    .lastName("Orlandeu")
                    .email("cidolfus.orlandeu@fftexample.com")
                    .build());

            customerRepository.save(Customer.builder()
                    .id("mellle")
                    .firstName("Meliadoul")
                    .lastName("Tengille")
                    .email("meliadoul.tengille@fftexample.com")
                    .build());

            customerRepository.save(Customer.builder()
                    .id("beomus")
                    .firstName("Beowulf")
                    .lastName("Cadmus")
                    .email("beowulf.cadmus@fftexample.com")
                    .build());

            customerRepository.save(Customer.builder()
                    .id("reilar")
                    .firstName("Reis")
                    .lastName("Duelar")
                    .email("reis.duelar@fftexample.com")
                    .build());

            customerRepository.save(Customer.builder()
                    .id("almlve")
                    .firstName("Alma")
                    .lastName("Beoulve")
                    .email("alma.beoulve@fftexample.com")
                    .build());

            customerRepository.save(Customer.builder()
                    .id("dyclve")
                    .firstName("Dycedarg")
                    .lastName("Beoulve")
                    .email("dycedarg.beoulve@fftexample.com")
                    .build());

            log.info("Data boostrapping completed. " + customerRepository.count() + " customers were loaded.");
        }
    }
}
