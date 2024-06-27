package com.example.cascading;

import com.example.cascading.model.*;
import com.example.cascading.repository.CustomerRepository;
import com.example.cascading.repository.PeopleRepository;
import com.example.cascading.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class CascadingApplication implements CommandLineRunner {

	private final QuestionRepository questionRepository;
	private final CustomerRepository customerRepository;
	private final PeopleRepository peopleRepository;

	public static void main(String[] args) {
		SpringApplication.run(CascadingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//One to many / Many to one cascade testing
		oneSideCascade();
		//twoSideCascade();
		//elementCollection();
	}

	//@Transactional
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

		//question.setAnswers(List.of(answer1, answer2, answer3));
		question.setAnswers(new ArrayList<>());

		questionRepository.save(question);
		//questionRepository.deleteById(1);
	}

	private void twoSideCascade() {
		Customer customer1 = new Customer(1, "Alif");
		Customer customer2 = new Customer(2, "Babu");

		Product product1 = new Product(1, "Iphone1");
		Product product2 = new Product(2, "Iphone2");
		Product product3 = new Product(3, "Iphone3");
		Product product4 = new Product(4, "Iphone4");
		Product product5 = new Product(5, "Iphone55");
		Product product6 = new Product(6, "Iphone56");
		Product product7 = new Product(7, "Iphone57");

		customer1.setProducts(Set.of(product1, product3, product4));
		customer2.setProducts(Set.of(product2, product3, product5, product6, product7));

		try {
			customerRepository.saveAll(Set.of(customer1, customer2));
		} catch (Exception e){
			log.info("{} cause of the error, persistence is not possible", e.getMessage());
		}
		customerRepository.deleteById(2);
	}

	private void elementCollection() {
		People people = new People();
		people.setId(1);
		people.setName("Peo");
		people.setPhoneNumbers(Set.of("01767310339", "01886310339"));

		People people2 = new People();
		people2.setId(2);
		people2.setName("Peo2");
		people2.setPhoneNumbers(Set.of("01767310339", "01886310339"));

		peopleRepository.save(people);
		//peopleRepository.deleteById(1);
	}
}
