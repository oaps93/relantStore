package com.relantstore.cservice.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Not empty first name")
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @NotEmpty(message = "Not empty email address")
    @Email(message = "Not valid email account")
    private String email;
    @Column(name = "phone_number")
    @Size(min = 10, max = 10, message = "Invalid phone number")
    private String phoneNumber;
    private String rfc;
    @NotEmpty
    @Column(name = "zip_code")
    @Size(min = 5, max = 5, message = "Invalid Zipcode")
    private String zipCode;
    @NotEmpty
    private String state;
    @Column(name = "active_customer")
    private boolean activeCustomer;



}
