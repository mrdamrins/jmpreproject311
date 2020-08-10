package com.example.controller;

import com.example.model.Role;
import com.example.model.User;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  @Autowired
  public MainController(UserRepository userRepository, RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }

  @GetMapping("/admin")
  public String main(Model model) {
    model.addAttribute("users", userRepository.findAll());
    return "main";
  }

  @GetMapping("/user")
  public String showUserPage(Model model) {
    return "user-page";
  }

  @PostMapping("filter")
  public String filter(@RequestParam String filter,
      Model model) {
    Iterable<User> users;
    if (filter != null && !filter.isEmpty()) {
      users = userRepository.findByFirstName(filter);
    } else {
      users = userRepository.findAll();
    }
    model.addAttribute("users", users);
    return "main";
  }

  @GetMapping("/admin/create")
  public String showFormAddUser() {
    return "new-user";
  }

  @PostMapping("/admin/create")
  public String addUser(@Valid User user,
      BindingResult result,
      @RequestParam(value = "role") String roleName) {
    if (result.hasErrors()) {
      return "new-user";
    }
    Set<Role> roles = new HashSet<>();
    String[] split = roleName.split(",");
    for (String role : split) {
      roles.add(roleRepository.findByRoleName(role));
    }
    user.setRoles(roles);
    userRepository.save(user);
    return "redirect:/admin";
  }

  @GetMapping("/admin/edit/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id,
      Model model) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));
    model.addAttribute("user", user);
    return "update-user";
  }

  @PostMapping("/admin/update/{id}")
  public String updateUser(@PathVariable("id") Long id,
      @Valid User user,
      BindingResult result,
      @RequestParam(value = "role") String roleName) {
    if (result.hasErrors()) {
      user.setId(id);
      return "update-user";
    }
    Set<Role> roles = new HashSet<>();
    String[] split = roleName.split(",");
    for (String role : split) {
      roles.add(roleRepository.findByRoleName(role));
    }
    user.setRoles(roles);
    userRepository.save(user);
    return "redirect:/admin";
  }

  @GetMapping("admin/delete/{id}")
  public String deleteUser(@PathVariable("id") Integer id,
      Model model) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));
    userRepository.delete(user);
    model.addAttribute("users", userRepository.findAll());
    return "redirect:/admin";
  }
}
