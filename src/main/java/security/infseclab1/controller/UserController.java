package security.infseclab1.controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import security.infseclab1.domain.dto.SetStatusRequest;
import security.infseclab1.service.UserService;

@RestController
@RequestMapping("/api")
@SuppressFBWarnings("EI_EXPOSE_REP2")
public final class UserController {
    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/data")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok(userService.getStatus());
    }

    @PutMapping("/data")
    public ResponseEntity<Void> setStatus(@RequestBody @Valid SetStatusRequest request) {
        userService.setStatus(request);
        return ResponseEntity.noContent().build();
    }
}
