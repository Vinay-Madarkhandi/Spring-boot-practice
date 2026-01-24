package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("anotherTodoService")
public class AnotherTodoService implements TodoService{

    private final TodoRepository todoRepository;

    public AnotherTodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public Todo createTodo(Todo todo){
        Todo saveTodo = todoRepository.save(todo);
        log.info("Todo Created");
        return saveTodo;
    }

}
