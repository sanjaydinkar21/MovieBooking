package com.example.MovieBooking.controller;

import com.example.MovieBooking.dto.ShowDTOForBooking;
import com.example.MovieBooking.service.ShowService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/shows")
@Validated
public class ShowController {


    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @PostMapping("/booking-show/{showId}")
    public ResponseEntity<ShowDTOForBooking> bookTicket(@PathVariable("showId") Long id) {
        Optional<ShowDTOForBooking> show = showService.bookTicket(id);
        return show.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/cancel-show/{showId}")
    public ResponseEntity<ShowDTOForBooking> cancelTicket(@PathVariable("showId") Long id) {
        Optional<ShowDTOForBooking> show = showService.cancelTicket(id);
        return show.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{showId}")
    public ResponseEntity<String> deleteShowById(@PathVariable("showId") Long id) {
        showService.deleteShowById(id);
        return ResponseEntity.ok("Deleted show with ID: " + id);
    }

}
