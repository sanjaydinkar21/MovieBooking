package com.example.MovieBooking.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ShowDTO {

    private Long id;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMMM dd, yyyy hh:mm a")
    private LocalDateTime showTime;

    @NotNull
    private int availableSeats;
    private List<Integer> bookedSeats;
    private String status;
    private Integer seatNumber;
//    private Long id;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMMM dd, yyyy hh:mm a")
//    private LocalDateTime showTime;
//    private int availableSeats;
//    private List<Integer> bookedSeats;
//    private String status;
//    private Integer seatNumber;
}