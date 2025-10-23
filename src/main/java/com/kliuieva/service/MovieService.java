package com.kliuieva.service;

import com.kliuieva.dto.MovieDTO;
import com.kliuieva.entity.Actor;
import com.kliuieva.entity.StreamingPlatform;
import com.kliuieva.entity.Movie;
import com.kliuieva.repositories.ActorRepository;
import com.kliuieva.repositories.MovieRepository;
import com.kliuieva.repositories.StreamingPlatformsRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository ;
    private final StreamingPlatformsRepository streamingPlatformsRepository;
    private ActorService actorService;

    private StreamingPlatformService streamingPlatformService;

    @Autowired
    public MovieService(MovieRepository movieRepository,StreamingPlatformsRepository streamingPlatformsRepository, ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.streamingPlatformsRepository = streamingPlatformsRepository;
        this.actorRepository = actorRepository;}

    @Getter
    private List<Movie> movies;
    private MovieService movieService;
    public MovieService(MovieRepository movieRepository, ActorRepository actorRepository, StreamingPlatformsRepository streamingPlatformsRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
        this.streamingPlatformsRepository = streamingPlatformsRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow();
    }

    public Movie save( MovieDTO dto) {
        Movie movie = new Movie();
        movie.setName(dto.getName());
        movie.setDate(dto.getDate());
        movie.setGenre(dto.getGenre());
        movie.setDirector(dto.getDirector());
        movie.setDescription(dto.getDescription());
        movie.setRating(dto.getRating());
        movie.setImage(dto.getImage());

        if (dto.getActorIds() != null) {
            List<Actor> actors = actorRepository.findAllById(dto.getActorIds());
            movie.setActors(actors);
        }

        if (dto.getStreamingPlatformIds() != null) {
            List<StreamingPlatform> platforms = streamingPlatformsRepository.findAllById(dto.getStreamingPlatformIds());
            movie.setStreamingPlatforms(platforms);
        }

        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public void editMovie( MovieDTO dto, Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found with id: " + id));

        movie.setName(dto.getName());
        movie.setDate(dto.getDate());
        movie.setGenre(dto.getGenre());
        movie.setDirector(dto.getDirector());
        movie.setDescription(dto.getDescription());
        movie.setRating(dto.getRating());
        movie.setImage(dto.getImage());

        if (dto.getActorIds() != null) {
            movie.setActors(actorRepository.findAllById(dto.getActorIds()));
        }

        if (dto.getStreamingPlatformIds() != null) {
            movie.setStreamingPlatforms(streamingPlatformsRepository.findAllById(dto.getStreamingPlatformIds()));
        }

        movieRepository.save(movie);
        }
    }



