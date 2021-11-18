package com.example.spring.controller.api;

import com.example.spring.dto.ArticleDto;
import static com.example.spring.utils.Constants.APP_ROOT;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("Articles")
public interface ArticleApi {

    @PostMapping(value = APP_ROOT + "/articles/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregister un article" ,notes = "Cette methode permet d'enregistrer un article", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "L'object article cree"),
            @ApiResponse(code = 400,message = "L'object article n'est pas valid")
    })
    ArticleDto save(@RequestBody ArticleDto articleDto);

    @GetMapping(value = APP_ROOT + "/articles/{idArticle}",produces = MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Rechercher un article" ,notes = "Cette methode permet de chercher un article by id ", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "L'object article a ete trouve dans la BDD"),
            @ApiResponse(code = 400,message = "Aucun article n'existe dans la bdd avec l'id fourni"),
    })
    ArticleDto findById(@PathVariable("idArticle") Integer id);

    @GetMapping(value = APP_ROOT + "/articles/{codeArticle}",produces = MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Rechercher un article" ,notes = "Cette methode permet de chercher un article by code ", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "L'object article a ete trouve dans la BDD"),
            @ApiResponse(code = 400,message = "Aucun article n'existe dans la bdd avec le code fourni"),
    })
    ArticleDto findByCodeArticle(@PathVariable("codeArticle") String codeArticle);

    @GetMapping(value = APP_ROOT + "/articles/all",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des articles" ,notes = "Cette permet de renvoiyer la liste des articles", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "la liste des article")
    })
    List<ArticleDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/articles/deleteArticle/{idArticle}")
    @ApiOperation(value = "Supprimer un article " ,notes = "Cette permet de supprimer un article by id ", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "L'article a ete supprimer")
    })
    void delete(@PathVariable("idArticle") Integer id);
}
