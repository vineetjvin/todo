package com.example.todo.todo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class TodoConfig {

    @Bean
    CommandLineRunner commandLineRunner(TodoRepository repository) {
        return args -> {
          TodoList shopping =  new TodoList(
                    12L,
                    "Shopping",
                    LocalDate.of(
                            2022,
                            JULY,
                            31)
            );
            TodoList travel =   new TodoList(
                    12L,
                    "Travel",
                    LocalDate.of(
                            2022,
                            SEPTEMBER,
                            30)
            );

            repository.saveAll(
                    List.of(shopping,travel)
            );

        };
    }
}
