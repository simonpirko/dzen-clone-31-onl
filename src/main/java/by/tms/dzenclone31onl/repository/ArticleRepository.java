package by.tms.dzenclone31onl.repository;

import by.tms.dzenclone31onl.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByCategory_Id(Long categoryId);

    @Query(value = " SELECT id FROM schema_dzen.articles WHERE LOWER(title) LIKE LOWER(CONCAT('%', :keyword, '%'))  OR LOWER(content) LIKE LOWER(CONCAT('%', :keyword, '%'))", nativeQuery = true)
    List<Long> findArticleId(@Param("keyword") String keyword);
}
