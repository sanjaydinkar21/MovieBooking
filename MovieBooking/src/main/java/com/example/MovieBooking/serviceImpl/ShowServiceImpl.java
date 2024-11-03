package com.example.MovieBooking.serviceImpl;

import com.example.MovieBooking.dto.ShowDTOForBooking;
import com.example.MovieBooking.entity.Show;
import com.example.MovieBooking.exception.ShowNotFoundException;
import com.example.MovieBooking.mapper.ShowMapper;
import com.example.MovieBooking.repository.ShowRepo;
import com.example.MovieBooking.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ShowServiceImpl implements ShowService {

    @Autowired
    private ShowRepo showRepository;

    @Transactional
    @Override
    public Optional<ShowDTOForBooking> bookTicket(Long showId) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ShowNotFoundException("Show not found with id " + showId));

        if (show.getAvailableSeats() == 0) {
            throw new IllegalArgumentException("All seats are booked");
        }

        int nextSeat = show.getBookedSeats().size() + 1;
        show.getBookedSeats().add(nextSeat);
        show.setAvailableSeats(show.getAvailableSeats() - 1);
        showRepository.save(show);

        ShowDTOForBooking showDTO = ShowMapper.convertToShowDTOForBooking(show);
        showDTO.setStatus("Booked");
        showDTO.setSeatNumber(nextSeat);
        return Optional.of(showDTO);
    }

    @Transactional
    @Override
    public Optional<ShowDTOForBooking> cancelTicket(Long showId) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ShowNotFoundException("Show not found with id " + showId));

        if (show.getBookedSeats().isEmpty()) {
            throw new IllegalArgumentException("You are trying to cancel an available seat");
        }

        show.setAvailableSeats(show.getAvailableSeats() + 1);
        show.getBookedSeats().remove(show.getBookedSeats().size() - 1);
        showRepository.save(show);

        ShowDTOForBooking showDTO = ShowMapper.convertToShowDTOForBooking(show);
        showDTO.setStatus("Cancelled");
        return Optional.of(showDTO);
    }

    @Transactional
    @Override
    public void deleteShowById(Long showId) {
        if (!showRepository.existsById(showId)) {
            throw new ShowNotFoundException("Show not found with id " + showId);
        }
        showRepository.deleteById(showId);
    }
}
