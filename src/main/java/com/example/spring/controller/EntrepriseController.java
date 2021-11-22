package com.example.spring.controller;


import java.util.List;

import com.example.spring.controller.api.EntrepriseApi;
import com.example.spring.dto.EntrepriseDto;
import com.example.spring.services.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntrepriseController implements EntrepriseApi {

  private final EntrepriseService entrepriseService;

  @Autowired
  public EntrepriseController(EntrepriseService  entrepriseService) {
    this.entrepriseService = entrepriseService;
  }

  @Override
  public EntrepriseDto save(EntrepriseDto dto) {
    return entrepriseService.save(dto);
  }

  @Override
  public EntrepriseDto findById(Integer id) {
    return entrepriseService.findById(id);
  }

  @Override
  public List<EntrepriseDto> findAll() {
    return entrepriseService.findAll();
  }

  @Override
  public void delete(Integer id) {
    entrepriseService.delete(id);
  }
}
