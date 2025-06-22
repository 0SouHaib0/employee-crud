package com.example.backendCrud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "last name is required")
    private  String lastName;
    @NotBlank(message = "phone is required")
    private String phone;

    @Email
    private String mail;

    public Employee(Long id, String lastName, String firstName, String phone, String mail, String cnssNumber) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.mail = mail;
        this.cnssNumber = cnssNumber;
    }

    private  String cnssNumber;

    public Employee() {
    }




}
