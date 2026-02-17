package org.example.homework1.controller;

import org.springframework.web.bind.annotation.*;

import org.example.homework1.service.MovieService;
import org.example.homework1.dto.MovieDto;
import java.util.List;


@RestController
@RequestMapping("/")
class MovieController {
    private final MovieService movieService;

    // Constructor Injection (clean and professional)
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * Method 1:
     * Get ALL movies (no query parameters)
     */
    @GetMapping
    public List<MovieDto> getAllMovies() {
        return movieService.getAllMovies();
    }

    /**
     * Method 2:
     * Get movies with query parameters
     */
    @GetMapping(params = {"Title", "Year", "page"})
    public List<MovieDto> getMoviesWithParams(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) String Title,
            @RequestParam(required = false) Integer Year
    ) {
        return movieService.getMoviesWithParams(page, Title, Year);
    }
}
