package com.example.todo.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/todos")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<TodoList> getTodoLists() {
        return todoService.getTodoLists();
    }

    @PostMapping
    public void createNewList(@RequestBody TodoList todoList) {
        todoService.addNewTodoList(todoList);
    }

    @DeleteMapping(path = "{todoListId}")
    public void deleteTodoList(@PathVariable("todoListId") Long todoListId) {
        todoService.deleteTodoList(todoListId);

    }

    @PutMapping(path ="{todoListId}")
    public void updateTodoList(@PathVariable("todoListId") Long todoListId,
                               @RequestParam(required = false) String title,
                               @RequestParam(required= false)LocalDate dueDate){
        todoService.updateTodoList(todoListId, title, dueDate);
    }
}
