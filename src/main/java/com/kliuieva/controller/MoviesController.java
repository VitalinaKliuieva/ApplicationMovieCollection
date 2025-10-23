package com.kliuieva.controller;

import com.kliuieva.dto.MovieDTO;
import com.kliuieva.service.ActorService;
import com.kliuieva.service.MovieService;
import com.kliuieva.service.StreamingPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class MoviesController {

    private final MovieService movieService;
    private final ActorService actorService;
    private final StreamingPlatformService streamingPlatformService;

    @Autowired
    public MoviesController(MovieService movieService, ActorService actorService, StreamingPlatformService streamingPlatformService) {
        this.movieService = movieService;
        this.actorService = actorService;
        this.streamingPlatformService = streamingPlatformService;

    }

    @GetMapping("movies")
    public String getAll(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        model.addAttribute("actors", actorService.getAllActors());
        model.addAttribute("streamingPlatforms", streamingPlatformService.getAllPlatforms());

        return "movies";
    }


    @PostMapping("movies")
    public String addMovie(@ModelAttribute MovieDTO movieDTO, Model model) {
        movieService.save(movieDTO);
        model.addAttribute("movies", movieService.getAllMovies());
        model.addAttribute("actors", actorService.getAllActors());
        model.addAttribute("streamingPlatforms", streamingPlatformService.getAllPlatforms());

        return "movies";
    }

    @PostMapping("movies/edit")
    public String editMovie( Long id, @ModelAttribute MovieDTO movieDTO, Model model) {
        movieService.editMovie(movieDTO, id);
        return "redirect:/movies";
    }

    @PostMapping("movies/delete")
    public String deleteMovie(Long id) {
        movieService.deleteMovie(id);
        return "redirect:/movies";
    }
}
