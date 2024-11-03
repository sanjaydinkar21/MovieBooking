package com.example.MovieBooking.controller;

import com.example.MovieBooking.dto.MovieDTO;
import com.example.MovieBooking.dto.MovieDTOForAll;
import com.example.MovieBooking.entity.Movie;
import com.example.MovieBooking.mapper.MovieMapper;
import com.example.MovieBooking.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/movies")
@Validated
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/getAll")
    public List<MovieDTOForAll> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDTOForAll> getMovieById(@PathVariable("movieId") Long id) {
        Optional<MovieDTOForAll> movie = movieService.getMovieById(id);
        return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/addMovie")
    public ResponseEntity<MovieDTO> addMovie(@RequestBody MovieDTO movie) {
        Movie savedMovie = movieService.saveMovie(movie);
        MovieDTO movieDTO = MovieMapper.convertToMovieDTO(savedMovie);
        return new ResponseEntity<>(movieDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<String> deleteMovieById(@PathVariable("movieId") Long id) {
        movieService.deleteMovieById(id);
        return ResponseEntity.ok("Deleted movie with ID: " + id);
    }
}
