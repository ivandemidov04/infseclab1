package security.infseclab1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import security.infseclab1.domain.model.User;
import security.infseclab1.error.UserAlreadyExistsException;
import security.infseclab1.repository.UserRepository;
import security.infseclab1.domain.dto.SetStatusRequest;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User create(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существует");
        }

        return save(user);
    }

    public String getStatus() {
        return this.getCurrentUser().getStatus();
    }

    public Boolean setStatus(SetStatusRequest request) {
        User user = this.getCurrentUser();
        user.setStatus(request.getStatus());
        User newUser = userRepository.save(user);
        return Objects.equals(newUser.getStatus(), request.getStatus());
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}

