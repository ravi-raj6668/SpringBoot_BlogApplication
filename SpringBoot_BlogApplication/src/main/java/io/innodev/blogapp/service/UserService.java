package io.innodev.blogapp.service;

import io.innodev.blogapp.config.Constant;
import io.innodev.blogapp.config.ModelMapperUtil;
import io.innodev.blogapp.entity.Role;
import io.innodev.blogapp.entity.User;
import io.innodev.blogapp.exceptions.ResourceNotFoundException;
import io.innodev.blogapp.payloads.UserDTO;
import io.innodev.blogapp.repository.RoleRepository;
import io.innodev.blogapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final ModelMapperUtil modelMapperUtil;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapperUtil modelMapperUtil, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.modelMapperUtil = modelMapperUtil;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapperUtil.modelMapper().map(userDTO, User.class);

        //encoded the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //roles
        Role role = roleRepository.findById(Constant.ROLE_ADMIN).get();
        user.getRoles().add(role);
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
        User updatedUser = userRepository.save(user);
        return userToDTO(updatedUser);
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

    @Override
    public UserDTO registerNewUser(UserDTO userDTO) {
        User user = modelMapperUtil.modelMapper().map(userDTO, User.class);

        //encoded the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //roles
        Role role = roleRepository.findById(Constant.ROLE_TEST_USER).get();
        user.getRoles().add(role);
        User newsUser = userRepository.save(user);
        return modelMapperUtil.modelMapper().map(newsUser, UserDTO.class);
    }

    public User dtoToUser(UserDTO userDTO) {
        return modelMapperUtil.modelMapper().map(userDTO, User.class);
    }

    public UserDTO userToDTO(User user) {
        return modelMapperUtil.modelMapper().map(user, UserDTO.class);
    }
}
