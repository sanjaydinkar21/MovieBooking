package com.example.MovieBooking.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Data
public class MovieDTO {

    private Long id;

    @NotNull
    @Size(min = 1, message = "Title must be at least 1 character long")
    private String title;

    @NotNull
    @Size(min = 1, message = "Genre must be at least 1 character long")
    private String genre;

    @NotNull
    @Size(min = 1, message = "There must be at least one show")
    private List<ShowDTO> shows;
//    private Long id;
//    private String title;
//    private String genre;
//    private List<ShowDTO> shows;
}
