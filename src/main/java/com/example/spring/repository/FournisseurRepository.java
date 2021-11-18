package com.example.spring.repository;

import com.example.spring.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {

}
