package com.example.cascading.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "answer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @Id
    private Integer id;
    private String answer;
    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    public Answer(Integer id, String answer) {
        this.id = id;
        this.answer = answer;
    }
}
