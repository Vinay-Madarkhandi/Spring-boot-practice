package com.example.demo.controller;

import org.springframework.stereotype.Service;

@Service("anotherTodoService")
public class AnotherTodoService implements TodoService{
    @Override
    public String doSomething() {
        return "This is from AnotherService";
    }
}
