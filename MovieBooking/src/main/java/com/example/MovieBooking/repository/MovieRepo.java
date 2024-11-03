package com.example.MovieBooking.repository;

import com.example.MovieBooking.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Long> {
}
