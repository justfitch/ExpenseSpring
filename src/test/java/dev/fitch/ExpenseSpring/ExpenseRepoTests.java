package dev.fitch.ExpenseSpring;

import dev.fitch.entities.Expense;
import dev.fitch.repos.ExpenseRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ExpenseRepoTests {

    @Autowired

    private ExpenseRepo expenseRepo;

    @Test
    public void createExpense(){
        Expense expense = new Expense(0, 1, 50, "Create Test");
        expenseRepo.save(expense);
        Assertions.assertNotEquals(0, expense.getExpenseNumber());
    }

    @Test
    public void getExpenseDetails(){
        Expense expense = new Expense(0, 1, 100, "Get Test");
        expenseRepo.save(expense);
        int id = expense.getExpenseNumber();
        Expense testExpense = expenseRepo.findById(id).get();
        Assertions.assertNotEquals(0, testExpense.getExpenseNumber());
    }

    @Test
    public void getAllExpenses(){
        List<Expense> expenses = expenseRepo.findAll();
        Assertions.assertNotNull(expenses);
    }

    @Test
    public void updateExpense(){
        Expense expense = new Expense(0, 1, 50, "Update Test");
        expenseRepo.save(expense);
        expense.setStatus("Denied");
        expenseRepo.save(expense);
        int number = expense.getExpenseNumber();
        Expense testExpense = expenseRepo.findById(number).get();
        Assertions.assertEquals("Denied", testExpense.getStatus());
    }

    @Test
    public void deleteExpense(){
        Expense expense = new Expense(0, 1, 200, "Delete Test");
        expenseRepo.save(expense);
        int number = expense.getExpenseNumber();
        expenseRepo.delete(expense);
        Optional<Expense> testExpense = expenseRepo.findById(number);
        Assertions.assertFalse(testExpense.isPresent());
    }
}

