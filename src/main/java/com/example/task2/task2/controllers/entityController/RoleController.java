package com.example.task2.task2.controllers.entityController;

import com.example.task2.task2.controllers.DTOs.RoleDTO;
import com.example.task2.task2.controllers.DTOs.UserDTO;
import com.example.task2.task2.data.entities.Role;
import com.example.task2.task2.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<UserDTO> getAllRoles() {
        return roleService.getAllRoles().stream().map(role -> modelMapper.map(role, UserDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable(name = "id") Long id) {
        Role role = (Role) roleService.getRoleById(id);

        // convert entity to DTO
        RoleDTO roleResponse = modelMapper.map(role, RoleDTO.class);
        return ResponseEntity.ok().body(roleResponse);
    }

    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {

        // convert DTO to entity
        Role roleRequest = modelMapper.map(roleDTO, Role.class);

        Role role = (Role) roleService.createRole((Role) roleRequest);
        // convert entity to DTO
        RoleDTO roleResponse = modelMapper.map(role, RoleDTO.class);

        return new ResponseEntity<RoleDTO>(roleResponse, HttpStatus.CREATED);
    }
    // change the request for DTO
    // change the response for DTO
    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable long id, @RequestBody RoleDTO roleDTO) {

        // convert DTO to Entity
        Role roleRequest = modelMapper.map(roleDTO, Role.class);

        Role role = (Role) roleService.updateRole(id, (Role) roleRequest);

        // entity to DTO
        RoleDTO roleResponse = modelMapper.map(role, RoleDTO.class);
        return ResponseEntity.ok().body(roleResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable(name = "id") Long id) {
        boolean deleted = roleService.deleteRole(id);
        if (deleted) {
            return ResponseEntity.ok("Role deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found");
        }
    }


}
