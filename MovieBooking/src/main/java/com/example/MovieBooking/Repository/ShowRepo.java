package com.example.MovieBooking.Repository;


import com.example.MovieBooking.Entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ShowRepo extends JpaRepository<Show, Long> {
}
