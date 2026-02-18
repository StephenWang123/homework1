package org.example.homework1.controller;

import org.springframework.web.bind.annotation.*;

import org.example.homework1.service.MovieService;
import org.example.homework1.dto.MovieDto;
import java.util.List;


@RestController
@RequestMapping("/movies")
class MovieController {
    private final MovieService movieService;

    // Constructor Injection (clean and professional)
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDto> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping(params = {"Title", "Year", "page"})
    public List<MovieDto> getMoviesWithParams(
            @RequestParam int page,
            @RequestParam String Title,
            @RequestParam Integer Year
    ) {
        return movieService.getMoviesWithParams(page, Title, Year);
    }
}
