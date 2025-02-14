package za.co.wedela.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.wedela.backend.dao.UserDao;
import za.co.wedela.backend.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Register a new user", description = "Endpoint to create a new user")
    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDao user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(user));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDao user) {
        return ResponseEntity.ok(userService.login(user.getUsername(), user.getPassword()));
    }

    @GetMapping
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") String id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") String id, @RequestBody UserDao user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}