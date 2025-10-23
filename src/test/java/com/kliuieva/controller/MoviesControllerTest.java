package com.kliuieva.controller;

import com.kliuieva.dto.MovieDTO;
import com.kliuieva.entity.Actor;
import com.kliuieva.entity.Movie;
import com.kliuieva.entity.StreamingPlatform;
import com.kliuieva.service.ActorService;
import com.kliuieva.service.MovieService;
import com.kliuieva.service.StreamingPlatformService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MoviesController.class)
class MoviesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @MockBean
    private ActorService actorService;

    @MockBean
    private StreamingPlatformService streamingPlatformService;

    private Movie testMovie;
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
    }

    @Test
    void shouldReturnMoviesPageWhenGetAllMoviesCalled() throws Exception {
        List<Movie> movies = Arrays.asList(testMovie);
        List<Actor> actors = Arrays.asList(testActor);
        List<StreamingPlatform> platforms = Arrays.asList(testPlatform);

        when(movieService.getAllMovies()).thenReturn(movies);
        when(actorService.getAllActors()).thenReturn(actors);
        when(streamingPlatformService.getAllPlatforms()).thenReturn(platforms);

        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(view().name("movies"))
                .andExpect(model().attribute("movies", movies))
                .andExpect(model().attribute("actors", actors))
                .andExpect(model().attribute("streamingPlatforms", platforms));

        verify(movieService).getAllMovies();
        verify(actorService).getAllActors();
        verify(streamingPlatformService).getAllPlatforms();
    }

    @Test
    void shouldAddMovieWhenPostMovieCalledWithValidData() throws Exception {
        when(movieService.save(any(MovieDTO.class))).thenReturn(testMovie);
        when(movieService.getAllMovies()).thenReturn(Arrays.asList(testMovie));
        when(actorService.getAllActors()).thenReturn(Arrays.asList(testActor));
        when(streamingPlatformService.getAllPlatforms()).thenReturn(Arrays.asList(testPlatform));

        mockMvc.perform(post("/movies")
                .param("name", "Test Movie")
                .param("genre", "Action")
                .param("director", "Test Director"))
                .andExpect(status().isOk())
                .andExpect(view().name("movies"));

        verify(movieService).save(any(MovieDTO.class));
    }

    @Test
    void shouldRedirectWhenEditMovieCalledWithValidData() throws Exception {
        mockMvc.perform(post("/movies/edit")
                .param("id", "1")
                .param("name", "Updated Movie")
                .param("genre", "Drama"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/movies"));

        verify(movieService).editMovie(any(MovieDTO.class), eq(1L));
    }

    @Test
    void shouldRedirectWhenDeleteMovieCalledWithValidId() throws Exception {
        mockMvc.perform(post("/movies/delete")
                .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/movies"));

        verify(movieService).deleteMovie(1L);
    }
}