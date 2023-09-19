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
    public Customer getCustomer(String email) {
        return this.customerRepository.findByEmail(email).orElse(null);
    }
    @Override
    public List<Customer> getAllActiveCustomer() {
        return this.customerRepository.findByActiveCustomerTrue();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer customerToCreate = this.customerRepository.findByEmail(customer.getEmail()).orElse(null);
        if(customerToCreate == null){
            customer.setActiveCustomer(true);
            return this.customerRepository.save(customer);
        }
        return customerToCreate;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer customerToUpdate = getCustomer(customer.getEmail());
        if(customerToUpdate == null) return null;

        customerToUpdate.setFirstName(customer.getFirstName());
        customerToUpdate.setLastName(customer.getLastName());
        customerToUpdate.setRfc(customer.getRfc());
        customerToUpdate.setPhoneNumber(customer.getPhoneNumber());
        customerToUpdate.setState(customer.getState());
        customerToUpdate.setZipCode(customer.getZipCode());

        return this.customerRepository.save(customerToUpdate) ;
    }

    @Override
    public Customer deleteCustomer(String email) {
        Customer customerToDelete = getCustomer(email);
        if(customerToDelete == null) return null;
        customerToDelete.setActiveCustomer(false);
        return this.customerRepository.save(customerToDelete);
    }

    @Override
    public List<Customer> getByState(String state) {
        return this.customerRepository.findByState(state);
    }


}
