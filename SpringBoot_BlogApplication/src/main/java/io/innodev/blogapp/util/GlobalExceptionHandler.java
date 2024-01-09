package io.innodev.blogapp.util;

import io.innodev.blogapp.entity.Message;
import io.innodev.blogapp.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Message> resourceNotFoundExceptionMessage(ResourceNotFoundException resourceNotFoundException) {
        String message = resourceNotFoundException.getMessage();
        Message message1 = new Message(message, false, new Date().toString());
        return new ResponseEntity<>(message1, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> responseMap = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();
                    Message message1 = new Message(message, false, new Date().toString());
                    responseMap.put(fieldName, message1.getMessages());
                });
        return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
    }
}
