package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Transactional
  @Override
  public List<User> getAll() {
    return userRepository.getAll();
  }

  @Transactional
  @Override
  public User addUser(User user) {
    this.userRepository.saveAndFlush(user);
    return user;
  }

  @Transactional
  @Override
  public void delete(Long id) {
    userRepository.deleteById(id);
  }

  @Transactional
  @Override
  public void updateUser(User user) {
    userRepository.save(user);
  }

  @Transactional
  @Override
  public User getUserById(Long id) {
    return userRepository.findById(id).get();
  }

  @Transactional
  @Override
  public User getUserByName(String email) {
    return userRepository.getUserByName(email);
  }

}