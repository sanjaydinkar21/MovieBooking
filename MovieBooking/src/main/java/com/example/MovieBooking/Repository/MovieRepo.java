package com.example.MovieBooking.Repository;

import com.example.MovieBooking.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Long> {
}
