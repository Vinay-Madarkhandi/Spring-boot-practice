package com.example.demo.controller;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Todo {
    @Id
    private int id;
    private Boolean completed;
    private String title;
    private Integer userId;
}
