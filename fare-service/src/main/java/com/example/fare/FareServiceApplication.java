package com.example.fare;

import com.example.fare.entity.Fare;
import com.example.fare.repository.FareRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FareServiceApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(FareServiceApplication.class);

	@Autowired
	FareRepository fareRepository;

	public static void main(String[] args) {
		SpringApplication.run(FareServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Fare> fares = new ArrayList<Fare>();
		fares.add(new Fare("BF100","22-JAN-16", "101"));
		fares.add(new Fare("BF101","22-JAN-16", "101"));
		fares.add(new Fare("BF102","22-JAN-16", "102"));
		fares.add(new Fare("BF103","22-JAN-16", "103"));
		fares.add(new Fare("BF104","22-JAN-16", "104"));
		fares.add(new Fare("BF105","22-JAN-16", "105"));
		fares.add(new Fare("BF106","22-JAN-16", "106"));

		fareRepository.saveAll(fares);

		log.info("Looking for Fare...");
		log.info(fareRepository.getFareByFlightNumberAndFlightDate("BF101", "22-JAN-16").toString());

	}
}
