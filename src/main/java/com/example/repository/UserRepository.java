package com.example.repository;

import com.example.model.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
  List<User> findByFirstName(String firstName);
  User findByEmail(String email);

}
