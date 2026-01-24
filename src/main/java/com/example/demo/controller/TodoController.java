package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class TodoController {


    private List<Todo> todoList = new ArrayList<>();

    private TodoService todoService;


    public TodoController(
            @Qualifier("anotherTodoService") TodoService todoService
            ) {
        this.todoService = todoService;

    }

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getAllTodos(){
        return ResponseEntity.ok(todoList);
    }

    @PostMapping("/todos")
    public ResponseEntity<Todo> addTodo(@RequestBody Todo newTodo){
        Todo todo = todoService.createTodo(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(todo);
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<Object> getTodoById(@PathVariable int id ){
        for(Todo todo : todoList){
            if(todo.getId() == id){
                return ResponseEntity.ok(todo);
            }
        }
        return ResponseEntity.status(404).body(new ResponseMessage("Not found"));
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<ResponseMessage> removeTodo(@PathVariable int id) {
        todoList.removeIf(todo -> todo.getId() == id);
        return ResponseEntity.status(200).body(new ResponseMessage("Deleted Successfully"));
    }

    @PatchMapping("/todos/{id}")
    public ResponseEntity<Object> updateTodo(@PathVariable int id , @RequestBody Todo title){
        try {
            for(Todo todo : todoList){
                if(todo.getId() == id){
                    todo.setTitle(title.getTitle());
                    return ResponseEntity.ok(todo);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Updating todo not found"));
    }
}
