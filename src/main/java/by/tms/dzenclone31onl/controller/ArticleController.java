package by.tms.dzenclone31onl.controller;

import by.tms.dzenclone31onl.domain.Article;
import by.tms.dzenclone31onl.domain.Category;
import by.tms.dzenclone31onl.domain.User;
import by.tms.dzenclone31onl.service.ArticleService;
import by.tms.dzenclone31onl.service.CategoryService;
import by.tms.dzenclone31onl.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final CategoryService categoryService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> addArticle(@RequestBody Map<String, String> body, @AuthenticationPrincipal User userAuth) {
        String title = body.get("title");
        String content = body.get("content");
        String category = body.get("category");

        if (title == null || content == null || category == null) {
            return ResponseEntity.badRequest().body("Все поля должны быть заполнены");
        }

        String username = userAuth.getUsername();
        Optional<User> userOptional = userService.getUserByUsername(username);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(404).body("Такой пользователь не найден");
        }
        User author = userOptional.get();

        Optional<Category> categoryOptional = categoryService.findByName(category);
        if (categoryOptional.isEmpty()) {
            return ResponseEntity.status(404).body("Категория не найдена");
        }
        Category category1 = categoryOptional.get();

        Article article = articleService.create(title, content, category1, author);
        return ResponseEntity.ok(Map.of("message", "Статья создана :)"));
    }
}
