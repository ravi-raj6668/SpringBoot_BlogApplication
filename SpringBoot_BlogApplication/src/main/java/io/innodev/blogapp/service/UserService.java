package io.innodev.blogapp.service;

import io.innodev.blogapp.config.UserModelMapper;
import io.innodev.blogapp.entity.User;
import io.innodev.blogapp.entity.UserDTO;
import io.innodev.blogapp.exceptions.ResourceNotFoundException;
import io.innodev.blogapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final UserModelMapper userModelMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserModelMapper userModelMapper) {
        this.userRepository = userRepository;
        this.userModelMapper = userModelMapper;
    }


    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = dtoToUser(userDTO);
        User saveUser = userRepository.save(user);
        return userToDTO(saveUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Data", "User", userId));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());

        return userToDTO(user);
    }

    @Override
    public UserDTO getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("UserData", "id", id));
        return userToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(this::userToDTO).toList();
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        userRepository.delete(user);
    }

    public User dtoToUser(UserDTO userDTO) {
        return userModelMapper.modelMapper().map(userDTO, User.class);
    }

    public UserDTO userToDTO(User user) {
        return userModelMapper.modelMapper().map(user, UserDTO.class);
    }
}
