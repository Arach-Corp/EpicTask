package br.com.fiap.epictask.controller;

import br.com.fiap.epictask.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import br.com.fiap.epictask.repositories.TaskRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskRepository repository;


	@GetMapping
	public String index(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "perPage", defaultValue = "10") int perPage,
			Model model)
	{
		final Pageable pageable = PageRequest.of(page, perPage);
		Page<Task> tasks = repository.findAll(pageable);

		model.addAttribute("tasks", tasks);
		return "tasks";
	}
	
	@GetMapping("/new")
	public String create(Task task) {
		return "task-form";
	}
		
	@PostMapping
	public String save(@Valid Task task, BindingResult result) {

		if (result.hasErrors()){
			return "task-form";
		}
	    repository.save(task); 
	    repository.findById(task.getId());
		System.out.println("salvando tarefa..." + task);
		return "redirect:task";
	}
}
