package com.example.spring.services;

import com.example.spring.dto.CommandeClientDto;
import com.example.spring.dto.LigneCommandeClientDto;
import com.example.spring.model.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeClientService {

  CommandeClientDto save(CommandeClientDto dto);

  CommandeClientDto findById(Integer id);

  CommandeClientDto findByCode(String code);

  List<CommandeClientDto> findAll();

  void delete(Integer id);

}
