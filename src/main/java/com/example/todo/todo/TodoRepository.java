package com.example.todo.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository
        extends JpaRepository<TodoList, Long> {


    Optional<TodoList> findTodoListByTitle(String title);
}
