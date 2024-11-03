package com.example.MovieBooking.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowDTOForAdding {

    @Schema(description = "Show time in format 'MMMM dd, yyyy hh:mm a'", example = "October 28, 2024 01:03 PM")
    private LocalDateTime showTime;

    @Schema(description = "Number of available seats")
    private int availableSeats;
}