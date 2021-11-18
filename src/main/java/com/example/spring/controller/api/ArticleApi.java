package com.example.spring.controller.api;

import com.example.spring.dto.ArticleDto;
import static com.example.spring.utils.Constants.APP_ROOT;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ArticleApi {

    @PostMapping(value = APP_ROOT + "/articles/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto save(@RequestBody ArticleDto articleDto);

    @GetMapping(value = APP_ROOT + "/articles/{idArticle}",produces = MediaType.APPLICATION_JSON_VALUE )
    ArticleDto findById(@PathVariable("idArticle") Integer id);

    @GetMapping(value = APP_ROOT + "/articles/{codeArticle}",produces = MediaType.APPLICATION_JSON_VALUE )
    ArticleDto findByCodeArticle(@PathVariable("codeArticle") String codeArticle);

    @GetMapping(value = APP_ROOT + "/articles/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<ArticleDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/articles/deleteArticle/{idArticle}")
    void delete(@PathVariable("idArticle") Integer id);
}
