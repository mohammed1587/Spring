package com.example.spring.repository;

import java.util.List;
import java.util.Optional;

import com.example.spring.model.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeClientRepository extends JpaRepository<CommandeClient, Integer> {

    Optional<CommandeClient> findCommandeClientByCode(String code);
}
