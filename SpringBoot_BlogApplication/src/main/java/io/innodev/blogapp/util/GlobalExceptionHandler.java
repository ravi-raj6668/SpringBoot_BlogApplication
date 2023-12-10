package io.innodev.blogapp.util;

import io.innodev.blogapp.entity.Message;
import io.innodev.blogapp.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Message> resourceNotFoundExceptionMessage(ResourceNotFoundException resourceNotFoundException){
        String message = resourceNotFoundException.getMessage();
        Message message1 = new Message(message, false, new Date().toGMTString());
        return new ResponseEntity<Message>(message1, HttpStatus.NOT_FOUND);
    }
}
