package br.com.fiap.epictask.services.impl;

import br.com.fiap.epictask.entities.User;
import br.com.fiap.epictask.repositories.UserRepository;
import br.com.fiap.epictask.services.UserService;
import br.com.fiap.epictask.services.exceptions.DeleteResourceException;
import br.com.fiap.epictask.services.exceptions.ResourceNotFoundException;
import br.com.fiap.epictask.services.exceptions.UpdateResourceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findById(Long id) throws ResourceNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User resource not found for id:" + id));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void update(Long id, User data) throws ResourceNotFoundException, UpdateResourceException {
        try {
            User user = findById(id);
            populate(data, user);
            userRepository.save(user);
        } catch (ResourceNotFoundException e) {
            log.info(e.getMessage());
            throw e;
        } catch (Exception e){
            log.error(e.getMessage());
            throw new UpdateResourceException("Cannot be update User resource with id:"+ id);
        }
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException, DeleteResourceException {
        try {
            userRepository.delete(findById(id));
        } catch (ResourceNotFoundException e) {
            log.info(e.getMessage());
            throw e;
        } catch (Exception e){
            log.error(e.getMessage());
            throw new DeleteResourceException("Cannot be delete User resource with id:"+ id);
        }
    }

    protected void populate(User source, User target) {
        target.setName(source.getName());
        target.setEmail(source.getEmail());
        target.setPassword(source.getPassword());
    }

}
