package by.tms.dzenclone31onl.service;

import by.tms.dzenclone31onl.domain.Category;
import by.tms.dzenclone31onl.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category createCategory(String name, String description) {
        Category category = Category.builder()
                .name(name)
                .description(description).build();
        return categoryRepository.save(category);
    }

    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
