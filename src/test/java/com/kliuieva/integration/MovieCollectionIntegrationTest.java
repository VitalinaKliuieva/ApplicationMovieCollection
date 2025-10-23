package com.kliuieva.integration;

import com.kliuieva.entity.Actor;
import com.kliuieva.entity.Movie;
import com.kliuieva.entity.StreamingPlatform;
import com.kliuieva.repositories.ActorRepository;
import com.kliuieva.repositories.MovieRepository;
import com.kliuieva.repositories.StreamingPlatformsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class MovieCollectionIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private StreamingPlatformsRepository streamingPlatformsRepository;

    @Test
    void shouldCreateCompleteMovieWhenAllDataProvided() {
        Actor actor = new Actor();
        actor.setName("Integration Actor");
        actor = actorRepository.save(actor);

        StreamingPlatform platform = new StreamingPlatform();
        platform.setName("Integration Platform");
        platform.setDescription("Test platform");
        platform = streamingPlatformsRepository.save(platform);

        Movie movie = new Movie();
        movie.setName("Integration Movie");
        movie.setGenre("Integration Genre");
        movie.setDirector("Integration Director");
        movie.setDate("2023");
        movie.setDescription("Integration test movie");
        movie.setRating("9.0");
        movie.setImage("integration.jpg");
        movie.setActors(Arrays.asList(actor));
        movie.setStreamingPlatforms(Arrays.asList(platform));

        Movie savedMovie = movieRepository.save(movie);

        assertNotNull(savedMovie.getId());
        assertEquals("Integration Movie", savedMovie.getName());
        assertEquals(1, savedMovie.getActors().size());
        assertEquals(1, savedMovie.getStreamingPlatforms().size());
    }

    @Test
    void shouldAccessMoviesPageWhenApplicationRunning() throws Exception {
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(view().name("movies"))
                .andExpect(model().attributeExists("movies"))
                .andExpect(model().attributeExists("actors"))
                .andExpect(model().attributeExists("streamingPlatforms"));
    }

    @Test
    void shouldPersistDataWhenRepositoriesUsed() {
        Actor actor = actorRepository.save(new Actor(null, "Persistent Actor"));
        StreamingPlatform platform = streamingPlatformsRepository.save(
            new StreamingPlatform(null, "Persistent Platform", "Test description"));

        assertNotNull(actor.getId());
        assertNotNull(platform.getId());

        List<Actor> actors = actorRepository.findAll();
        List<StreamingPlatform> platforms = streamingPlatformsRepository.findAll();

        assertTrue(actors.size() > 0);
        assertTrue(platforms.size() > 0);
    }

    @Test
    void shouldMaintainDataIntegrityWhenMovieDeletedWithRelationships() {
        Actor actor = actorRepository.save(new Actor(null, "Relationship Actor"));
        StreamingPlatform platform = streamingPlatformsRepository.save(
            new StreamingPlatform(null, "Relationship Platform", "Test"));

        Movie movie = new Movie();
        movie.setName("Relationship Movie");
        movie.setGenre("Test");
        movie.setDirector("Test Director");
        movie.setActors(Arrays.asList(actor));
        movie.setStreamingPlatforms(Arrays.asList(platform));
        movie = movieRepository.save(movie);

        Long movieId = movie.getId();
        Long actorId = actor.getId();
        Long platformId = platform.getId();

        movieRepository.deleteById(movieId);

        assertFalse(movieRepository.findById(movieId).isPresent());
        assertTrue(actorRepository.findById(actorId).isPresent());
        assertTrue(streamingPlatformsRepository.findById(platformId).isPresent());
    }
}