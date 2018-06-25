package com.example.fare.controller;

import com.example.fare.component.FareComponent;
import com.example.fare.entity.Fare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/fare")
public class FareController {
    FareComponent fareComponent;

    @Autowired
    public FareController(FareComponent fareComponent) {
        this.fareComponent = fareComponent;
    }

    @RequestMapping("/get")
    Fare getFare(@RequestParam(value = "flightNumber") String flightNumber, @RequestParam(value = "flightDate") String flightDate) {
        return fareComponent.getFare(flightNumber, flightDate);
    }
}
