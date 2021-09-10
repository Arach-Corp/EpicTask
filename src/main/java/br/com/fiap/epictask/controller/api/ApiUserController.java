package br.com.fiap.epictask.controller.api;

import br.com.fiap.epictask.controller.dto.UserDTO;
import br.com.fiap.epictask.controller.dto.UserForm;
import br.com.fiap.epictask.entities.User;
import br.com.fiap.epictask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/users")
public class ApiUserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserDTO>> getAll(@PageableDefault Pageable pageable) {
        final Page<User> users = userService.findAll(pageable);
        return ResponseEntity.ok(convertUserPage(users, pageable));
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserForm dto) {
        final User user = userService.save(dto.toUser());
        final URI uri = createURI("/{id}", user.getId());
        return ResponseEntity.created(uri).body(UserDTO.of(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        final User user = userService.findById(id);
        return ResponseEntity.ok(UserDTO.of(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody UserForm data) {
        userService.update(id, data.toUser());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    protected URI createURI(String path, Object... params) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path(path).buildAndExpand(params).toUri();
    }

    protected Page<UserDTO> convertUserPage(Page<User> page, Pageable pageable) {
        final List<UserDTO> content = page.getContent().stream().map(UserDTO::of).collect(Collectors.toList());;
        return new PageImpl<>(content, pageable, page.getTotalElements());
    }

}
