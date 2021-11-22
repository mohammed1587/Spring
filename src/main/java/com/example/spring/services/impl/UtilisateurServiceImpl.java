package com.example.spring.services.impl;



import com.example.spring.dto.UtilisateurDto;
import com.example.spring.services.UtilisateurService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

  @Override
  public UtilisateurDto save(UtilisateurDto dto) {
    return null;
  }

  @Override
  public UtilisateurDto findById(Integer id) {
    return null;
  }

  @Override
  public List<UtilisateurDto> findAll() {
    return null;
  }

  @Override
  public void delete(Integer id) {

  }

  @Override
  public UtilisateurDto findByEmail(String email) {
    return null;
  }
}
