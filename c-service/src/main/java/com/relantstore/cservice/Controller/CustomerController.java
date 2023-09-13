package com.relantstore.cservice.Controller;

import com.relantstore.cservice.Entity.Customer;
import com.relantstore.cservice.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController (CustomerService customerService) { this.customerService = customerService; }

    @GetMapping
    public ResponseEntity<List<Customer>> listCustomers (@RequestParam(name = "activeCustomer", required = false ) Boolean activeCustomer) {
        List<Customer> customerList = new ArrayList<>();
        if(activeCustomer == null || !activeCustomer){
            customerList = this.customerService.getAllCustomers();
            if(customerList.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        } else {
            customerList = this.customerService.getAllActiveCustomer();
            if(customerList.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Customer> getCustomer (@PathVariable(name = "email") String email){
        Customer customer = this.customerService.getCustomer(email);
        if(customer == null) return ResponseEntity.notFound().build();
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }





}
