package br.com.fiap.epictask.controller;

import br.com.fiap.epictask.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.fiap.epictask.repositories.TaskRepository;

@Controller
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private TaskRepository repository;
	
	@GetMapping
	public String index() {
		return "tasks";
	}
	
	@GetMapping("/new")
	public String create() {
		return "task-form";
	}
		
	@PostMapping
	public String save(Task task) {
			
	    repository.save(task); 
	    repository.findById(task.getId());
		System.out.println("salvando tarefa..." + task);
		return "tasks";
	}
}
