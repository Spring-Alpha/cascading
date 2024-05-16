package com.example.cascading;

import com.example.cascading.model.Answer;
import com.example.cascading.model.Customer;
import com.example.cascading.model.Product;
import com.example.cascading.model.Question;
import com.example.cascading.repository.CustomerRepository;
import com.example.cascading.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class CascadingApplication implements CommandLineRunner {

	private final QuestionRepository questionRepository;
	private final CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(CascadingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//One to many / Many to one cascade testing
		//oneSideCascade();
		twoSideCascade();
	}

	private void oneSideCascade() {
		Question question = new Question();
		question.setId(1);
		question.setQuestion("What is cascading...?");

		Answer answer1 = new Answer(1, "It is a hibernate concept", question);
		Answer answer2 = new Answer(2, "It is a hibernate concept1", question);
		Answer answer3 = new Answer(3, "It is a hibernate concept2", question);

//		Answer answer1 = new Answer(1, "It is a hibernate concept");
//		Answer answer2 = new Answer(2, "It is a hibernate concept1");
//		Answer answer3 = new Answer(3, "It is a hibernate concept2");

		question.setAnswers(List.of(answer1, answer2, answer3));

		questionRepository.save(question);
		questionRepository.deleteById(1);
	}

	private void twoSideCascade() {
		Customer customer1 = new Customer(1, "Alif");
		Customer customer2 = new Customer(2, "Babu");

		Product product1 = new Product(1, "Iphone1");
		Product product2 = new Product(2, "Iphone2");
		Product product3 = new Product(3, "Iphone3");
		Product product4 = new Product(4, "Iphone4");

		customer1.setProducts(Set.of(product1, product3, product4));
		customer2.setProducts(Set.of(product2, product3));

		customerRepository.saveAll(Set.of(customer1, customer2));
		customerRepository.deleteById(1);
	}
}
