package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.security.CustomUserDetailsService;
import org.example.backend.security.JwUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/refresh")
@RequiredArgsConstructor
public class RefreshController {
    private final JwUtil jwUtil;
    private final CustomUserDetailsService customUserDetailsService;
    @GetMapping
    public String refresh(){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        return "Bearer "+jwUtil.generateToken(userDetails);
    }
}
