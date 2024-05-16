package com.Project.TaskManager.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    @ForeignKey
//    I can manage the relation between the task and the user entity either strictly via database
//    or strictly via spring
    private String name;
    private LocalDate deadline;
    private int priority;
    private String note;
    private boolean done;

    @ManyToOne
    private User user;

    public Task(String name, LocalDate deadline, int priority, String note, User user) {
        this.name = name;
        this.deadline = deadline;
        this.priority = priority;
        this.note = note;
        this.done = false;
        this.user = user;
    }

    public Task() {
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }

//    public String getUkrainianFormattedDeadline() {
//        Locale ukrainianLocale = new Locale("uk");
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM", ukrainianLocale);
//        return getDeadline().format(formatter);
//    }
    public String getEnglishFormattedDeadline() {
        Locale englishLocale = new Locale("en");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d MMM", englishLocale);
        return getDeadline().format(formatter);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return Objects.equals(this.id, task.id);
    }
}
