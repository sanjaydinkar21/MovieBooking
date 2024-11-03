package com.example.MovieBooking.dto;

import lombok.Data;
import java.util.List;

@Data
public class MovieDTOForAll {
    private Long id;
    private String title;
    private String genre;
    private List<ShowDTOForAll> shows;
}