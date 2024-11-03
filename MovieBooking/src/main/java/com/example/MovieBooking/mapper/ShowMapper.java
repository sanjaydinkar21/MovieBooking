package com.example.MovieBooking.mapper;

import com.example.MovieBooking.dto.ShowDTO;
import com.example.MovieBooking.dto.ShowDTOForAll;
import com.example.MovieBooking.dto.ShowDTOForBooking;
import com.example.MovieBooking.entity.Show;

public class ShowMapper {
    public static Show convertToShow(ShowDTO showDTO) {
        Show show = new Show();
        show.setShowTime(showDTO.getShowTime()); // Set LocalDateTime directly
        show.setAvailableSeats(showDTO.getAvailableSeats());
        show.setBookedSeats(showDTO.getBookedSeats());
        return show;
    }

    public static ShowDTOForAll convertToShowDTOForAll(Show show) {
        ShowDTOForAll showDTO = new ShowDTOForAll();
        showDTO.setId(show.getId());
        showDTO.setShowTime(show.getShowTime());
        showDTO.setAvailableSeats(show.getAvailableSeats());
        showDTO.setBookedSeats(show.getBookedSeats());
        return showDTO;
    }
    public static ShowDTOForBooking convertToShowDTOForBooking(Show show) {
        ShowDTOForBooking showDTO = new ShowDTOForBooking();
        showDTO.setId(show.getId());
        showDTO.setShowTime(show.getShowTime());
        showDTO.setAvailableSeats(show.getAvailableSeats());
        showDTO.setBookedSeats(show.getBookedSeats());
        return showDTO;
    }

    public static ShowDTO convertToShowDTO(Show show) {
        ShowDTO showDTO = new ShowDTO();
        showDTO.setId(show.getId());
        showDTO.setShowTime(show.getShowTime());
        showDTO.setAvailableSeats(show.getAvailableSeats());
        showDTO.setBookedSeats(show.getBookedSeats());
        return showDTO;
    }
}
