package com.example.repository;

import com.example.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  @Query("FROM Role where role = :roleName")
  Role getRoleByName(@Param("roleName") String roleName);
}
