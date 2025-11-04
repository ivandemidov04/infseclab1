package security.infseclab1.error;

import lombok.Value;

import java.util.Collections;
import java.util.Map;

@Value
public class ValidationErrorResponse {
    String message;
    Map<String, String> fieldErrors;
    String error;
    int status;

    public ValidationErrorResponse(String message, Map<String, String> fieldErrors, String error, int status) {
        this.message = message;
        this.fieldErrors = fieldErrors != null ?
                Collections.unmodifiableMap(fieldErrors) : Collections.emptyMap();
        this.error = error;
        this.status = status;
    }
}
