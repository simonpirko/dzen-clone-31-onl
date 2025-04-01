package by.tms.dzenclone31onl.repository;

import by.tms.dzenclone31onl.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByCategory(Long categoryId);
}
