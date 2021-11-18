package com.example.spring.repository;

 import com.example.spring.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Integer> {

}
