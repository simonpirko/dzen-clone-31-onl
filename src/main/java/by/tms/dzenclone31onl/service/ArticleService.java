package by.tms.dzenclone31onl.service;

import by.tms.dzenclone31onl.domain.Article;
import by.tms.dzenclone31onl.domain.Category;
import by.tms.dzenclone31onl.domain.User;
import by.tms.dzenclone31onl.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Article create(String title, String content, Category category, User author) {
        Article article = Article.builder()
                .title(title)
                .content(content)
                .category(category)
                .author(author)
                .views(0)
                .likes(0)
                .build();

        return articleRepository.save(article);
    }

    public List<Article> findByCategory(Long categoryId) {
        return articleRepository.findByCategory(categoryId);
    }
}
