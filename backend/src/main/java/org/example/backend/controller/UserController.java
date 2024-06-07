package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.Util.Util;
import org.example.backend.entity.User;
import org.example.backend.repo.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserRepository userRepository;
    private final UserDtoService userDtoService;
    private final Util util;
    @GetMapping("/me")
    public User getMe(){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (User) userRepository.findByUsername(username);
    }
    @GetMapping
    public String getUserDto(){
        String url = "https://jsonplaceholder.typicode.com/users";
        return util.getAll(url);
    }
    @GetMapping("/post/{id}")
    public String getUserDtoPost(@PathVariable Integer id){
        String url = "https://jsonplaceholder.typicode.com/posts?userId="+id;
        return util.getAll(url);
    }
}
