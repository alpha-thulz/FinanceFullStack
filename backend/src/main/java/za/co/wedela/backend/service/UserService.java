package za.co.wedela.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import za.co.wedela.backend.UserRepo;
import za.co.wedela.backend.dao.UserDao;
import za.co.wedela.backend.model.User;
import za.co.wedela.backend.model.role.Role;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public Map<?, ?> register(UserDao model) {
        String username = model.getUsername();
        String password = model.getPassword();
        String firstName = model.getFirstName();
        String lastName = model.getLastName();
        String email = model.getEmail();
        String phone = model.getPhone();
        Role role = model.getRole();

        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phone(phone)
                .role(role)
                .build();

        user = userRepo.save(user);
        return Map.of("token", tokenService.generateToken(user.getUsername()));
    }

    public Map<?, ?> login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        if (authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            return Map.of("token", tokenService.generateToken(user.getUsername()));
        }
        throw new UsernameNotFoundException("Invalid username or password");
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUser(String id) {
        return userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user id"));
    }

    public void deleteUser(String id) {
        User user = getUser(id);
        userRepo.delete(user);
    }

    public User updateUser(String id, UserDao model) {
        User user = getUser(id);
        user.setFirstName(model.getFirstName());
        user.setLastName(model.getLastName());
        user.setEmail(model.getEmail());
        user.setPhone(model.getPhone());
        user.setRole(model.getRole());
        user.setUsername(model.getUsername());
        if (model.getPassword() != null && !model.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(model.getPassword()));
        }
        return userRepo.save(user);
    }
}