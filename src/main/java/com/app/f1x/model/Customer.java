package com.app.f1x.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email; // phone number and email fields can be blank

    public Customer() {}

    public Customer(String firstName, String lastName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstName() { return this.firstName; }
    public String getLastName() { return this.lastName; }
    public String getPhoneNumber() { return this.phoneNumber; }
    public String getEmail() { return this.email; }

}
