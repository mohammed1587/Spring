package com.example.spring.services.impl;

import com.example.spring.dto.ArticleDto;
import com.example.spring.exception.EntityNotFoundException;
import com.example.spring.exception.ErrorCodes;
import com.example.spring.exception.InvalidEntityException;
import com.example.spring.model.Article;
import com.example.spring.repository.ArticleRepository;
import com.example.spring.services.ArticleService;
import com.example.spring.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository repository;
    public ArticleServiceImpl(ArticleRepository repository){
        this.repository = repository;
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {
        List<String> errors = ArticleValidator.validate(articleDto);
        if (!errors.isEmpty()){
            log.error("Article is not valid {}",articleDto);
            throw new InvalidEntityException("L'article n'est pas valid", ErrorCodes.ARTICLE_NOT_VALID,errors);
        }
         return ArticleDto.fromEntity(repository.save(ArticleDto.toEntity(articleDto)));
    }

    @Override
    public ArticleDto findById(Integer id) {
        if (id == null){
            log.error("Article id is null");
            return null;
        }
        return repository.findById(id).map(ArticleDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun article avec l'ID = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.ARTICLE_NOT_FOUND)
        );
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        if (!StringUtils.hasLength(codeArticle)){
            log.error("Article code is null");
            return null;
        }
        return repository.findArticleByCodeArticle(codeArticle).map(ArticleDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun article avec le code = " + codeArticle + " n' ete trouve dans la BDD",
                        ErrorCodes.ARTICLE_NOT_FOUND)
        );    }

    @Override
    public List<ArticleDto> findAll() {
        return repository.findAll().stream().map(ArticleDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null){
            log.error("Article id is null");
            return ;
        }
        repository.deleteById(id);
    }
}
