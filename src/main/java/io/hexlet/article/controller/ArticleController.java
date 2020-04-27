package io.hexlet.article.controller;

import io.hexlet.article.domain.Article;
import io.hexlet.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static java.time.LocalDateTime.now;

@RequiredArgsConstructor
@Controller
@RequestMapping("/articles")
public class ArticleController {

    private static final String TEMPLATE_DIR = "article";

    private static final String LIST = TEMPLATE_DIR + "/list";

    private static final String VIEW = TEMPLATE_DIR + "/view";

    private static final String CREATE = TEMPLATE_DIR + "/create";

    private static final String UPDATE = TEMPLATE_DIR + "/update";

    private static final String REDIRECT_ARTICLES_INDEX = "redirect:/articles/";

    private final ArticleService articleService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("articles", articleService.findAll());
        return LIST;
    }

    @GetMapping("/search")
    public String getByAuthor(Model model, @RequestParam String name) {
        model.addAttribute("articles", articleService.findByAuthor(name));
        return LIST;
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable Long id) {
        final Optional<Article> article = articleService.findById(id);
        if (article.isEmpty()) {
            return REDIRECT_ARTICLES_INDEX;
        }
        model.addAttribute("article", article.get());
        return VIEW;
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("article", new Article());
        return CREATE;
    }

    @PostMapping
    public String create(Model model, @ModelAttribute @Valid Article article) {
        article.setId(null);
        article.setPublishedAt(now());
        final Article savedArticle = articleService.save(article);
        model.addAttribute("article", savedArticle);
        return REDIRECT_ARTICLES_INDEX + savedArticle.getId();
    }

    @GetMapping("/update/{id}")
    public String getUpdatePage(Model model, @PathVariable Long id) {
        final Optional<Article> article = articleService.findById(id);
        if (article.isEmpty()) {
            return REDIRECT_ARTICLES_INDEX;
        }
        model.addAttribute("article", article.get());
        return UPDATE;
    }

    @PutMapping
    public String update(Model model, @Valid Article article) {
        if (article.getId() == null) {
            return REDIRECT_ARTICLES_INDEX;
        }
        final Article updatedArticle = articleService.save(article);
        model.addAttribute("article", updatedArticle);
        return REDIRECT_ARTICLES_INDEX + updatedArticle.getId();
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        articleService.deleteById(id);
        return REDIRECT_ARTICLES_INDEX;
    }
}
