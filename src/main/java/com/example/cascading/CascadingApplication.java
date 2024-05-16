package com.example.cascading;

import com.example.cascading.model.Answer;
import com.example.cascading.model.Question;
import com.example.cascading.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class CascadingApplication implements CommandLineRunner {

	private final QuestionRepository questionRepository;

	public static void main(String[] args) {
		SpringApplication.run(CascadingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
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
}
