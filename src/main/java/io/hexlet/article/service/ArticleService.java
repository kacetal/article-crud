package io.hexlet.article.service;

import io.hexlet.article.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    Article save(Article article);

    List<Article> findAll();

    List<Article> findByAuthor(String author);

    Optional<Article> findById(Long id);

    void deleteById(Long id);

    void delete(Article article);
}
