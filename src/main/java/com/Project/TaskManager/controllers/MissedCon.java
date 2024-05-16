package com.Project.TaskManager.controllers;
import com.Project.TaskManager.models.Task;
import com.Project.TaskManager.repo.TaskRepository;
import com.Project.TaskManager.util.Tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MissedCon {
    @GetMapping("/missed")
    public String missed(Model model){
        Iterable<Task> tasks = MainCon.getUser().getTasks();
        model.addAttribute("missedTasks", Tasks.missedTasks(tasks));
        model.addAttribute("activePage", "missed");
        model.addAttribute("userName", MainCon.getUser().getName());
        return "pages/missed";
    }
}
