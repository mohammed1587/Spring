package com.example.spring.services;

 import com.example.spring.dto.FournisseurDto;
 import org.springframework.stereotype.Service;

 import java.util.List;

public interface FournisseurService {

  FournisseurDto save(FournisseurDto dto);

  FournisseurDto findById(Integer id);

  List<FournisseurDto> findAll();

  void delete(Integer id);

}
