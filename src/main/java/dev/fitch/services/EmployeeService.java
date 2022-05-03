package dev.fitch.services;

import dev.fitch.entities.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    Employee getEmployeeDetails(int id);

    List<Employee> getAllEmployees();

    Employee updateEmployeeRecord(int id, Employee employee);

    String deleteEmployee(int id);

    boolean checkEmployeeId(int id);
}
