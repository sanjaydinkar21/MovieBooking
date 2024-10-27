package com.example.MovieBooking.Controller;

import com.example.MovieBooking.DTO.MovieDTO;
import com.example.MovieBooking.Entity.Movie;
import com.example.MovieBooking.ServiceImpl.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
@Validated
public class MovieController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/getAll")
    public List<MovieDTO> getAllMovies() {
        return bookingService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable Long id) {
        Optional<MovieDTO> movie = bookingService.getMovieById(id);
        return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/addMovie")
    public ResponseEntity<MovieDTO> addMovie(@RequestBody MovieDTO movie) {
        Movie savedMovie = bookingService.saveMovie(movie);
        MovieDTO movieDTO = bookingService.convertToMovieDTO(savedMovie);
        return new ResponseEntity<>(movieDTO, HttpStatus.CREATED);
    }
}
