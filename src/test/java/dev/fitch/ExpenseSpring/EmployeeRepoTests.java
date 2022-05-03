package dev.fitch.ExpenseSpring;

import dev.fitch.entities.Employee;
import dev.fitch.repos.EmployeeRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class EmployeeRepoTests {

    @Autowired

    private EmployeeRepo employeeRepo;

    @Test
    public void createEmployee(){
        Employee employee = new Employee(0, "Test", "One", "password");
        employeeRepo.save(employee);
        Assertions.assertNotEquals(0, employee.getId());
    }

    @Test
    public void getEmployeeDetails(){
        Employee employee = new Employee(0, "Test", "Two", "password");
        employeeRepo.save(employee);
        int id = employee.getId();
        Employee testEmployee = employeeRepo.findById(id).get();
        Assertions.assertNotEquals(0, testEmployee.getId());
    }

    @Test
    public void getAllEmployees(){
        List<Employee> employees = employeeRepo.findAll();
        Assertions.assertNotNull(employees);
    }

    @Test
    public void updateEmployee(){
        Employee employee = new Employee(0, "Test", "Three", "password");
        employeeRepo.save(employee);
        employee.setFirstName("Changed");
        employeeRepo.save(employee);
        int id = employee.getId();
        Employee testEmployee = employeeRepo.findById(id).get();
        Assertions.assertEquals("Changed", testEmployee.getFirstName());
    }

    @Test
    public void deleteEmployee(){
        Employee employee = new Employee(0, "Test", "Four", "Password");
        employeeRepo.save(employee);
        int id = employee.getId();
        employeeRepo.delete(employee);
        Optional<Employee> testEmployee = employeeRepo.findById(id);
        Assertions.assertFalse(testEmployee.isPresent());
    }
}
