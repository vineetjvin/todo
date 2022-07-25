package com.example.todo.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoList> getTodoLists() {
        return todoRepository.findAll();
    }

    public void addNewTodoList(TodoList todoList) {
        System.out.println(todoList);
        Optional<TodoList> todoListOptional = todoRepository.findTodoListByTitle(todoList.getTitle());
        if (todoListOptional.isPresent()) {
            throw new IllegalStateException("List already exists");
        }
        todoRepository.save(todoList);
    }


    public void deleteTodoList(Long todoListId) {
        boolean exists = todoRepository.existsById(todoListId);
        if (!exists) {
            throw new IllegalStateException("List with id: " + todoListId + " doesn't exist!");
        }
        todoRepository.deleteById(todoListId);
    }

    @Transactional
    public void updateTodoList(Long todoListId, String title, LocalDate dueDate) {
        TodoList todoList = todoRepository.findById(todoListId)
                .orElseThrow(() -> new IllegalStateException(
                        "List with id: " + todoListId + " doesn't exist!"));
        if (title != null && title.length() > 0 && !Objects.equals(todoList.getTitle(), title)) {
            Optional<TodoList> todoListOptional = todoRepository.findTodoListByTitle(title);
            if (todoListOptional.isPresent()) {
                throw new IllegalStateException("List already exists!!!");
            }
            todoList.setTitle(title);
        }
        if (dueDate != null && !Objects.equals(todoList.getDueDate(), dueDate)) {
            todoList.setDueDate(dueDate);
        }
    }
}
