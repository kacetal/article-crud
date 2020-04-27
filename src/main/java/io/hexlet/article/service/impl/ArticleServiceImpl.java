package io.hexlet.article.service.impl;

import io.hexlet.article.domain.Article;
import io.hexlet.article.repository.ArticleRepository;
import io.hexlet.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public Article save(Article article) {
        log.info("Request to save Article : {}", article);
        return articleRepository.save(article);
    }

    @Override
    public List<Article> findAll() {
        log.info("Request to get all Article");
        return articleRepository.findAll();
    }

    @Override
    public List<Article> findByAuthor(String author) {
        log.info("Request to get Article by author ignore case : {}", author);
        return articleRepository.findAllByAuthorLikeIgnoreCase(author);
    }

    @Override
    public Optional<Article> findById(Long id) {
        log.info("Request to get Article by id : {}", id);
        return articleRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Request to delete Article by id : {}", id);
        articleRepository.deleteById(id);
    }

    @Override
    public void delete(Article article) {
        log.info("Request to delete Article : {}", article);
        articleRepository.delete(article);
    }
}
