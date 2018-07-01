package com.example.checkin;

import com.example.checkin.entiry.CheckinRecord;
import com.example.checkin.repository.CheckinRepository;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class CheckinServiceApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	CheckinRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(CheckinServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CheckinRecord record = new CheckinRecord("Franc", "Gean", "28A", new Date(), "BF101", "22-JAN-16", 1);

		CheckinRecord result = repository.save(record);
		log.info("Checked in successfully ..." + result);
		log.info("Looking to load check in record ...");
		log.info("Result: " + repository.findById(result.getId()).get());
	}
}
