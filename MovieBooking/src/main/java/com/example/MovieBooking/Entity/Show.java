package com.example.MovieBooking.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SHOW_TIMES")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime showTime;
    private String status;
    private int availableSeats;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ElementCollection
    @CollectionTable(name = "BOOKED_SEATS", joinColumns = @JoinColumn(name = "show_id"))
    @Column(name = "seat_number")
    private List<Integer> bookedSeats = new ArrayList<>();
}