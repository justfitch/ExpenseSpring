package dev.fitch.controllers;

import dev.fitch.entities.Employee;
import dev.fitch.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.OnError;
import java.util.List;

@Component
@RestController
public class EmployeeController {

    @Autowired
    public EmployeeService employeeService;

    @PostMapping("/employees")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }


    @GetMapping("/employees/{id}")  //Generate /employees route for get requests
    @ResponseBody // Convert whatever is returned from Service into a Json to include in http response
    public Employee getEmployeeDetails(@PathVariable int id){
        Employee employee = this.employeeService.getEmployeeDetails(id);
        return employee;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        List<Employee> employees = this. employeeService.getAllEmployees();
        return employees;
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployeeRecord(@PathVariable int id, @RequestBody Employee employee){
        return employeeService.updateEmployeeRecord(id, employee);
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id){
        return employeeService.deleteEmployee(id);
    }

}
