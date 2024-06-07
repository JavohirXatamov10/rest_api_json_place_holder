package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.LoginDTO;
import org.example.backend.dto.TokenDTO;
import org.example.backend.security.JwUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwUtil jwUtil;
    @PostMapping("/login")
    public TokenDTO login(@RequestBody LoginDTO loginDTO){
         var auth = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(auth);
        UserDetails  userDetails=(UserDetails) authenticate.getPrincipal();
        return  new TokenDTO("Bearer "+jwUtil.generateToken(userDetails),
                "Bearer "+jwUtil.generateRefreshToken(userDetails)
                );
    }
}
