package com.pasanyasara.EmployeeCRUDApplication.controller;

import com.pasanyasara.EmployeeCRUDApplication.model.Employee;
import com.pasanyasara.EmployeeCRUDApplication.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    //get all empolyees
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/employees")
    public List<Employee> getAllEmployees()
    {

        return employeeRepository.findAll();
    }

    //create employee REST api
    @PostMapping("employees")
    public Employee createEmployee(@RequestBody Employee employee)
    {
        return employeeRepository.save(employee);
    }
}
