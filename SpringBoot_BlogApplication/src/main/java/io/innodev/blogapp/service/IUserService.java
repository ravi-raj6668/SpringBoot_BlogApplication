package io.innodev.blogapp.service;

import io.innodev.blogapp.entity.UserDTO;

import java.util.List;

public interface IUserService {

    public UserDTO createUser(UserDTO userDTO);

    public UserDTO updateUser(UserDTO userDTO, Integer userId);

    public UserDTO getUserById(Integer id);

    List<UserDTO> getAllUsers();

    public void deleteUser(Integer usedId);
}
