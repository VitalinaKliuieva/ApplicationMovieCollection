package com.kliuieva.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;


import java.util.List;
@Entity
@Data @AllArgsConstructor
@NoArgsConstructor
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String date;
    private String genre;
    private String director;

    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "movie_actors",
            joinColumns = {@JoinColumn(
                    name = "movie_id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "actor_id"
            )}
    )
    private List<Actor> actors;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "movie_platforms",
            joinColumns = {@JoinColumn(
                    name = "movie_id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "platform_id"
            )}
    )
    @Column( name = "streaming_platform")
    private List<StreamingPlatform> streamingPlatforms;

    private String rating;
    private String image;







}
