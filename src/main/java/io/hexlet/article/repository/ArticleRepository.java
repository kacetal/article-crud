package io.hexlet.article.repository;

import io.hexlet.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAllByAuthorLikeIgnoreCase(String author);
}
