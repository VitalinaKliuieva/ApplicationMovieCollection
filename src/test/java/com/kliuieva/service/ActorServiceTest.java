package com.kliuieva.service;

import com.kliuieva.entity.Actor;
import com.kliuieva.repositories.ActorRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActorServiceTest {

    @Mock
    private ActorRepository actorRepository;
    
    @InjectMocks
    private ActorService actorService;
    
    private Actor testActor;

    @BeforeEach
    void setUp() {
        testActor = new Actor(1L, "Test Actor");
    }

    @Test
    void shouldReturnAllActorsWhenGetAllActorsCalled() {
        List<Actor> expectedActors = Arrays.asList(testActor);
        when(actorRepository.findAll()).thenReturn(expectedActors);

        List<Actor> result = actorService.getAllActors();

        assertEquals(expectedActors, result);
        verify(actorRepository).findAll();
    }

    @Test
    void shouldReturnActorWhenFindByIdCalledWithValidId() {
        when(actorRepository.findById(1L)).thenReturn(Optional.of(testActor));

        Optional<Actor> result = actorService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(testActor, result.get());
        verify(actorRepository).findById(1L);
    }

    @Test
    void shouldReturnEmptyWhenFindByIdCalledWithInvalidId() {
        when(actorRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Actor> result = actorService.findById(999L);

        assertFalse(result.isPresent());
        verify(actorRepository).findById(999L);
    }

    @Test
    void shouldSaveActorWhenSaveActorCalledWithValidActor() {
        when(actorRepository.save(testActor)).thenReturn(testActor);

        Actor result = actorService.saveActor(testActor);

        assertEquals(testActor, result);
        verify(actorRepository).save(testActor);
    }

    @Test
    void shouldDeleteActorWhenDeleteActorCalledWithValidId() {
        actorService.deleteActor(1L);

        verify(actorRepository).deleteById(1L);
    }
}