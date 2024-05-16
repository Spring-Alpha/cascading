package com.example.cascading.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    private Integer id;
    private String name;
    //@ManyToMany(mappedBy = "products")
    //private Set<Customer> customers;

    public Product(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
