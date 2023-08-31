package com.relantstore.cservice.Service;

import com.relantstore.cservice.Entity.Customer;
import com.relantstore.cservice.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements CustomerServiceInterface {


    final CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(Long id) {
        return this.customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer customerToCreate = this.customerRepository.findByEmail(customer.getEmail()).orElse(null);
        if(customerToCreate == null){
            return this.customerRepository.save(customer);
        }
        return customerToCreate;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return null;
    }

    @Override
    public Customer deleteCustomer(Long id) {
        return null;
    }

    @Override
    public List<Customer> getByState(String state) {
        return null;
    }
}
