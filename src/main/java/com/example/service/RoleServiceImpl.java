package com.example.service;

import com.example.model.Role;
import com.example.repository.RoleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl  implements RoleService {

  @Autowired
  private RoleRepository roleRepository;

  @Transactional
  @Override
  public Role getRoleById(long id) {
    return roleRepository.findById(id).get();
  }

  @Transactional
  @Override
  public Role getRoleByName(String roleName) {
    return roleRepository.getRoleByName(roleName);
  }

  @Transactional
  @Override
  public void addRole(Role role) {
    roleRepository.saveAndFlush(role);
  }

  @Transactional
  @Override
  public void updateRole(Role role) {
    roleRepository.saveAndFlush(role);
  }

  @Transactional
  @Override
  public void deleteRole(long id) {
    roleRepository.deleteById(id);
  }

  @Transactional
  @Override
  public List<Role> getRoles() {
    return roleRepository.findAll();
  }
}
