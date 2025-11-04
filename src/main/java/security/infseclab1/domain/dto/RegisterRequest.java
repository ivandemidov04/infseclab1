package security.infseclab1.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @Size(min = 4, max = 50, message = "Имя пользователя должно содержать от 4 до 50 символов")
    @NotBlank(message = "Имя пользователя не может быть пустыми")
    private String username;

    @Size(min = 4, max = 255, message = "Длина пароля должна быть от 4 до 255 символов")
    @NotBlank(message = "Пароль не может быть пустыми")
    private String password;

    @NotBlank(message = "Статус не может быть пустыми")
    private String Status;
}
