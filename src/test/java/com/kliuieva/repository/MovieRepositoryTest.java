package com.kliuieva.repository;

import com.kliuieva.entity.Actor;
import com.kliuieva.entity.Movie;
import com.kliuieva.entity.StreamingPlatform;
import com.kliuieva.repositories.ActorRepository;
import com.kliuieva.repositories.MovieRepository;
import com.kliuieva.repositories.StreamingPlatformsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private StreamingPlatformsRepository streamingPlatformsRepository;

    private Movie testMovie;
    private Actor testActor;
    private StreamingPlatform testPlatform;

    @BeforeEach
    void setUp() {
        movieRepository.deleteAll();
        
        testActor = new Actor();
        testActor.setName("Test Actor");
        testActor = actorRepository.save(testActor);

        testPlatform = new StreamingPlatform();
        testPlatform.setName("Netflix");
        testPlatform.setDescription("Streaming service");
        testPlatform = streamingPlatformsRepository.save(testPlatform);

        testMovie = new Movie();
        testMovie.setName("Test Movie");
        testMovie.setGenre("Action");
        testMovie.setDirector("Test Director");
        testMovie.setDate("2023");
        testMovie.setDescription("Test description");
        testMovie.setRating("8.5");
        testMovie.setImage("test.jpg");
        testMovie.setActors(Arrays.asList(testActor));
        testMovie.setStreamingPlatforms(Arrays.asList(testPlatform));
    }

    @Test
    void shouldSaveMovieWhenSaveCalledWithValidMovie() {
        Movie savedMovie = movieRepository.save(testMovie);

        assertNotNull(savedMovie.getId());
        assertEquals("Test Movie", savedMovie.getName());
        assertEquals("Action", savedMovie.getGenre());
        assertEquals("Test Director", savedMovie.getDirector());
    }

    @Test
    void shouldFindMovieWhenFindByIdCalledWithValidId() {
        Movie savedMovie = movieRepository.save(testMovie);

        Optional<Movie> foundMovie = movieRepository.findById(savedMovie.getId());

        assertTrue(foundMovie.isPresent());
        assertEquals(savedMovie.getName(), foundMovie.get().getName());
    }

    @Test
    void shouldReturnEmptyWhenFindByIdCalledWithInvalidId() {
        Optional<Movie> foundMovie = movieRepository.findById(999L);

        assertFalse(foundMovie.isPresent());
    }

    @Test
    void shouldReturnAllMoviesWhenFindAllCalled() {
        movieRepository.save(testMovie);
        
        Movie anotherMovie = new Movie();
        anotherMovie.setName("Another Movie");
        anotherMovie.setGenre("Drama");
        anotherMovie.setDirector("Another Director");
        movieRepository.save(anotherMovie);

        List<Movie> movies = movieRepository.findAll();

        assertEquals(2, movies.size());
    }

    @Test
    void shouldDeleteMovieWhenDeleteByIdCalledWithValidId() {
        Movie savedMovie = movieRepository.save(testMovie);
        Long movieId = savedMovie.getId();

        movieRepository.deleteById(movieId);

        Optional<Movie> deletedMovie = movieRepository.findById(movieId);
        assertFalse(deletedMovie.isPresent());
    }

    @Test
    void shouldMaintainRelationshipsWhenMovieSavedWithActorsAndPlatforms() {
        Movie savedMovie = movieRepository.save(testMovie);

        Optional<Movie> foundMovie = movieRepository.findById(savedMovie.getId());
        assertTrue(foundMovie.isPresent());
        assertEquals(1, foundMovie.get().getActors().size());
        assertEquals(1, foundMovie.get().getStreamingPlatforms().size());
        assertEquals("Test Actor", foundMovie.get().getActors().get(0).getName());
        assertEquals("Netflix", foundMovie.get().getStreamingPlatforms().get(0).getName());
    }
}