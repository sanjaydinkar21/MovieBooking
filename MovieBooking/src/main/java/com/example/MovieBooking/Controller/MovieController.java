package com.example.MovieBooking.Controller;

import com.example.MovieBooking.DTO.MovieDTO;
import com.example.MovieBooking.DTO.MovieDTOForAll;
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
@RequestMapping("/v1/movies")
@Validated
public class MovieController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/getAll")
    public List<MovieDTOForAll> getAllMovies() {
        return bookingService.getAllMovies();
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDTOForAll> getMovieById(@PathVariable("movieId") Long id) {
        Optional<MovieDTOForAll> movie = bookingService.getMovieById(id);
        return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/addMovie")
    public ResponseEntity<MovieDTO> addMovie(@RequestBody MovieDTO movie) {
        Movie savedMovie = bookingService.saveMovie(movie);
        MovieDTO movieDTO = bookingService.convertToMovieDTO(savedMovie);
        return new ResponseEntity<>(movieDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<String> deleteMovieById(@PathVariable("movieId") Long id) {
        bookingService.deleteMovieById(id);
        return ResponseEntity.ok("Deleted movie with ID: " + id);
    }
}
