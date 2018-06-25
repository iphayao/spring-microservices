package com.example.fare.component;

import com.example.fare.entity.Fare;
import com.example.fare.repository.FareRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FareComponent {
    private static final Logger log = LoggerFactory.getLogger(FareComponent.class);

    FareRepository fareRepository;

    public FareComponent() {
    }

    @Autowired
    public FareComponent(FareRepository fareRepository) {
        this.fareRepository = fareRepository;
    }

    public Fare getFare(String flightNumber, String flightDate) {
        log.info("Looking for fare flightNumber " + flightNumber + " flightDate " + flightDate);
        return fareRepository.getFareByFlightNumberAndFlightDate(flightNumber, flightDate);
    }
}
