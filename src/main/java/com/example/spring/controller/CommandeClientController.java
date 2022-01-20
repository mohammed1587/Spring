package com.example.spring.controller;


 import java.util.List;

import com.example.spring.controller.api.CommandeClientApi;
import com.example.spring.dto.CommandeClientDto;
import com.example.spring.services.CommandeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandeClientController implements CommandeClientApi {

  private final CommandeClientService commandeClientService;

  @Autowired
  public CommandeClientController(CommandeClientService commandeClientService) {
    this.commandeClientService = commandeClientService;
  }

  @Override
  public ResponseEntity<CommandeClientDto> save(CommandeClientDto dto) {
    return ResponseEntity.ok(commandeClientService.save(dto));
  }


  @Override
  public ResponseEntity<CommandeClientDto> findById(Integer id) {
    return ResponseEntity.ok(commandeClientService.findById(id));
  }

  @Override
  public ResponseEntity<CommandeClientDto> findByCode(String code) {
    return ResponseEntity.ok(commandeClientService.findByCode(code));
  }

  @Override
  public ResponseEntity<List<CommandeClientDto>> findAll() {
    return ResponseEntity.ok(commandeClientService.findAll());
  }

  @Override
  public ResponseEntity<Void> delete(Integer id) {
    commandeClientService.delete(id);
    return ResponseEntity.ok().build();
  }
}
