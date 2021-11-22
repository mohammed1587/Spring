package com.example.spring.services;

import com.example.spring.dto.UtilisateurDto;
import org.springframework.stereotype.Service;

import java.util.List;

 public interface UtilisateurService {

  UtilisateurDto save(UtilisateurDto dto);

  UtilisateurDto findById(Integer id);

  List<UtilisateurDto> findAll();

  void delete(Integer id);

  UtilisateurDto findByEmail(String email);



}
