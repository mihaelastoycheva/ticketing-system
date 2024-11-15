package bg.tu_sofia.fdiba.ticketing.controller;

import bg.tu_sofia.fdiba.ticketing.model.User;
import bg.tu_sofia.fdiba.ticketing.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/register")
    public ResponseEntity<String> register(final @RequestBody User user) {
        return this.userService.registerUser(user);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(final @RequestParam String username, final @RequestParam String password) {
        return this.userService.loginUser(username, password);
    }

    @GetMapping(value = "/getAll")
    public List<User> getAll() {
        return this.userService.getAllUsers();
    }
}