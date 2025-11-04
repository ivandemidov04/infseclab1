package security.infseclab1.error;

import java.util.Collections;
import java.util.Map;

public final class ValidationErrorResponse {
    private final String message;
    private final Map<String, String> fieldErrors;
    private final String error;
    private final int status;

    public ValidationErrorResponse(String message, Map<String, String> fieldErrors, String error, int status) {
        this.message = message;
        this.fieldErrors = fieldErrors != null ?
                Collections.unmodifiableMap(fieldErrors) : Collections.emptyMap();
        this.error = error;
        this.status = status;
    }

    public String getMessage() { return message; }
    public Map<String, String> getFieldErrors() { return Collections.unmodifiableMap(fieldErrors); }
    public String getError() { return error; }
    public int getStatus() { return status; }
}