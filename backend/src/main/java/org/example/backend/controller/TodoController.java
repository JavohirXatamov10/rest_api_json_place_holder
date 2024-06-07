package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.Util.Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {
    private final Util util;
    @GetMapping("/get/{id}")
    public String getTodo(@PathVariable Integer id){
        String url = "https://jsonplaceholder.typicode.com/todos?userId="+id;
        return util.getAll(url);
    }
}
