package com.example.likelionjpaswagger.auth;

import com.example.likelionjpaswagger.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {

        String token = jwtTokenProvider.createToken(request.getEmail());

        return new TokenResponse(token);
    }
}