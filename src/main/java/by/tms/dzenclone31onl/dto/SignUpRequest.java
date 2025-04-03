package by.tms.dzenclone31onl.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @Size(min = 5, max = 50, message = "Имя пользователя должно содержать от 5 до 50 символов")
    @NotBlank(message = "Имя пользователя не должно быть пустым")
    private String username;

    @Size(min = 5, max = 255, message = "Адрес электронной почты должен содержать от 5 до 255 символов")
    @NotBlank(message = "Адрес электронной почты не должен быть пустым")
    @Email(message = "Email адрес должен быть в формате user@example.com")
    private String email;

    @Size(min = 4, max = 255, message = "Длина пароля должна быть от 4 до 255 символов")
    private String password;
}