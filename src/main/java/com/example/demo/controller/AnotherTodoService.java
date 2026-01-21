package com.example.demo.controller;

import org.springframework.stereotype.Service;

@Service("anotherTodoService")
public class AnotherTodoService implements TodoService{
    @TimeTeller
    @Override
    public String doSomething() {

        return "This is from AnotherService";
    }
}
