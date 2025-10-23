package com.kliuieva.service;

import com.kliuieva.dto.MovieDTO;
import com.kliuieva.entity.Actor;
import com.kliuieva.entity.Movie;
import com.kliuieva.entity.StreamingPlatform;
import com.kliuieva.repositories.ActorRepository;
import com.kliuieva.repositories.MovieRepository;
import com.kliuieva.repositories.StreamingPlatformsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;
    
    @Mock
    private ActorRepository actorRepository;
    
    @Mock
    private StreamingPlatformsRepository streamingPlatformsRepository;
    
    @InjectMocks
    private MovieService movieService;
    
    private Movie testMovie;
    private MovieDTO testMovieDTO;
    private Actor testActor;
    private StreamingPlatform testPlatform;

    @BeforeEach
    void setUp() {
        testActor = new Actor(1L, "Test Actor");
        testPlatform = new StreamingPlatform(1L, "Netflix", "Streaming service");
        
        testMovie = new Movie();
        testMovie.setId(1L);
        testMovie.setName("Test Movie");
        testMovie.setGenre("Action");
        testMovie.setDirector("Test Director");
        testMovie.setActors(Arrays.asList(testActor));
        testMovie.setStreamingPlatforms(Arrays.asList(testPlatform));
        
        testMovieDTO = new MovieDTO("Test Movie", "2023", "Action", "Test Director", 
                                   Arrays.asList(1L), "Test description", Arrays.asList(1L), "8.5", "test.jpg");
    }

    @Test
    void shouldReturnAllMoviesWhenGetAllMoviesCalled() {
        List<Movie> expectedMovies = Arrays.asList(testMovie);
        when(movieRepository.findAll()).thenReturn(expectedMovies);

        List<Movie> result = movieService.getAllMovies();

        assertEquals(expectedMovies, result);
        verify(movieRepository).findAll();
    }

    @Test
    void shouldReturnMovieWhenGetMovieByIdCalledWithValidId() {
        when(movieRepository.findById(1L)).thenReturn(Optional.of(testMovie));

        Movie result = movieService.getMovieById(1L);

        assertEquals(testMovie, result);
        verify(movieRepository).findById(1L);
    }

    @Test
    void shouldSaveMovieWhenSaveCalledWithValidDTO() {
        when(actorRepository.findAllById(Arrays.asList(1L))).thenReturn(Arrays.asList(testActor));
        when(streamingPlatformsRepository.findAllById(Arrays.asList(1L))).thenReturn(Arrays.asList(testPlatform));
        when(movieRepository.save(any(Movie.class))).thenReturn(testMovie);

        Movie result = movieService.save(testMovieDTO);

        assertNotNull(result);
        verify(movieRepository).save(any(Movie.class));
        verify(actorRepository).findAllById(Arrays.asList(1L));
        verify(streamingPlatformsRepository).findAllById(Arrays.asList(1L));
    }

    @Test
    void shouldDeleteMovieWhenDeleteMovieCalledWithValidId() {
        movieService.deleteMovie(1L);

        verify(movieRepository).deleteById(1L);
    }

    @Test
    void shouldUpdateMovieWhenEditMovieCalledWithValidData() {
        when(movieRepository.findById(1L)).thenReturn(Optional.of(testMovie));
        when(actorRepository.findAllById(Arrays.asList(1L))).thenReturn(Arrays.asList(testActor));
        when(streamingPlatformsRepository.findAllById(Arrays.asList(1L))).thenReturn(Arrays.asList(testPlatform));

        movieService.editMovie(testMovieDTO, 1L);

        verify(movieRepository).findById(1L);
        verify(movieRepository).save(testMovie);
    }

    @Test
    void shouldThrowExceptionWhenEditMovieCalledWithInvalidId() {
        when(movieRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> movieService.editMovie(testMovieDTO, 999L));
    }
}