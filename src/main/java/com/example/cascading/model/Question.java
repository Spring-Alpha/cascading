package com.example.cascading.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "question")
@Getter
@Setter
@NoArgsConstructor
public class Question {
    @Id
    private Integer id;
    private String question;
    @OneToMany(mappedBy = "question", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    @JoinColumn(name = "question_id")
    private List<Answer> answers;
}
