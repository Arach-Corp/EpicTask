package br.com.fiap.epictask.services;

import br.com.fiap.epictask.entities.Task;
import br.com.fiap.epictask.entities.User;
import br.com.fiap.epictask.services.exceptions.DeleteResourceException;
import br.com.fiap.epictask.services.exceptions.ResourceNotFoundException;
import br.com.fiap.epictask.services.exceptions.UpdateResourceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    Page<Task> findAll(Pageable pageable);

    Task findById(Long id) throws ResourceNotFoundException, UpdateResourceException;

    Task save(Task task);

    void update(Long id, Task data) throws ResourceNotFoundException, UpdateResourceException;

    void  delete(Long id) throws ResourceNotFoundException, DeleteResourceException;

    List<Task> findAllByTitleAndDescription(String title, String description);

    Page<Task> findByTitleLike(String s, Pageable pageable);
}
