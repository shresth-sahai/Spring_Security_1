package com.example.demo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;
    public AuthService(UserRepository userRepository,JwtUtil jwtUtil){
        this.userRepository=userRepository;
        this.jwtUtil=jwtUtil;
        this.passwordEncoder=new BCryptPasswordEncoder();
    }
    public String registerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");userRepository.save(user);
        return "User Registered Done!";
    }
    public String authenticate(String username,String password){
        Optional<User> user=userRepository.findByUserName(username);
        if(user.isPresent() && passwordEncoder.matches(password,user.get().getPassword())){
            return JwtUtil.generateToken(username);
        }
        return "Invalid";
    }

}
