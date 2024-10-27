package com.example.MovieBooking.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ShowDTOForAll {
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMM yyyy, HH:mm")
    private LocalDateTime showTime;

    private int availableSeats;
    private List<Integer> bookedSeats;
}