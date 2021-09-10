package br.com.fiap.epictask.services;

import br.com.fiap.epictask.services.exceptions.DeleteResourceException;
import br.com.fiap.epictask.services.exceptions.ResourceNotFoundException;
import br.com.fiap.epictask.entities.User;
import br.com.fiap.epictask.services.exceptions.UpdateResourceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<User> findAll(Pageable pageable);

    User findById(Long id) throws ResourceNotFoundException, UpdateResourceException;

    User save(User user);

    void update(Long id, User data) throws ResourceNotFoundException;

    void  delete(Long id) throws ResourceNotFoundException, DeleteResourceException;

}
