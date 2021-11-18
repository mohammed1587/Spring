package com.example.spring.services.impl;

import com.example.spring.dto.CommandeClientDto;
import com.example.spring.dto.CommandeFournisseurDto;
import com.example.spring.dto.LigneCommandeClientDto;
import com.example.spring.dto.LigneCommandeFournisseurDto;
import com.example.spring.exception.EntityNotFoundException;
import com.example.spring.exception.ErrorCodes;
import com.example.spring.exception.InvalidEntityException;
import com.example.spring.model.*;
import com.example.spring.repository.*;
import com.example.spring.services.CommandeFournisseurService;
import com.example.spring.validator.CommandeFournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private final CommandeFournisseurRepository commandeFournisseurRepository;
    private final FournisseurRepository fournisseurRepository;
    private final ArticleRepository articleRepository;
    private final LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;

    public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository, FournisseurRepository fournisseurRepository, ArticleRepository articleRepository, LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
        List<String> errors = CommandeFournisseurValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Command fournisseur n'est pas valide");
            throw new InvalidEntityException("La commande fournisseur n'est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID,errors);
        }

        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(dto.getFournisseur().getId());
        if (fournisseur.isEmpty()){
            log.warn("Fournisseur with ID {} was not found in the DB",dto.getFournisseur().getId());
            throw new EntityNotFoundException("Aucun fournisseur avec l'id "+dto.getFournisseur().getId() +"n'a ete touve dans la BDD",ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }
        List<String> articleErrors = new ArrayList<>();
        if (dto.getLigneCommandeFournisseurs() != null){
            dto.getLigneCommandeFournisseurs().forEach(ligneCommandeFournisseurDto -> {
                if(ligneCommandeFournisseurDto.getArticle() != null){
                    Optional<Article> article = articleRepository.findById(ligneCommandeFournisseurDto.getArticle().getId());
                    if(article.isEmpty()){
                        articleErrors.add("L'article avec l'id"+ligneCommandeFournisseurDto.getArticle().getId()+"n'existe pas");
                    }
                }

            });
        }else{
            articleErrors.add("Impossible d'enregitrer une commande avec un article null");
        }
        CommandeFournisseur commandeFournisseur = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(dto));

        if(dto.getLigneCommandeFournisseurs() != null) {
            dto.getLigneCommandeFournisseurs().forEach(ligneCommandeClientDto -> {
                LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(ligneCommandeClientDto);
                ligneCommandeFournisseur.setCommandeFournisseur(commandeFournisseur);
                ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
            });
        }
        return CommandeFournisseurDto.fromEntity(commandeFournisseur);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        if (id==null){
            log.error("Commande fournisseur  id is null");
            return null;
        }
        return commandeFournisseurRepository.findById(id).map(CommandeFournisseurDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune commande fournisseur  avec l'id = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND)
        );
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if (!StringUtils.hasLength(code)){
            log.error("Commande fournisseur code is null");
            return null;
        }
        return commandeFournisseurRepository.findCommandeFournisseurByCode(code).map(CommandeFournisseurDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune commande fournisseur avec le code = " + code + " n' ete trouve dans la BDD",
                        ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND)
        );    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurRepository.findAll().stream().map(CommandeFournisseurDto::fromEntity).collect(Collectors.toList());

    }

    @Override
    public void delete(Integer id) {
        if (id==null){
            log.error("Commande fournisseur id is null");
            return ;
        }
        commandeFournisseurRepository.deleteById(id);
    }
}
