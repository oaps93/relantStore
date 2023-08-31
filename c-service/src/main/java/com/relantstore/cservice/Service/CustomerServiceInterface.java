package com.relantstore.cservice.Service;

import com.relantstore.cservice.Entity.Customer;

import java.util.List;

public interface CustomerServiceInterface {

    List<Customer> getAllCustomers();
    Customer getCustomer(Long id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    Customer deleteCustomer(Long id);
    List<Customer> getByState(String state);



}
