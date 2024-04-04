package no.ntnu.idatt2105.quizbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class QuizBankWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizBankWebApplication.class, args);
	}
}


