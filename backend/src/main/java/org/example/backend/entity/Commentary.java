package org.example.backend.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Commentary {
    private Integer postId;
    private Integer id;
    private String name;
    private String email;
    private String body;
}
