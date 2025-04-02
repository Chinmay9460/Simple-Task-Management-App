package com.CB.TaskManager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "tasks";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new Task());
        return "task-form";
    }

    @PostMapping
    public String saveTask(@ModelAttribute Task task) {
        taskService.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        taskService.findById(id).ifPresent(task -> model.addAttribute("task", task));
        return "task-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return "redirect:/tasks";
    }
}
