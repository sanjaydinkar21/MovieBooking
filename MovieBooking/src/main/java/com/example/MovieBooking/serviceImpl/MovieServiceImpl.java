package com.example.MovieBooking.serviceImpl;

import com.example.MovieBooking.dto.*;
import com.example.MovieBooking.entity.Movie;
import com.example.MovieBooking.entity.Show;
import com.example.MovieBooking.exception.MovieNotFoundException;
import com.example.MovieBooking.mapper.MovieMapper;
import com.example.MovieBooking.mapper.ShowMapper;
import com.example.MovieBooking.repository.MovieRepo;
import com.example.MovieBooking.repository.ShowRepo;
import com.example.MovieBooking.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepo movieRepository;

    @Autowired
    private ShowRepo showRepository;

    @Override
    public List<MovieDTOForAll> getAllMovies() {
        return movieRepository.findAll().stream().map(MovieMapper::convertToMovieDTOForAll).collect(Collectors.toList());
    }

    @Override
    public Optional<MovieDTOForAll> getMovieById(Long id) {
        return Optional.ofNullable(movieRepository.findById(id).map(MovieMapper::convertToMovieDTOForAll)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with id " + id)));
    }
    @Transactional
    @Override
    public void deleteMovieById(Long movieId) {
        if (!movieRepository.existsById(movieId)) {
            throw new MovieNotFoundException("Movie not found with id " + movieId);
        }
        movieRepository.deleteById(movieId);
    }

    @Transactional
    @Override
    public Movie saveMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setTitle(movieDTO.getTitle());
        movie.setGenre(movieDTO.getGenre());
        movie.setShows(movieDTO.getShows().stream().map(ShowMapper::convertToShow).collect(Collectors.toList()));
        for (Show show : movie.getShows()) {
            show.setMovie(movie);
        }
        return movieRepository.save(movie);
    }

//    private Show convertToShow(ShowDTO showDTO) {
//        Show show = new Show();
//        show.setShowTime(showDTO.getShowTime()); // Set LocalDateTime directly
//        show.setAvailableSeats(showDTO.getAvailableSeats());
//        show.setBookedSeats(showDTO.getBookedSeats());
//        return show;
//    }

//    private MovieDTOForAll convertToMovieDTOForAll(Movie movie) {
//        MovieDTOForAll movieDTO = new MovieDTOForAll();
//        movieDTO.setId(movie.getId());
//        movieDTO.setTitle(movie.getTitle());
//        movieDTO.setGenre(movie.getGenre());
//        movieDTO.setShows(movie.getShows().stream().map(this::convertToShowDTOForAll).collect(Collectors.toList()));
//        return movieDTO;
//    }

//    private ShowDTOForAll convertToShowDTOForAll(Show show) {
//        ShowDTOForAll showDTO = new ShowDTOForAll();
//        showDTO.setId(show.getId());
//        showDTO.setShowTime(show.getShowTime());
//        showDTO.setAvailableSeats(show.getAvailableSeats());
//        showDTO.setBookedSeats(show.getBookedSeats());
//        return showDTO;
//    }
//    public MovieDTO convertToMovieDTO(Movie movie) {
//        MovieDTO movieDTO = new MovieDTO();
//        movieDTO.setId(movie.getId());
//        movieDTO.setTitle(movie.getTitle());
//        movieDTO.setGenre(movie.getGenre());
//        movieDTO.setShows(movie.getShows().stream().map(this::convertToShowDTO).collect(Collectors.toList()));
//        return movieDTO;
//    }
//    private ShowDTO convertToShowDTO(Show show) {
//        ShowDTO showDTO = new ShowDTO();
//        showDTO.setId(show.getId());
//        showDTO.setShowTime(show.getShowTime());
//        showDTO.setAvailableSeats(show.getAvailableSeats());
//        showDTO.setBookedSeats(show.getBookedSeats());
//        return showDTO;
//    }
}
