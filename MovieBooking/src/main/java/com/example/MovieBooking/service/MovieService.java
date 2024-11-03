package com.example.MovieBooking.service;

import com.example.MovieBooking.dto.MovieDTO;
import com.example.MovieBooking.dto.MovieDTOForAll;
import com.example.MovieBooking.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
        List<MovieDTOForAll> getAllMovies();
    Optional<MovieDTOForAll> getMovieById(Long id);
        void deleteMovieById(Long movieId);
    Movie saveMovie(MovieDTO movieDTO);

}
