package com.relantstore.cservice.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.relantstore.cservice.Entity.Customer;
import com.relantstore.cservice.Error.ErrorMessage;
import com.relantstore.cservice.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        } else {
            customerList = this.customerService.getAllActiveCustomer();
        }
        if(customerList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Customer> getCustomer (@PathVariable(name = "email") String email){
        Customer customer = this.customerService.getCustomer(email);
        if(customer == null) return ResponseEntity.notFound().build();
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

   /* @GetMapping
    public ResponseEntity<List<Customer>> listCustomerByState (@RequestParam String state){
        List<Customer> customerList = this.customerService.getByState(state);
        if(customerList == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }*/

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer, BindingResult result){
        if(result.hasErrors()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Customer customerSaved = this.customerService.createCustomer(customer);
        return new ResponseEntity<>(customerSaved, HttpStatus.CREATED);

    }

    @PutMapping("/{email}")
    public  ResponseEntity<Customer> updateCustomerInfo(@PathVariable String email, @Valid @RequestBody Customer customer, BindingResult result){
        if(result.hasErrors()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        customer.setEmail(email);
        Customer customerToUpdate = this.customerService.updateCustomer(customer);
        if(customerToUpdate == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(customerToUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable String email){
        Customer customerToDelete = this.customerService.deleteCustomer(email);
        if(customerToDelete == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(customerToDelete, HttpStatus.OK);
    }

    private String formatMessage(BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map( e -> {
                    Map<String,String> errorMap = new HashMap<>();
                    errorMap.put(e.getField(), e.getDefaultMessage());
                    return errorMap;
                })
                .collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";
        try{
            jsonString = objectMapper.writeValueAsString(errorMessage);

        } catch (JsonProcessingException jpe){
            jpe.printStackTrace();

        }
        return jsonString;
    }





}
