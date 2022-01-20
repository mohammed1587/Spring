package com.example.spring.services.impl;


import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import com.example.spring.dto.EntrepriseDto;

import com.example.spring.exception.EntityNotFoundException;
import com.example.spring.exception.ErrorCodes;
 import com.example.spring.repository.EntrepriseRepository;
import com.example.spring.repository.RolesRepository;
import com.example.spring.services.EntrepriseService;
import com.example.spring.services.UtilisateurService;
 import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional(rollbackOn = Exception.class)
@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

  private EntrepriseRepository entrepriseRepository;

  @Autowired
  public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository
      ) {
    this.entrepriseRepository = entrepriseRepository;
   }

  @Override
  public EntrepriseDto save(EntrepriseDto dto) {

    return  null;
  }





  @Override
  public EntrepriseDto findById(Integer id) {
    if (id == null) {
      log.error("Entreprise ID is null");
      return null;
    }
    return entrepriseRepository.findById(id)
        .map(EntrepriseDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
            "Aucune entreprise avec l'ID = " + id + " n' ete trouve dans la BDD",
            ErrorCodes.ENTREPRISE_NOT_FOUND)
        );
  }

  @Override
  public List<EntrepriseDto> findAll() {
    return entrepriseRepository.findAll().stream()
        .map(EntrepriseDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Entreprise ID is null");
      return;
    }
    entrepriseRepository.deleteById(id);
  }
}
