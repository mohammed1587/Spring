package com.example.spring.controller.api;

import static com.example.spring.utils.Constants.APP_ROOT;


import java.math.BigDecimal;
import java.util.List;

import com.example.spring.dto.CommandeClientDto;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api("commandesclients")
public interface CommandeClientApi {


  @PostMapping(APP_ROOT + "/commandesclients/create")
  ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto dto);

  @GetMapping(APP_ROOT + "/commandesclients/{idCommandeClient}")
  ResponseEntity<CommandeClientDto> findById(@PathVariable Integer idCommandeClient);

  @GetMapping(APP_ROOT + "/commandesclients/filter/{codeCommandeClient}")
  ResponseEntity<CommandeClientDto> findByCode(@PathVariable("codeCommandeClient") String code);

  @GetMapping(APP_ROOT + "/commandesclients/all")
  ResponseEntity<List<CommandeClientDto>> findAll();

  @DeleteMapping(APP_ROOT + "/commandesclients/delete/{idCommandeClient}")
  ResponseEntity<Void> delete(@PathVariable("idCommandeClient") Integer id);

}
