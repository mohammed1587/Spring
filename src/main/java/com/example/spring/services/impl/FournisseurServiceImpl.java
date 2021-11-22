package com.example.spring.services.impl;


import java.util.List;
import java.util.stream.Collectors;

import com.example.spring.dto.FournisseurDto;
import com.example.spring.services.FournisseurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {


  @Override
  public FournisseurDto save(FournisseurDto dto) {
    return null;
  }

  @Override
  public FournisseurDto findById(Integer id) {
    return null;
  }

  @Override
  public List<FournisseurDto> findAll() {
    return null;
  }

  @Override
  public void delete(Integer id) {

  }
}
