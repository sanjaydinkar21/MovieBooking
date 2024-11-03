package com.example.MovieBooking.repository;


import com.example.MovieBooking.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ShowRepo extends JpaRepository<Show, Long> {
}
