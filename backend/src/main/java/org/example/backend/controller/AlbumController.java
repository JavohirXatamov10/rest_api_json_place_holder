package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.Util.Util;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/album")
@RequiredArgsConstructor
public class AlbumController {
    private final Util util;
    @GetMapping("/get/{id}")
    public String getAlbum(@PathVariable Integer id){
        String url="https://jsonplaceholder.typicode.com/albums?userId="+id;
        return util.getAll(url);
    }
}
