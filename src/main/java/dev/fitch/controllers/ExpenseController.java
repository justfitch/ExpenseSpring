package dev.fitch.controllers;

import dev.fitch.entities.Expense;
import dev.fitch.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.ConditionalOnRepositoryType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
public class ExpenseController {

    @Autowired
    public ExpenseService expenseService;

    @PostMapping("/expenses")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Expense createExpense(@RequestBody Expense expense){
        expense.setStatus("Pending");
        return expenseService.createExpense(expense);
    }

    @GetMapping("/expenses/{expenseNumber}")
    public Expense getExpenseDetails(@PathVariable int expenseNumber) {
        return expenseService.getExpenseDetails(expenseNumber);
    }

    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(@RequestParam(required = false) String status){
        return expenseService.getAllExpenses(status);
    }

    @PutMapping("/expenses/{expenseNumber}")
    public Expense updateExpense(@PathVariable int expenseNumber, @RequestBody Expense expense){
        return expenseService.updateExpense(expenseNumber, expense);
    }

    @PatchMapping("/expenses/{expenseNumber}/approve")
    public Expense approveExpense(@PathVariable int expenseNumber){
        return expenseService.approveExpense(expenseNumber);
    }

    @PatchMapping("/expenses/{expenseNumber}/deny")
    public Expense denyExpense(@PathVariable int expenseNumber){
        return expenseService.denyExpense(expenseNumber);
    }

    @DeleteMapping("/expenses/{expenseNumber}")
    public String deleteExpense(@PathVariable int expenseNumber){
        return expenseService.deleteExpense(expenseNumber);
    }

//NESTED
    @GetMapping("/employees/{employeeId}/expenses")
    public List<Expense> findExpensesByEmployeeId(@PathVariable int employeeId){
        return expenseService.findExpensesByEmployeeId(employeeId);
    }

    @PostMapping("/employees/{employeeId}/expenses")
    public Expense createExpenseByEmployee(@PathVariable int employeeId, @RequestBody Expense expense){
        expense.setEmployeeId(employeeId);
        return expenseService.createExpense(expense);
    }

}
