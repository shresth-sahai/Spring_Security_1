package com.example.demo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService){
        this.authService=authService;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        return ResponseEntity.ok(authService.registerUser(user));
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){return ResponseEntity.ok(authService.authenticate(user.getUsername(),user.getPassword()));
    }


}
