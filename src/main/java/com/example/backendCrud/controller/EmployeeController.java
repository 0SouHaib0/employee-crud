package com.example.backendCrud.controller;

import com.example.backendCrud.model.Employee;
import com.example.backendCrud.repository.EmployeeRepo;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeRepo employeeRepo;

    public EmployeeController(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @GetMapping
    public List<Employee> getAllEmployee(){
        return  employeeRepo.findAll();
    }

    @GetMapping("/search")
    public List<Employee> searchEmployees(@RequestParam("query") String query) {
        return employeeRepo.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrPhoneContainingIgnoreCase(
                query, query, query
        );
    }
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id){
        return employeeRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "EMPLOYEE_NOT_FOUND"));
    }



    @PostMapping
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeRepo.save(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable("id") Long id, @Valid @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException ("EMPLOYEE_NOT_FOUND"));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setMail(employeeDetails.getMail());
        employee.setCnssNumber(employeeDetails.getCnssNumber());
        employee.setPhone(employeeDetails.getPhone());

        return employeeRepo.save(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException ("EMPLOYEE_NOT_FOUND"));
        employeeRepo.delete(employee);
    }

}
