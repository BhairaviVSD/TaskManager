package com.Project.TaskManager.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.Project.TaskManager.models.Task;

public class Tasks {
    public String getUkrainianFormattedDeadline(Task task) {
        Locale ukrainianLocale = new Locale("uk");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM", ukrainianLocale);
        return task.getDeadline().format(formatter);
    }
    public static List<Task> todayTasks(Iterable<Task> tasks){
        LocalDate today = LocalDate.now();
        List<Task> todayTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDeadline().equals(today) && !task.isDone()) {
                todayTasks.add(task);
            }
        }

        return todayTasks;
    }
    public static List<Task> laterTasks(Iterable<Task> tasks) {
        LocalDate today = LocalDate.now();
        List<Task> laterTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDeadline().isAfter(today) && !task.isDone()) {
                laterTasks.add(task);
            }
        }

        return laterTasks;
    }
    public static List<Task> missedTasks(Iterable<Task> tasks) {
        LocalDate today = LocalDate.now();
        List<Task> missedTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDeadline().isBefore(today) && !task.isDone()) {
                missedTasks.add(task);
            }
        }

        return missedTasks;
    }

    public static List<Task> priorityTasks(Iterable<Task> tasks, int prior) {
        List<Task> priorityTasks = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (Task task : tasks) {
            if (task.getPriority() == prior && task.getDeadline().equals(today) && !task.isDone()) {
                priorityTasks.add(task);
            }
        }

        return priorityTasks;
    }
    public static List<Task> doneTasks(Iterable<Task> tasks) {
        List<Task> doneTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.isDone()) {
                doneTasks.add(task);
            }
        }

        return doneTasks;
    }
}
