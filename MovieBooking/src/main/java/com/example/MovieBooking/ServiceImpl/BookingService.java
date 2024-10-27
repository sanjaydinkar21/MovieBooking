package com.example.MovieBooking.ServiceImpl;

import com.example.MovieBooking.DTO.MovieDTO;
import com.example.MovieBooking.DTO.ShowDTO;
import com.example.MovieBooking.Entity.Movie;
import com.example.MovieBooking.Entity.Show;
import com.example.MovieBooking.Exception.MovieNotFoundException;
import com.example.MovieBooking.Exception.ShowNotFoundException;
import com.example.MovieBooking.Repository.MovieRepo;
import com.example.MovieBooking.Repository.ShowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private MovieRepo movieRepository;

    @Autowired
    private ShowRepo showRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy hh:mm a");

    public List<MovieDTO> getAllMovies() {
        return movieRepository.findAll().stream().map(this::convertToMovieDTO).collect(Collectors.toList());
    }

    public Optional<MovieDTO> getMovieById(Long id) {
        return Optional.ofNullable(movieRepository.findById(id).map(this::convertToMovieDTO)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with id " + id)));
    }

    @Transactional
    public Optional<ShowDTO> bookTicket(Long showId) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ShowNotFoundException("Show not found with id " + showId));
        if (show.getAvailableSeats() > 0) {
            int nextSeat = show.getBookedSeats().size() + 1;
            show.getBookedSeats().add(nextSeat);
            show.setAvailableSeats(show.getAvailableSeats() - 1);
            showRepository.save(show);
            ShowDTO showDTO = convertToShowDTO(show);
            showDTO.setStatus("Booked");
            showDTO.setSeatNumber(nextSeat);
            return Optional.of(showDTO);
        } else {
            ShowDTO showDTO = new ShowDTO();
            showDTO.setStatus("Failed");
            return Optional.of(showDTO);
        }
    }

    @Transactional
    public Optional<ShowDTO> cancelTicket(Long showId) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ShowNotFoundException("Show not found with id " + showId));
        show.setAvailableSeats(show.getAvailableSeats() + 1);
        if (!show.getBookedSeats().isEmpty()) {
            show.getBookedSeats().remove(show.getBookedSeats().size() - 1);
        }
        showRepository.save(show);
        ShowDTO showDTO = convertToShowDTO(show);
        showDTO.setStatus("Cancelled");
        return Optional.of(showDTO);
    }

    @Transactional
    public Movie saveMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setTitle(movieDTO.getTitle());
        movie.setGenre(movieDTO.getGenre());
        movie.setShows(movieDTO.getShows().stream().map(this::convertToShow).collect(Collectors.toList()));
        for (Show show : movie.getShows()) {
            show.setMovie(movie);
        }
        return movieRepository.save(movie);
    }

    public MovieDTO convertToMovieDTO(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setGenre(movie.getGenre());
        movieDTO.setShows(movie.getShows().stream().map(this::convertToShowDTO).collect(Collectors.toList()));
        return movieDTO;
    }

    private ShowDTO convertToShowDTO(Show show) {
        ShowDTO showDTO = new ShowDTO();
        showDTO.setId(show.getId());
        showDTO.setShowTime(show.getShowTime());
        showDTO.setAvailableSeats(show.getAvailableSeats());
        showDTO.setBookedSeats(show.getBookedSeats());
        return showDTO;
    }

    private Show convertToShow(ShowDTO showDTO) {
        Show show = new Show();
        show.setShowTime(LocalDateTime.parse(showDTO.getShowTime().format(formatter), formatter)); // Parse LocalDateTime correctly
        show.setAvailableSeats(showDTO.getAvailableSeats());
        show.setBookedSeats(showDTO.getBookedSeats());
        return show;
    }
}
