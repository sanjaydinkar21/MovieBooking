package com.example.MovieBooking.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Data
public class MovieDTOForAdding {
    @Schema(description = "Title of the movie")
    private String title;

    @Schema(description = "Genre of the movie")
    private String genre;

    @Schema(description = "List of shows associated with the movie")
    private List<ShowDTOForAdding> shows;
}


