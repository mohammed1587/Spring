package com.example.spring.repository;

import java.util.List;
import java.util.Optional;

import com.example.spring.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    Optional<Article> findArticleByCodeArticle(String code);

}
