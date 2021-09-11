package br.com.fiap.epictask.controller.dto;

import br.com.fiap.epictask.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserForm {

    @NotBlank(message = "{user.form.name.empty}")
    private String name;

    @Email(message = "{user.form.email.valid}")
    @NotBlank(message = "{user.form.email.empty}")
    private String email;

    @Size(min = 8, message = "{user.form.password.length}")
    @NotBlank(message = "{user.form.password.empty}")
    private String password;

    public User toUser() {
        return User.builder()
                .name(getName())
                .email(getEmail())
                .password(getPassword())
                .build();
    }

}
