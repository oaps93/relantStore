package com.relantstore.cservice.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
    private String phoneNumber;
    private String rfc;
    @NotEmpty
    @Column(name = "zip_code")
    private String zipCode;
    @NotEmpty
    private String state;



}