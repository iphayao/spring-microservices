package com.example.book;

import com.example.book.component.BookingComponent;
import com.example.book.component.BookingStatus;
import com.example.book.entity.BookingRecord;
import com.example.book.entity.Inventory;
import com.example.book.entity.Passenger;
import com.example.book.repository.BookingRepository;
import com.example.book.repository.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BookServiceApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(BookServiceApplication.class);

	@Autowired
    private BookingComponent bookingComponent;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	InventoryRepository inventoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Inventory[] invs = {
				new Inventory("BF100", "22-JAN-16", 100),
				new Inventory("BF101", "22-JAN-16", 100),
				new Inventory("BF102", "22-JAN-16", 100),
				new Inventory("BF103", "22-JAN-16", 100),
				new Inventory("BF104", "22-JAN-16", 100),
				new Inventory("BF105", "22-JAN-16", 100),
				new Inventory("BF106", "22-JAN-16", 100)
		};

		Arrays.asList(invs).forEach(inventory -> {
			inventoryRepository.save(inventory);
		});

		BookingRecord booking = new BookingRecord("BF101", "NYC", "SFO", "22-JAN-16", new Date(), "101", BookingStatus.BOOKING_CONFIRMED);
		Set<Passenger> passengers = new HashSet<>();
		passengers.add(new Passenger("John", "Doe", "He", booking));

		booking.setPassengers(passengers);
		long record = bookingComponent.book(booking);
		log.info("booking successfully saved ..." + record);

		log.info("looking to load booking record ...");
		log.info("Result: " + bookingComponent.getBooking(record));

	}
}
