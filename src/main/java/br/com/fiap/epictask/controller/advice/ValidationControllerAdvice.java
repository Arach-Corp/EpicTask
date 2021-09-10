package br.com.fiap.epictask.controller.advice;

import br.com.fiap.epictask.controller.advice.errors.StandardError;
import br.com.fiap.epictask.controller.advice.errors.ValidationFieldError;
import br.com.fiap.epictask.services.exceptions.DeleteResourceException;
import br.com.fiap.epictask.services.exceptions.ResourceNotFoundException;
import br.com.fiap.epictask.services.exceptions.UpdateResourceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ValidationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest req) {
        log.info("erro de validação aconteceu");

        final HttpStatus status = HttpStatus.BAD_REQUEST;
        final List<Object> list = new ArrayList<>();
        final List<FieldError> errors = e.getBindingResult().getFieldErrors();
        errors.forEach(error -> {
            list.add(new ValidationFieldError(
                    error.getField(),
                    error.getDefaultMessage()));
        });
        final StandardError error = StandardError.builder()
                .code(status.value())
                .message("Validation error")
                .path(req.getRequestURI())
                .errors(list)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFoundException(ResourceNotFoundException e, HttpServletRequest req) {
        final HttpStatus status = HttpStatus.NOT_FOUND;
        final StandardError error = StandardError.builder()
                .code(status.value())
                .message("Resource not fpund")
                .path(req.getRequestURI())
                .timestamp(LocalDateTime.now())
                .errors(Collections.singletonList(e.getMessage()))
                .build();
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DeleteResourceException.class)
    public ResponseEntity<StandardError> deleteResourceException(DeleteResourceException e, HttpServletRequest req) {
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        final StandardError error = StandardError.builder()
                .code(status.value())
                .message("Cannot delete resource")
                .path(req.getRequestURI())
                .timestamp(LocalDateTime.now())
                .errors(Collections.singletonList(e.getMessage()))
                .build();
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(UpdateResourceException.class)
    public ResponseEntity<StandardError> updateResourceException(UpdateResourceException e, HttpServletRequest req) {
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        final StandardError error = StandardError.builder()
                .code(status.value())
                .message("Cannot update resource")
                .path(req.getRequestURI())
                .timestamp(LocalDateTime.now())
                .errors(Collections.singletonList(e.getMessage()))
                .build();
        return ResponseEntity.status(status).body(error);
    }
}
