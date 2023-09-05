package com.relantstore.cservice.RepositoryTest;

import com.relantstore.cservice.Entity.Customer;
import com.relantstore.cservice.Repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    public void setUp(){

        Customer customer1 = Customer.builder()
                .firstName("Josue")
                .lastName("Aguilera")
                .email("jaguilera@gmail.com")
                .rfc("jaco910323ja4")
                .state("Jalisco")
                .zipCode("45150")
                .phoneNumber("3332453919")
                .build();

        this.customerRepository.save(customer1);

        Customer customer2 = Customer.builder()
                .firstName("Josue")
                .lastName("Antonio")
                .email("jantonio@gmail.com")
                .rfc("jano910213il4")
                .state("Jalisco")
                .zipCode("41234")
                .phoneNumber("3333339193")
                .activeCustomer(true)
                .build();

        this.customerRepository.save(customer2);

    }

    @Test
    public void whenFindByEmail_thenReturnAnOptional(){

        String emailSetUp  = "jaguilera@gmail.com";
        String emailPresaved  = "dhernandez@gmail.com";
        String notFoundEmail = "oaps93@gmail.com";

        Assertions.assertNotNull(this.customerRepository.findByEmail(emailPresaved).orElse(null));
        Assertions.assertNotNull(this.customerRepository.findByEmail(emailSetUp).orElse(null));
        Assertions.assertNull(this.customerRepository.findByEmail(notFoundEmail).orElse(null));

    }

    @Test
    public void whenFindByActiveCustomer_thenReturnList(){

        Assertions.assertEquals(3,this.customerRepository.findByActiveCustomerTrue().size());
    }

    @Test
    public  void  whenFindByState_thenReturnList(){

        Assertions.assertEquals(4,this.customerRepository.findByState("Jalisco").size());
        Assertions.assertEquals(1,this.customerRepository.findByState("Campeche").size());
    }
}
