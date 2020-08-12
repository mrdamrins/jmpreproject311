package com.example.controller;

import com.example.model.Role;
import com.example.model.User;
import com.example.service.RoleService;
import com.example.service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/")
public class AdminRestController {
  private final UserService userService;
  private final RoleService roleService;

  @Autowired
  public AdminRestController(UserService userService, RoleService roleService) {
    this.userService = userService;
    this.roleService = roleService;
  }

  @GetMapping("/users")
  public List<User> getAllUsers() {
    return this.userService.getAll();
  }

  @PostMapping("addUser")
  public User createUser(@RequestBody User user) {
    return userService.addUser(user);
  }

  @GetMapping("roles")
  public List<Role> getAllRoles() {
    return this.roleService.getRoles();
  }

  @GetMapping("roles/{id}")
  public ResponseEntity<Role> getRoleById(@PathVariable(value = "id") Long roleId) {
    Role role = roleService.getRoleById(roleId);
    if (role == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(role);
  }

  @GetMapping("updateUser/{id}")
  public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) {
    User user = userService.getUserById(id);
    if (user == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(user);
  }

  @PutMapping("updateUser/{id}")
  public ResponseEntity<User> updateEmployee(@PathVariable(value = "id") Long userId, @Valid @RequestBody User editUser) {
    User user = userService.getUserById(userId);
    if (user == null) {
      return ResponseEntity.notFound().build();
    }
    user.setEmail(editUser.getEmail());
    user.setLogin(editUser.getLogin());
    user.setPassword(editUser.getPassword());
    user.setRoles(editUser.getRoles());
    userService.updateUser(user);
    return ResponseEntity.ok().body(userService.getUserById(userId));
  }
}
