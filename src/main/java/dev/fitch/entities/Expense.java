package dev.fitch.entities;

import org.springframework.data.annotation.Reference;

import javax.persistence.*;

@Entity
@Table(name = "expense")
public class Expense {

    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_number")
    int expenseNumber;

    @Column(name = "employee_id")
    int employeeId;

    @Column(name = "amount")
    double amount;

    @Column(name = "expense_desc")
    String description;

    @Column (name = "status")
    String status;

    public Expense(int expenseNumber, int employeeId, double amount, String description) {
        this.expenseNumber = expenseNumber;
        this.employeeId = employeeId;
        this.amount = amount;
        this.description = description;
        this.status = "Pending";
    }
    public Expense(int expenseNumber, int employeeId, double amount, String description, String status) {
        this.expenseNumber = expenseNumber;
        this.employeeId = employeeId;
        this.amount = amount;
        this.description = description;
        this.status = status;
    }

    public Expense() {
    }

    public int getExpenseNumber() {
        return expenseNumber;
    }

    public void setExpenseNumber(int expenseNumber) {
        this.expenseNumber = expenseNumber;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseNumber=" + expenseNumber +
                ", employeeId=" + employeeId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
