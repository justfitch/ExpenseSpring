package dev.fitch.services;

import dev.fitch.entities.Employee;
import dev.fitch.entities.Expense;
import dev.fitch.repos.EmployeeRepo;
import dev.fitch.repos.ExpenseRepo;
import dev.fitch.utilities.LogLevel;
import dev.fitch.utilities.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Component
@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ExpenseRepo expenseRepo;

    @Override
    public Employee createEmployee(Employee employee) {
        this.employeeRepo.save(employee);
        return employee;
    }

    @Override
    public Employee getEmployeeDetails(int id) {
        Optional<Employee> oe = this.employeeRepo.findById(id);
        if(oe.isPresent()) {
            return oe.get();
        } else {
            Logger.log("employee ID " + id + " not found. Exception thrown.", LogLevel.NOT_FOUND);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The requested employee could not be found. " +
                    "Please verify employee ID and try again.");
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeRepo.findAll();
        return employees;
    }

    @Override
    public Employee updateEmployeeRecord(int id, Employee employee) {
        Optional<Employee> oe = employeeRepo.findById(id);
        if (oe.isPresent()) {
            employee.setId(id);
            this.employeeRepo.save(employee);
            return employee;
        } else {
            Logger.log("employee ID " + id + " not found. Exception thrown.", LogLevel.NOT_FOUND);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The requested employee could not be found. " +
                    "Please verify employee ID and try again.");
        }
    }

    @Override
    public String deleteEmployee(int id) {
        Optional<Employee> oe = employeeRepo.findById(id);
        if (oe.isPresent()) {
            List<Expense> expenses = this.expenseRepo.findExpensesByEmployeeId(id);
            if(!expenses.isEmpty()){
                return "Sorry, employees with posted expense reports cannot be deleted";
            } else {
                employeeRepo.deleteById(id);
                return "Employee Deleted Successfully";
            }
        } else {
            Logger.log("employee ID " + id + " not found. Exception thrown.", LogLevel.NOT_FOUND);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The requested employee could not be found. " +
                    "Please verify employee ID and try again.");
        }
    }

    @Override
    public boolean checkEmployeeId(int id) {
        Optional<Employee> oe = employeeRepo.findById(id);
        if (oe.isPresent())
            return true;
        return false;
    }
}
