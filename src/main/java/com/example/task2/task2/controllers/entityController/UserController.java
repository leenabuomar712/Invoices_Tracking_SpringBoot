package com.example.task2.task2.controllers.entityController;

import com.example.task2.task2.NotFoundException;
import com.example.task2.task2.controllers.DTOs.UserDTO;
import com.example.task2.task2.service.UserService;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private ModelMapper modelMapper;

    private final UserService userService;
    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers().stream().map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") Long id) {
        User user = (User) userService.getUserById(id);

        if (user != null){
            UserDTO userResponse = modelMapper.map(user, UserDTO.class);
            return ResponseEntity.ok().body(userResponse);
        }
        else {
            throw new NotFoundException("User not found with id: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {

        // convert DTO to entity
        User userRequest = modelMapper.map(userDTO, User.class);

        User user = (User) userService.createUser((com.example.task2.task2.data.entities.User) userRequest);

        // convert entity to DTO
        UserDTO userResponse = modelMapper.map(user, UserDTO.class);

        return new ResponseEntity<UserDTO>(userResponse, HttpStatus.CREATED);
    }

    // change the request for DTO
    // change the response for DTO
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable long id, @RequestBody UserDTO userDto) {

        // convert DTO to Entity
        User userRequest = modelMapper.map(userDto, User.class);

        User user = (User) userService.updateUser(id, (com.example.task2.task2.data.entities.User) userRequest);
        // entity to DTO
        UserDTO userResponse = modelMapper.map(user, UserDTO.class);
        return ResponseEntity.ok().body(userResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

}
