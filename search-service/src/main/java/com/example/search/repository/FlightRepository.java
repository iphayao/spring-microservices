package com.example.search.repository;

import com.example.search.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByOriginAndDestinationAndFlightDate(String origin, String destination, String flightDate);
    Flight findByFlightNumberAndFlightDate(String flightNumber, String flightDate);
}
