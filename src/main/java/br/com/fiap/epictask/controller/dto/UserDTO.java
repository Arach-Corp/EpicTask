package br.com.fiap.epictask.controller.dto;

import br.com.fiap.epictask.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updateTime;
    public static UserDTO of(User source) {
        return Objects.isNull(source)? null:UserDTO.builder()
                .id(source.getId())
                .name(source.getName())
                .email(source.getEmail())
                .createTime(source.getCreationTime())
                .updateTime(source.getUpdateTime())
                .build();
    }

}
