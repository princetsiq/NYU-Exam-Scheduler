package dev.javaprojects.ExamScheduler.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import dev.javaprojects.ExamScheduler.model.User;
import dev.javaprojects.ExamScheduler.model.UserResponse;
import dev.javaprojects.ExamScheduler.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${jwt.secret}")
    private String secret;

    public UserResponse createUser(@NotNull User user) {
        if (!user.getEmail().matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new IllegalArgumentException("Invalid email format");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        String token = generateToken(savedUser.getEmail());
        return new UserResponse(savedUser, token);
    }

    public UserResponse loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = generateToken(user.getEmail());
        return new UserResponse(user, token);
    }

    private String generateToken(String email) {
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + 864_000_000)) // 10 days
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Transactional
    public boolean updateUser(Long userId, String firstName, String lastName, String email, String password) {
        return userRepository.findById(userId).map(user -> {
            if (firstName != null) user.setFirstName(firstName);
            if (lastName != null) user.setLastName(lastName);
            if (email != null) user.setEmail(email);
            if (password != null) user.setPassword(password);
            userRepository.save(user);
            return true;
        }).orElse(false);
    }

    @Transactional
    public boolean deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}