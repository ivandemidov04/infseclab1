package security.infseclab1.error;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class ValidationErrorResponse {
    private String message;
    private Map<String, String> fieldErrors;
    private String error;
    private int status;

    public ValidationErrorResponse(String message, Map<String, String> fieldErrors, String error, int status) {
        this.message = message;
        this.fieldErrors = fieldErrors;
        this.error = error;
        this.status = status;
    }
}
