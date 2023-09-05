package com.relantstore.cservice.Service;

import com.relantstore.cservice.Entity.Customer;

import java.util.List;

public interface CustomerServiceInterface {

    List<Customer> getAllCustomers();
    Customer getCustomer(Long id);
    List<Customer> getAllActiveCustomer();
    List<Customer> getByState(String state);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    Customer deleteCustomer(Long id);





}
