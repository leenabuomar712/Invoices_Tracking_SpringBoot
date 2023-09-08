package com.example.task2.task2.service;

import com.example.task2.task2.data.entities.User;
import com.example.task2.task2.data.Repositories.UserRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User createUser(User post) {
        return userRepository.save(post);
    }
    public User updateUser(long id, User userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User"));

        user.setName(userRequest.getName());
        user.setUsername(userRequest.getUsername());
        user.setRoles(userRequest.getRoles());
        user.setEmail(userRequest.getEmail());

        return userRepository.save(user);
    }
    public boolean deleteUser(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User"));

        userRepository.delete(user);
        return false;
    }
    public User getUserById(long id) {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new ResourceNotFoundException("User");
        }
    }
    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
