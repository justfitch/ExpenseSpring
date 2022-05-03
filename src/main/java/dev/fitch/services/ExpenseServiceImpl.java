package dev.fitch.services;

import dev.fitch.entities.Employee;
import dev.fitch.entities.Expense;
import dev.fitch.repos.ExpenseRepo;
import dev.fitch.utilities.LogLevel;
import dev.fitch.utilities.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Component
@Service
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    private ExpenseRepo expenseRepo;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public Expense createExpense(Expense expense) {
        if (expense.getAmount() > 0) {
            expenseRepo.save(expense);
            return expense;
        } else {
            Logger.log("Expense must be greater than $0. Exception thrown.", LogLevel.INVALID);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Expense amount must be greater than $0.");
        }
    }

    @Override
    public Expense getExpenseDetails(int expenseNumber) {
        Optional<Expense> oe = expenseRepo.findById(expenseNumber);
        if(oe.isPresent()) {
            return oe.get();
        } else {
            Logger.log("Expense report number " + expenseNumber + " not found. Exception thrown.", LogLevel.NOT_FOUND);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The requested expense report could not be found. " +
                    "Please check your report number and try again.");
        }
    }

    @Override
    public List<Expense> getAllExpenses(String status) {
        List<Expense> expenses;
        if (status == null) {
            expenses = expenseRepo.findAll();
        } else {
            expenses = expenseRepo.findByStatus(status);
        }
        return expenses;
    }

    @Override
    public List<Expense> findExpensesByEmployeeId(int employeeId) {
        if (employeeService.checkEmployeeId(employeeId)) {
            return expenseRepo.findExpensesByEmployeeId(employeeId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee ID " + employeeId + " could not be found");
        }
    }

    @Override
    public Expense updateExpense(int expenseNumber, Expense expense) {
        Optional<Expense> oe = expenseRepo.findById(expenseNumber);
        if(oe.isPresent()){
            if(oe.get().getStatus().equals("Pending")){
                expense.setExpenseNumber(expenseNumber);
                expenseRepo.save(expense);
                return expense;
            } else {
                Logger.log("Expense report number " + expenseNumber + " has already been resolved. Exception thrown.", LogLevel.LOCKED);
                throw new ResponseStatusException(HttpStatus.LOCKED, "Only pending expense reports may be changed.");
            }
        } else {
            Logger.log("Expense report " + expenseNumber + " not found. Exception thrown.", LogLevel.NOT_FOUND);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The requested expense report could not be found. " +
                    "Please check your report number and try again.");
        }
    }

    @Override
    public Expense approveExpense(int expenseNumber) {
        Optional<Expense> oe = expenseRepo.findById(expenseNumber);
        if(oe.isPresent()){
            Expense expense = oe.get();
            if(expense.getStatus().equals("Pending")){
                expense.setStatus("Approved");
                expenseRepo.save(expense);
                return expense;
            } else {
                Logger.log("Expense report number " + expenseNumber + " has already been resolved. Exception thrown.", LogLevel.LOCKED);
                throw new ResponseStatusException(HttpStatus.LOCKED, "Only pending expense reports can be approved.");
            }
        } else{
            Logger.log("Expense report " + expenseNumber + " not found. Exception thrown.", LogLevel.NOT_FOUND);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The requested expense report could not be found. " +
                    "Please check your report number and try again.");
        }
    }

    @Override
    public Expense denyExpense(int expenseNumber) {
        Optional<Expense> oe = expenseRepo.findById(expenseNumber);
        if(oe.isPresent()){
            Expense expense = oe.get();
            if(expense.getStatus().equals("Pending")){
                expense.setStatus("Denied");
                expenseRepo.save(expense);
                return expense;
            } else {
                Logger.log("Expense report number " + expenseNumber + " has already been resolved. Exception thrown.", LogLevel.LOCKED);
                throw new ResponseStatusException(HttpStatus.LOCKED, "Only pending expense reports can be denied.");
            }
        } else{
            Logger.log("Expense report " + expenseNumber + " not found. Exception thrown.", LogLevel.NOT_FOUND);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The requested expense report could not be found. " +
                    "Please check your report number and try again.");
        }
    }

    @Override
    public String deleteExpense(int expenseNumber) {
        Optional<Expense> oe = expenseRepo.findById(expenseNumber);
        if(oe.isPresent()){
            Expense expense = oe.get();
            if(expense.getStatus().equals("Pending")){
                expenseRepo.delete(expense);
                return "Expense Report was Deleted Successfully";
            } else {
                return "Only pending expense reports can be deleted.";
            }
        } else{
            Logger.log("Expense report " + expenseNumber + " not found. Exception thrown.", LogLevel.NOT_FOUND);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The requested expense report could not be found. " +
                    "Please check your report number and try again.");
        }
    }
}
