package com.example.MovieBooking.DTO;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ShowDTOForBooking {
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMMM dd, yyyy hh:mm a")
    private LocalDateTime showTime;

    private int availableSeats;
    private List<Integer> bookedSeats;
    private String status;
    private Integer seatNumber;
}
