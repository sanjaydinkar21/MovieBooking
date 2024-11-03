package com.example.MovieBooking.mapper;

import com.example.MovieBooking.dto.MovieDTO;
import com.example.MovieBooking.dto.MovieDTOForAll;
import com.example.MovieBooking.entity.Movie;
import com.example.MovieBooking.entity.Show;

import java.util.stream.Collectors;

public class MovieMapper {
    public static MovieDTO convertToMovieDTO(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setGenre(movie.getGenre());
        movieDTO.setShows(movie.getShows().stream().map(ShowMapper::convertToShowDTO).collect(Collectors.toList()));
        return movieDTO;
    }
    public static MovieDTOForAll convertToMovieDTOForAll(Movie movie) {
        MovieDTOForAll movieDTO = new MovieDTOForAll();
        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setGenre(movie.getGenre());
        movieDTO.setShows(movie.getShows().stream().map(ShowMapper::convertToShowDTOForAll).collect(Collectors.toList()));
        return movieDTO;
    }


}
