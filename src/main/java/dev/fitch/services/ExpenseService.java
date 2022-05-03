package dev.fitch.services;

import dev.fitch.entities.Expense;

import java.util.List;

public interface ExpenseService {

    Expense createExpense(Expense expense);

    Expense getExpenseDetails(int expenseNumber);

    List<Expense> getAllExpenses(String status);

    List<Expense> findExpensesByEmployeeId(int employeeId);

    Expense updateExpense(int expenseNumber, Expense expense);

    Expense approveExpense(int expenseNumber);

    Expense denyExpense(int expenseNumber);

    String deleteExpense(int expenseNumber);
}
