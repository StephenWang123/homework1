package org.example.homework1.service;

import org.example.homework1.dto.MovieDto;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    // Method 1: No query parameters (fetch all pages later)
    public List<MovieDto> getAllMovies() {
        return new ArrayList<>();
    }

    // Method 2: With query parameters
    public List<MovieDto> getMoviesWithParams(
            Integer page,
            String Title,
            Integer Year
    ) {
        return new ArrayList<>();
    }
}
