package org.example.homework1.service;

import org.example.homework1.dto.MovieDto;
import org.example.homework1.dto.PageDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class MovieService {

    private static final String BASE_URL =
            "https://jsonmock.hackerrank.com/api/moviesdata/search/";

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Method 1:
     * Fetch ALL pages (no filters)
     */
    public List<MovieDto> getAllMovies() {

        List<MovieDto> allMovies = new ArrayList<>();

        // 1️⃣ Fetch first page
        String firstPageUrl = BASE_URL + "?page=1";

        PageDto firstResponse =
                restTemplate.getForObject(firstPageUrl, PageDto.class);

        if (firstResponse == null || firstResponse.data == null) {
            return allMovies;
        }

        allMovies.addAll(firstResponse.data);
        int totalPages = firstResponse.total_pages;

        // 2️⃣ Multithreading for remaining pages
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<List<MovieDto>>> futures = new ArrayList<>();

        for (int i = 2; i <= totalPages; i++) {
            int currentPage = i;

            futures.add(executor.submit(() -> {
                String url = BASE_URL + "?page=" + currentPage;

                PageDto response =
                        restTemplate.getForObject(url, PageDto.class);

                if (response != null && response.data != null) {
                    return response.data;
                }

                return new ArrayList<>();
            }));
        }

        // 3️⃣ Collect results
        for (Future<List<MovieDto>> future : futures) {
            try {
                allMovies.addAll(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();

        return allMovies;
    }

    /**
     * Method 2:
     * Fetch ONE specific page with filters
     */
    public List<MovieDto> getMoviesWithParams(
            int page,
            String Title,
            Integer Year
    ) {

        String url = BASE_URL +
                "?page=" + page +
                "&Title=" + Title +
                "&Year=" + Year;

        PageDto response =
                restTemplate.getForObject(url, PageDto.class);

        if (response != null && response.data != null) {
            return response.data;
        }

        return new ArrayList<>();
    }
}
