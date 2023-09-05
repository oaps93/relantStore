package com.relantstore.cservice.Repository;

import com.relantstore.cservice.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);
    List<Customer> findByActiveCustomerTrue();
    List<Customer> findByState(String state);
}
