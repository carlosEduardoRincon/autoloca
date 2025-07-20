package com.example.autoloca.autoloca.controllers.handlers;

import com.example.autoloca.autoloca.dtos.ResourceNotFoundDTO;
import com.example.autoloca.autoloca.dtos.ValidationErrorDTO;
import com.example.autoloca.autoloca.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundDTO> handlerResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status.value()).body(new ResourceNotFoundDTO(resourceNotFoundException.getMessage(), status.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handlerMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        var status = HttpStatus.BAD_REQUEST;
        List<String> errors = new ArrayList<>();
        for (var error:  methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        return ResponseEntity.status(status.value()).body(new ValidationErrorDTO(errors, status.value()));
    }
}
