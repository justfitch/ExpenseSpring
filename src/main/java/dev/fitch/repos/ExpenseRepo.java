package dev.fitch.repos;

import dev.fitch.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Integer> {

    List<Expense> findByStatus (String status);
    List<Expense> findExpensesByEmployeeId (int employeeId);

}
