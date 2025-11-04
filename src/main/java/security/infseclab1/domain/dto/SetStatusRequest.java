package security.infseclab1.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SetStatusRequest {
    @NotBlank(message = "Статус не может быть пустыми")
    private String status;
}
