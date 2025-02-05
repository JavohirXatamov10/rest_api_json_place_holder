package org.example.backend.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Photo {
    private Integer albumId;
    private Integer id;
    private String title;
    private String url;
    private String thumbnailUrl;

}
