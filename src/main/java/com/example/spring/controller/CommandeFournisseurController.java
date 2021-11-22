package com.example.spring.controller;


import java.util.List;

import com.example.spring.controller.api.CommandeFournisseurApi;
import com.example.spring.dto.CommandeFournisseurDto;
import com.example.spring.services.CommandeFournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandeFournisseurController implements CommandeFournisseurApi {

  private CommandeFournisseurService commandeFournisseurService;

  @Autowired
  public CommandeFournisseurController(CommandeFournisseurService commandeFournisseurService) {
    this.commandeFournisseurService = commandeFournisseurService;
  }

  @Override
  public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
    return commandeFournisseurService.save(dto);
  }


  @Override
  public CommandeFournisseurDto findById(Integer id) {
    return commandeFournisseurService.findById(id);
  }

  @Override
  public CommandeFournisseurDto findByCode(String code) {
    return commandeFournisseurService.findByCode(code);
  }

  @Override
  public List<CommandeFournisseurDto> findAll() {
    return commandeFournisseurService.findAll();
  }

  @Override
  public void delete(Integer id) {
    commandeFournisseurService.delete(id);
  }
}
