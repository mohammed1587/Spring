package com.example.spring.services;


import com.example.spring.dto.CommandeFournisseurDto;
import com.example.spring.dto.LigneCommandeFournisseurDto;
import com.example.spring.model.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeFournisseurService {

  CommandeFournisseurDto save(CommandeFournisseurDto dto);

   CommandeFournisseurDto findById(Integer id);

  CommandeFournisseurDto findByCode(String code);

  List<CommandeFournisseurDto> findAll();


  void delete(Integer id);

}
