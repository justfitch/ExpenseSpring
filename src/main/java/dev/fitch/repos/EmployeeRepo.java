package dev.fitch.repos;

import dev.fitch.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}
