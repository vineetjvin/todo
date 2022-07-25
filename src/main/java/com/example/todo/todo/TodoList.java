package com.example.todo.todo;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class TodoList {
    @Id
    @SequenceGenerator(
            name= "todo_list_sequence",
            sequenceName = "todo_list_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "todo_list_sequence"
    )
    private Long id;
    private String title;
    private LocalDate dueDate;
    @Transient
    private int daysRemaining;

    public TodoList() {
    }

    public int getDaysRemaining() {
        return Period.between(LocalDate.now(), dueDate).getDays();
    }

    public void setDaysRemaining(int daysRemaining) {
        this.daysRemaining = daysRemaining;
    }

    public TodoList(Long id, String title, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.dueDate = dueDate;
    }

    public TodoList(String title, LocalDate dueDate) {
        this.title = title;
        this.dueDate = dueDate;
    }

    public TodoList(String title) {
        this.title = title;
    }

    public TodoList(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "TodoList{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", dueDate=" + dueDate +
                '}';
    }
}
