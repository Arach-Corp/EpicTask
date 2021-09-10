package br.com.fiap.epictask.controller.advice.errors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StandardError {

    private int code;
    private String message;
    private String path;
    private List<Object> errors;
    private LocalDateTime timestamp;

}
