package com.example.spring.services.impl;

import com.example.spring.dto.CommandeClientDto;
import com.example.spring.dto.LigneVenteDto;
import com.example.spring.dto.VentesDto;
import com.example.spring.exception.EntityNotFoundException;
import com.example.spring.exception.ErrorCodes;
import com.example.spring.exception.InvalidEntityException;
import com.example.spring.model.Article;
import com.example.spring.model.LigneVente;
import com.example.spring.model.Ventes;
import com.example.spring.repository.ArticleRepository;
import com.example.spring.repository.LigneVenteRepository;
import com.example.spring.repository.VentesRepository;
import com.example.spring.services.VentesService;
import com.example.spring.validator.VentesValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VentesServicImpl implements VentesService {

    private final VentesRepository ventesRepository;
    private final ArticleRepository articleRepository;
    private final LigneVenteRepository ligneVenteRepository;

    public VentesServicImpl(VentesRepository ventesRepository, ArticleRepository articleRepository, LigneVenteRepository ligneVenteRepository) {
        this.ventesRepository = ventesRepository;
        this.articleRepository = articleRepository;
        this.ligneVenteRepository = ligneVenteRepository;
    }

    @Override
    public VentesDto save(VentesDto dto) {
        List<String> errors = VentesValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Vente n'est pas valide");
            throw new InvalidEntityException("Vente n'est pas valide", ErrorCodes.VENTE_NOT_VALID,errors);
        }

        List<String> articleErrors = new ArrayList<>();

        if(dto.getLigneVentes() != null) {
            dto.getLigneVentes().forEach(ligneVenteDto -> {
              Optional<Article> article =articleRepository.findById(ligneVenteDto.getArticle().getId());
              if (article.isEmpty()){
                  articleErrors.add("Aucun client avec l'id "+ligneVenteDto.getArticle().getId() +"n'a ete touve dans la BDD");
              }
            });
        }
        Ventes savedVentes = ventesRepository.save(VentesDto.toEntity(dto));

        dto.getLigneVentes().forEach(ligneVenteDto -> {
            LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVente(savedVentes);
            ligneVenteRepository.save(ligneVente);

        });

        return VentesDto.fromEntity(savedVentes);
    }

    @Override
    public VentesDto findById(Integer id) {
        if (id==null){
            log.error("Vente  id is null");
            return null;
        }
        return ventesRepository.findById(id).map(VentesDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun vente  avec l'id = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.VENTE_NOT_FOUND)
        );
    }

    @Override
    public VentesDto findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("Vente CODE is NULL");
            return null;
        }
        return ventesRepository.findVentesByCode(code)
                .map(VentesDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune vente client n'a ete trouve avec le CODE " + code, ErrorCodes.VENTE_NOT_VALID
                ));
    }

    @Override
    public List<VentesDto> findAll() {
        return ventesRepository.findAll().stream()
                .map(VentesDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null){
            log.error("Vente id is null");
            return ;
        }
        ligneVenteRepository.deleteById(id);
    }
}
