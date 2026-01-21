package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController("/")
public class TodoController {
    private List<Todo> todoList = new ArrayList<>();

    private TodoService todoService;

    private TodoService todoService2;

    public TodoController(
            @Qualifier("fakeTodoService") TodoService todoService,
            @Qualifier("anotherTodoService") TodoService todoService2
            ) {
        this.todoService = todoService;
        this.todoService2 = todoService2;
        todoList.add(new Todo(1, true, "Wake up" , 1));
        todoList.add(new Todo(2, false, "Boil egg" , 2));

    }

    @TimeTeller
    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getAllTodos(){
        System.out.println(todoService.doSomething());
        System.out.println(todoService2.doSomething());
        return ResponseEntity.ok(todoList);
    }

    @PostMapping("/todos")
    public ResponseEntity<Todo> addTodo(@RequestBody Todo newTodo){
        todoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
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
