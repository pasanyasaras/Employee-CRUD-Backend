package com.pasanyasara.EmployeeCRUDApplication.controller;

import com.pasanyasara.EmployeeCRUDApplication.exception.ResourceNotFoundException;
import com.pasanyasara.EmployeeCRUDApplication.model.Employee;
import com.pasanyasara.EmployeeCRUDApplication.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    //get all empolyees
    //@CrossOrigin(origins = "http://localhost:4200")
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

    //get employee by ID rest API
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exit with id: "+id));

        return ResponseEntity.ok(employee);
    }

    //update employee REST API
    @CrossOrigin
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exit with id: "+id));
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());

        Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }
}
