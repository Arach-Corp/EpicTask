package br.com.fiap.epictask.controller;

import br.com.fiap.epictask.entities.Task;
import br.com.fiap.epictask.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRepository repository;


    @GetMapping
    public String index(@PageableDefault Pageable pageable, Model model) {
        final Page<Task> tasks = repository.findAll(pageable);
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("task", new Task());
        return "task-form";
    }

    @PostMapping
    public String save(@Valid Task task, BindingResult result) {
        if (result.hasErrors()) {
            return "task-form";
        }
        repository.save(task);
        repository.findById(task.getId());
        System.out.println("salvando tarefa..." + task);
        return "redirect:task";
    }
}
