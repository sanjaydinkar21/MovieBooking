package com.example.MovieBooking.Controller;

import com.example.MovieBooking.DTO.ShowDTOForBooking;
import com.example.MovieBooking.ServiceImpl.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/shows")
@Validated
public class ShowController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/{showId}/book")
    public ResponseEntity<ShowDTOForBooking> bookTicket(@PathVariable("showId") Long id) {
        Optional<ShowDTOForBooking> show = bookingService.bookTicket(id);
        return show.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{showId}/cancel")
    public ResponseEntity<ShowDTOForBooking> cancelTicket(@PathVariable("showId") Long id) {
        Optional<ShowDTOForBooking> show = bookingService.cancelTicket(id);
        return show.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{showId}")
    public ResponseEntity<String> deleteShowById(@PathVariable("showId") Long id) {
        bookingService.deleteShowById(id);
        return ResponseEntity.ok("Deleted show with ID: " + id);
    }

}
