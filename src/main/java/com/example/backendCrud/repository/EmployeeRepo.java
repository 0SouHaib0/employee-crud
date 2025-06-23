package com.example.backendCrud.repository;

import com.example.backendCrud.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    List<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrPhoneContainingIgnoreCase(
            String firstName, String lastName, String phone);
}
