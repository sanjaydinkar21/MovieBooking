package com.example.MovieBooking.Controller;

import com.example.MovieBooking.DTO.ShowDTOForBooking;
import com.example.MovieBooking.ServiceImpl.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/shows")
@Validated
public class ShowController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/{id}/book")
    public ResponseEntity<ShowDTOForBooking> bookTicket(@PathVariable Long id) {
        Optional<ShowDTOForBooking> show = bookingService.bookTicket(id);
        return show.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<ShowDTOForBooking> cancelTicket(@PathVariable Long id) {
        Optional<ShowDTOForBooking> show = bookingService.cancelTicket(id);
        return show.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
