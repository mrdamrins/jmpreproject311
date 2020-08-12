package com.example.security.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailService")
public class SecurityUserDetailService implements UserDetailsService {
  private final UserRepository userRepository;

  @Autowired
  public SecurityUserDetailService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional(readOnly = true)
  @Override
  public UserDetails loadUserByUsername(String email)
      throws UsernameNotFoundException {
    User user = userRepository.getUserByName(email);
    if (user == null) {
      throw new UsernameNotFoundException(email);
    }
    return user;
  }
}
