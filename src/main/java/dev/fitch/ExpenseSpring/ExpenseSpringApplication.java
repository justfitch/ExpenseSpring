package dev.fitch.ExpenseSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "dev.fitch")
@EntityScan(basePackages = "dev.fitch.entities")
@EnableJpaRepositories(basePackages = "dev.fitch.repos")
public class ExpenseSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseSpringApplication.class, args);
	}

}
