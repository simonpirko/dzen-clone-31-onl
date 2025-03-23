package by.tms.dzenclone31onl.controller;

import by.tms.dzenclone31onl.domain.Category;
import by.tms.dzenclone31onl.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Map<String, String> requestBody) {
        String name = requestBody.get("name");
        String description = requestBody.get("description");

        if (name == null){
            return ResponseEntity.badRequest().body("Название категории обязательно");
        }
        Optional<Category> category = categoryService.findByName(name);
        if (category.isPresent()){
            return ResponseEntity.badRequest().body("Такая категория уже существует");
        }
        Category newCategory = categoryService.createCategory(name, description);
        return ResponseEntity.ok(Map.of("message", "Категория добавлена"));
    }
}
