package com.Project.TaskManager.controllers;
import com.Project.TaskManager.models.Task;
import com.Project.TaskManager.repo.TaskRepository;
import com.Project.TaskManager.util.Tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyDayCon {
    @GetMapping("/my-day")
    public String myday(Model model){
        Iterable<Task> tasks = MainCon.getUser().getTasks();
        model.addAttribute("firstPriorityTasks", Tasks.priorityTasks(tasks, 1));
        model.addAttribute("secondPriorityTasks", Tasks.priorityTasks(tasks, 2));
        model.addAttribute("thirdPriorityTasks", Tasks.priorityTasks(tasks, 3));
        model.addAttribute("activePage", "my-day");
        model.addAttribute("userName", MainCon.getUser().getName());
        return "pages/my-day";
    }
}
