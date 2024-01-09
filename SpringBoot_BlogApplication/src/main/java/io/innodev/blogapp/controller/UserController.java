package io.innodev.blogapp.controller;

import io.innodev.blogapp.entity.Message;
import io.innodev.blogapp.payloads.UserDTO;
import io.innodev.blogapp.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/blog-app/users")
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    //create user
    @GetMapping("/test")
    public String applicationStatus() {
        return "Application started successfully";
    }

    @PostMapping("/test-request")
    public ResponseEntity<String> testPostRequest() {
        return ResponseEntity.ok("POST request successful");
    }
    @PostMapping(value = "/createUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }
    // update user

    @PutMapping(value = "/updateUser/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Integer id) {
        UserDTO updatedUser = userService.updateUser(userDTO, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    //delete user
    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<Message> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(new Message("User Deleted successfully with id : " + userId, true, new Date().toString()), HttpStatus.OK);
    }

    //getUser

    @GetMapping(value = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getAllUser() {
        List<UserDTO> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping(value = "/getUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id) {
        UserDTO user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
