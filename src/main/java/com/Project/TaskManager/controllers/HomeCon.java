package com.Project.TaskManager.controllers;

import com.Project.TaskManager.models.Task;
import com.Project.TaskManager.models.User;
import com.Project.TaskManager.repo.TaskRepository;
import com.Project.TaskManager.util.Tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class HomeCon {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/home")
        public String home (Model model){
        Iterable<Task> tasks;
        try {
            tasks = MainCon.getUser().getTasks();
        } catch (NullPointerException e){
            return "redirect:/log-in";
        }
        model.addAttribute("todayTasks", Tasks.todayTasks(tasks));
        model.addAttribute("laterTasks", Tasks.laterTasks(tasks));
        model.addAttribute("activePage", "home");
        model.addAttribute("userName", MainCon.getUser().getName());
        return "pages/home";
    }
    @GetMapping("/home/add")
    public String taskAdd(Model model){
        return "actions/task-add";
    }

    @PostMapping("home/add")
    @Transactional
    public String taskPostAdd(@RequestParam String name,
                              @RequestParam LocalDate deadline,
                              @RequestParam int priority,
                              @RequestParam String note,
                              Model model){
        Task task = new Task(name, deadline, priority, note, MainCon.getUser());
        taskRepository.save(task);
        MainCon.getUser().addTask(task);
        return "redirect:/home";
    }
    @GetMapping("/home/{id}")
    public String taskUpdate(@PathVariable(value = "id") long id,
                             Model model){
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty())
            return "redirect:/home";
        model.addAttribute("task", task.get());
        return "actions/task-update";
    }
    @PostMapping("home/{id}")
    public String taskPostUpdate(@PathVariable(value = "id") long id,
                                 @RequestParam String name,
                                 @RequestParam LocalDate deadline,
                                 @RequestParam int priority,
                                 @RequestParam String note,
                                 Model model){
        Task task = taskRepository.findById(id).orElseThrow();
        task.setName(name);
        task.setDeadline(deadline);
        task.setPriority(priority);
        task.setNote(note);
        taskRepository.save(task);
        MainCon.getUser().updateTask(task);
        return "redirect:/home";
    }
    @PostMapping("home/{id}/delete")
    @Transactional
    public String taskPostDelete(@PathVariable(value = "id") long id,
                                 Model model){
        taskRepository.deleteTaskById(id);
        MainCon.getUser().deleteTaskById(id);
        return "redirect:/home";
    }
    @PostMapping("home/{id}/complete")
    @Transactional
    public String taskPostComplete(@PathVariable(value = "id") long id,
                                 Model model){
        Task task = taskRepository.findById(id).orElseThrow();
        task.setDone(true);
        taskRepository.save(task);
        MainCon.getUser().updateTask(task);
        return "redirect:/home";
    }
}

