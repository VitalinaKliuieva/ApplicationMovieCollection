package com.kliuieva.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MovieDTO {
    private String name;
    private String date;
    private String genre;
    private String director;
    private List<Long> actorIds;
    private String description;
    private List<Long> streamingPlatformIds;
    private String rating;
    private String image;
}
