package br.com.fiap.epictask.services.impl;

import br.com.fiap.epictask.entities.Task;
import br.com.fiap.epictask.entities.User;
import br.com.fiap.epictask.repositories.TaskRepository;
import br.com.fiap.epictask.services.TaskService;
import br.com.fiap.epictask.services.exceptions.DeleteResourceException;
import br.com.fiap.epictask.services.exceptions.ResourceNotFoundException;
import br.com.fiap.epictask.services.exceptions.UpdateResourceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DefaultTaskService implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Page<Task> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @Override
    public Task findById(Long id) throws ResourceNotFoundException, UpdateResourceException {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task resource not found for id:" + id));
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void update(Long id, Task task) throws ResourceNotFoundException, UpdateResourceException {
        try {
            Task entity = findById(id);
            populate(task, entity);
            taskRepository.save(entity);
        } catch (ResourceNotFoundException e) {
            log.info(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new UpdateResourceException("Cannot update Task resource with id:" + id);
        }
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException, DeleteResourceException {
        try {
            taskRepository.delete(findById(id));
        } catch (ResourceNotFoundException e) {
            log.info(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new DeleteResourceException("Cannot be delete Task resource with id:" + id);
        }
    }

    @Override
    public List<Task> findAllByTitleAndDescription(String title, String description) {
        return taskRepository.findAllByTitleAndDescription(title, description);
    }

    @Override
    public Page<Task> findByTitleLike(String title, Pageable pageable) {
        return taskRepository.findByTitleLike(title, pageable);
    }

    protected void populate(Task source, Task target) {
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        target.setPoints(source.getPoints());
    }

}
