package com.example.search.component;

import com.example.search.entity.Flight;
import com.example.search.entity.Inventory;
import com.example.search.repository.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchComponent {
    private FlightRepository flightRepository;
    private static final Logger log = LoggerFactory.getLogger(SearchComponent.class);

    @Autowired
    public SearchComponent(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> search(SearchQuery query) {
        List<Flight> flights = flightRepository.findByOriginAndDestinationAndFlightDate(query.getOrigin(), query.getDestination(), query.getFlightDate());

        List<Flight> result = new ArrayList<Flight>();
        result.addAll(flights);
        flights.forEach(flight -> {
            flight.getFares();
            int inv = flight.getInventory().getCount();
            if(inv < 0) {
                result.remove(flight);
            }
        });

        return flights;
    }

    public void updateInventory(String flightNumber, String flightDate, int inventory) {
        log.info("Updating inventory for flight " + flightNumber + " inventory " + inventory);

        Flight flight = flightRepository.findByFlightNumberAndFlightDate(flightNumber, flightDate);
        Inventory inv = flight.getInventory();
        inv.setCount(inventory);

        flightRepository.save(flight);
    }
}
