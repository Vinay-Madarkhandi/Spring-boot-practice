package com.example.demo.controller;

import org.springframework.stereotype.Service;

@Service("fakeTodoService")
public class FakeTodoService implements TodoService{

    @TimeTeller
    @Override
    public String doSomething() {
        return "Do something";
    }
}
