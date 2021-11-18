package com.example.spring.services.impl;

import com.example.spring.dto.ArticleDto;
import com.example.spring.dto.CommandeClientDto;
import com.example.spring.dto.LigneCommandeClientDto;
import com.example.spring.exception.EntityNotFoundException;
import com.example.spring.exception.ErrorCodes;
import com.example.spring.exception.InvalidEntityException;
import com.example.spring.model.Article;
import com.example.spring.model.Client;
import com.example.spring.model.CommandeClient;
import com.example.spring.model.LigneCommandeClient;
import com.example.spring.repository.ArticleRepository;
import com.example.spring.repository.ClientRepository;
import com.example.spring.repository.CommandeClientRepository;
import com.example.spring.repository.LigneCommandeClientRepository;
import com.example.spring.services.CommandeClientService;
import com.example.spring.validator.CommandeClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandClientServiceImpl implements CommandeClientService {

    private final CommandeClientRepository commandeClientRepository;
    private final ClientRepository clientRepository;
    private final ArticleRepository articleRepository;
    private final LigneCommandeClientRepository ligneCommandeClientRepository;

    @Autowired
    public CommandClientServiceImpl(CommandeClientRepository commandeClientRepository, ClientRepository clientRepository, ArticleRepository articleRepository, LigneCommandeClientRepository ligneCommandeClientRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {

        List<String> errors = CommandeClientValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Command client n'est pas valide");
            throw new InvalidEntityException("La commande client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID,errors);
        }
        Optional<Client> client = clientRepository.findById(dto.getClient().getId());
        if (client.isEmpty()){
            log.warn("Client with ID {} was not found in the DB",dto.getClient().getId());
            throw new EntityNotFoundException("Aucun client avec l'id "+dto.getClient().getId() +"n'a ete touve dans la BDD",ErrorCodes.CLIENT_NOT_FOUND);
        }
         List<String> articleErrors = new ArrayList<>();
        if (dto.getLigneCommandeClients() != null){
            dto.getLigneCommandeClients().forEach(ligneCommandeClientDto -> {
                if(ligneCommandeClientDto.getArticle() != null){
                    Optional<Article> article = articleRepository.findById(ligneCommandeClientDto.getArticle().getId());
                    if(article.isEmpty()){
                        articleErrors.add("L'article avec l'id"+ligneCommandeClientDto.getArticle().getId()+"n'existe pas");
                    }
                }

             });
        }

        if(!articleErrors.isEmpty()){
            log.warn("");
            throw new InvalidEntityException("Article n'exist pas dans la BDD",ErrorCodes.ARTICLE_NOT_FOUND,articleErrors);
        }
        CommandeClient commandeClient = commandeClientRepository.save(CommandeClientDto.toEntity(dto));

        if(dto.getLigneCommandeClients() != null) {
            dto.getLigneCommandeClients().forEach(ligneCommandeClientDto -> {
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligneCommandeClientDto);
                ligneCommandeClient.setCommandeClient(commandeClient);
                ligneCommandeClientRepository.save(ligneCommandeClient);
            });
        }
        return CommandeClientDto.fromEntity(commandeClient);
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        if (id==null){
            log.error("Commande Client  id is null");
            return null;
        }
        return commandeClientRepository.findById(id).map(CommandeClientDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune commande client  avec l'id = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.COMMANDE_CLIENT_NOT_FOUND)
        );
    }


    @Override
    public CommandeClientDto findByCode(String code) {
        if (!StringUtils.hasLength(code)){
            log.error("Commande client code is null");
            return null;
        }
        return commandeClientRepository.findCommandeClientByCode(code).map(CommandeClientDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune commande client avec le code = " + code + " n' ete trouve dans la BDD",
                        ErrorCodes.COMMANDE_CLIENT_NOT_FOUND)
        );    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream().map(CommandeClientDto::fromEntity).collect(Collectors.toList());

    }

    @Override
    public void delete(Integer id) {
        if (id==null){
            log.error("Commande client id is null");
            return ;
        }
        commandeClientRepository.deleteById(id);
    }
}
