package com.example.spring.repository;

 import java.util.Optional;

 import com.example.spring.model.Ventes;
 import org.springframework.data.jpa.repository.JpaRepository;

public interface VentesRepository extends JpaRepository<Ventes, Integer> {

 }
