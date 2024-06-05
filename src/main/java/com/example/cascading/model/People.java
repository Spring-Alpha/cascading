package com.example.cascading.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "people")
@Getter
@Setter
@NoArgsConstructor
public class People {
    @Id
    private Integer id;
    private String name;
    @ElementCollection
    private Set<String> phoneNumbers;
}
