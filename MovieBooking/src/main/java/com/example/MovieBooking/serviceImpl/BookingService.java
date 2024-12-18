//package com.example.MovieBooking.serviceImpl;
//
//import com.example.MovieBooking.dto.*;
//import com.example.MovieBooking.entity.Movie;
//import com.example.MovieBooking.entity.Show;
//import com.example.MovieBooking.exception.ShowNotFoundException;
//import com.example.MovieBooking.repository.MovieRepo;
//import com.example.MovieBooking.repository.ShowRepo;
//import com.example.MovieBooking.service.ShowService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.format.DateTimeFormatter;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class BookingService implements ShowService {
//
//    @Autowired
//    private MovieRepo movieRepository;
//
//    @Autowired
//    private ShowRepo showRepository;
//
//    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy hh:mm a");
//
////    public List<MovieDTO> getAllMovies() {
////        return movieRepository.findAll().stream().map(this::convertToMovieDTO).collect(Collectors.toList());
////    }
////
////    public Optional<MovieDTO> getMovieById(Long id) {
////        return movieRepository.findById(id).map(this::convertToMovieDTO);
////    }
//
////    @Override
////    public List<MovieDTOForAll> getAllMovies() {
////        return movieRepository.findAll().stream().map(this::convertToMovieDTOForAll).collect(Collectors.toList());
////    }
////
////    @Override
////    public Optional<MovieDTOForAll> getMovieById(Long id) {
////        return Optional.ofNullable(movieRepository.findById(id).map(this::convertToMovieDTOForAll)
////                .orElseThrow(() -> new MovieNotFoundException("Movie not found with id " + id)));
////    }
//
//    @Transactional
//    @Override
//    public Optional<ShowDTOForBooking> bookTicket(Long showId) {
//        Show show = showRepository.findById(showId)
//                .orElseThrow(() -> new ShowNotFoundException("Show not found with id " + showId));
//
//        if (show.getAvailableSeats() == 0) {
//            throw new IllegalArgumentException("All seats are booked");
//        }
//
//        int nextSeat = show.getBookedSeats().size() + 1;
//        show.getBookedSeats().add(nextSeat);
//        show.setAvailableSeats(show.getAvailableSeats() - 1);
//        showRepository.save(show);
//
//        ShowDTOForBooking showDTO = convertToShowDTOForBooking(show);
//        showDTO.setStatus("Booked");
//        showDTO.setSeatNumber(nextSeat);
//        return Optional.of(showDTO);
//    }
//
//    @Transactional
//    @Override
//    public Optional<ShowDTOForBooking> cancelTicket(Long showId) {
//        Show show = showRepository.findById(showId)
//                .orElseThrow(() -> new ShowNotFoundException("Show not found with id " + showId));
//
//        if (show.getBookedSeats().isEmpty()) {
//            throw new IllegalArgumentException("You are trying to cancel an available seat");
//        }
//
//        show.setAvailableSeats(show.getAvailableSeats() + 1);
//        show.getBookedSeats().remove(show.getBookedSeats().size() - 1);
//        showRepository.save(show);
//
//        ShowDTOForBooking showDTO = convertToShowDTOForBooking(show);
//        showDTO.setStatus("Cancelled");
//        return Optional.of(showDTO);
//    }
////
////    @Transactional
////    public Optional<ShowDTOForBooking> cancelTicket(Long showId) {
////        return Optional.empty();
//////        Show show = showRepository.findById(showId)
//////                .orElseThrow(() -> new ShowNotFoundException("Show not found with id " + showId));
//////        show.setAvailableSeats(show.getAvailableSeats() + 1);
//////        if (!show.getBookedSeats().isEmpty()) {
//////            show.getBookedSeats().remove(show.getBookedSeats().size() - 1);
//////        }
//////        showRepository.save(show);
//////        ShowDTOForBooking showDTO = convertToShowDTOForBooking(show);
//////        showDTO.setStatus("Cancelled");
//////        return Optional.of(showDTO);
////    }
////
////    @Transactional
////    @Override
////    public Movie saveMovie(MovieDTO movieDTO) {
////        Movie movie = new Movie();
////        movie.setTitle(movieDTO.getTitle());
////        movie.setGenre(movieDTO.getGenre());
////        movie.setShows(movieDTO.getShows().stream().map(this::convertToShow).collect(Collectors.toList()));
////        for (Show show : movie.getShows()) {
////            show.setMovie(movie);
////        }
////        return movieRepository.save(movie);
////    }
//
//    @Transactional
//    @Override
//    public void deleteShowById(Long showId) {
//        if (!showRepository.existsById(showId)) {
//            throw new ShowNotFoundException("Show not found with id " + showId);
//        }
//        showRepository.deleteById(showId);
//    }
//
////    @Transactional
////    @Override
////    public void deleteMovieById(Long movieId) {
////        if (!movieRepository.existsById(movieId)) {
////            throw new MovieNotFoundException("Movie not found with id " + movieId);
////        }
////        movieRepository.deleteById(movieId);
////    }
//
//    public MovieDTO convertToMovieDTO(Movie movie) {
//        MovieDTO movieDTO = new MovieDTO();
//        movieDTO.setId(movie.getId());
//        movieDTO.setTitle(movie.getTitle());
//        movieDTO.setGenre(movie.getGenre());
//        movieDTO.setShows(movie.getShows().stream().map(this::convertToShowDTO).collect(Collectors.toList()));
//        return movieDTO;
//    }
//
//    private ShowDTO convertToShowDTO(Show show) {
//        ShowDTO showDTO = new ShowDTO();
//        showDTO.setId(show.getId());
//        showDTO.setShowTime(show.getShowTime());
//        showDTO.setAvailableSeats(show.getAvailableSeats());
//        showDTO.setBookedSeats(show.getBookedSeats());
//        return showDTO;
//    }
//
//    private ShowDTOForBooking convertToShowDTOForBooking(Show show) {
//        ShowDTOForBooking showDTO = new ShowDTOForBooking();
//        showDTO.setId(show.getId());
//        showDTO.setShowTime(show.getShowTime());
//        showDTO.setAvailableSeats(show.getAvailableSeats());
//        showDTO.setBookedSeats(show.getBookedSeats());
//        return showDTO;
//    }
//
//    private Show convertToShow(ShowDTO showDTO) {
//        Show show = new Show();
//        show.setShowTime(showDTO.getShowTime()); // Set LocalDateTime directly
//        show.setAvailableSeats(showDTO.getAvailableSeats());
//        show.setBookedSeats(showDTO.getBookedSeats());
//        return show;
//    }
//
//    private MovieDTOForAll convertToMovieDTOForAll(Movie movie) {
//        MovieDTOForAll movieDTO = new MovieDTOForAll();
//        movieDTO.setId(movie.getId());
//        movieDTO.setTitle(movie.getTitle());
//        movieDTO.setGenre(movie.getGenre());
//        movieDTO.setShows(movie.getShows().stream().map(this::convertToShowDTOForAll).collect(Collectors.toList()));
//        return movieDTO;
//    }
//
//    private ShowDTOForAll convertToShowDTOForAll(Show show) {
//        ShowDTOForAll showDTO = new ShowDTOForAll();
//        showDTO.setId(show.getId());
//        showDTO.setShowTime(show.getShowTime());
//        showDTO.setAvailableSeats(show.getAvailableSeats());
//        showDTO.setBookedSeats(show.getBookedSeats());
//        return showDTO;
//    }
//}
