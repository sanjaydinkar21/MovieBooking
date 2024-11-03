package com.example.MovieBooking.service;

import com.example.MovieBooking.dto.ShowDTOForBooking;

import java.util.Optional;

public interface ShowService {
    Optional<ShowDTOForBooking> bookTicket(Long showId);
    Optional<ShowDTOForBooking> cancelTicket(Long showId);
    void deleteShowById(Long showId);
}
