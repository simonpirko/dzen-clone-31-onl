package by.tms.dzenclone31onl.repository;

import by.tms.dzenclone31onl.domain.Category;
import by.tms.dzenclone31onl.service.CategoryService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
