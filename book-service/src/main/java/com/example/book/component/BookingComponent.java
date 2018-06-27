package com.example.book.component;

import com.example.book.entity.BookingRecord;
import com.example.book.entity.Inventory;
import com.example.book.entity.Passenger;
import com.example.book.repository.BookingRepository;
import com.example.book.repository.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class BookingComponent {
    public static final Logger log = LoggerFactory.getLogger(BookingComponent.class);
    public static final String FareURL = "http://localhost:8080/fare";

    BookingRepository bookingRepository;
    InventoryRepository inventoryRepository;

    //@Autowired
    private RestTemplate restTemplate;

    Sender sender;

    @Autowired
    public BookingComponent(BookingRepository bookingRepository, Sender sender, InventoryRepository inventoryRepository) {
        this.bookingRepository = bookingRepository;
        this.inventoryRepository = inventoryRepository;
        this.sender = sender;
        this.restTemplate = new RestTemplate();
    }

    public long book(BookingRecord record) {
        log.info("calling fares to get fare");

        // Call fare to get fare
        Fare fare = restTemplate.getForObject(FareURL + "/get?flightNumber=" + record.getFlightNunber() + "&flightDate=" + record.getFlightDate(), Fare.class);
        log.info("calling fares to get fare " + fare);

        // Check fare
        if(!record.getFare().equals(fare.getFare())) {
            throw new BookingException("fare is template");
        }
        log.info("Calling inventory to get inventory");

        // Check inventory
        Inventory inventory = inventoryRepository.findByFlightNumberAndFlightDate(record.getFlightNunber(), record.getFlightDate());
        if(!inventory.isAvailable(record.getPassengers().size())) {
            throw new BookingException("no more seats available");
        }
        log.info("successfully checked inventory" + inventory);
        log.info("calling inventory to update inventory");

        // Update inventory
        inventory.setAvailable(inventory.getAvailable() - record.getPassengers().size());
        inventoryRepository.saveAndFlush(inventory);
        log.info("successfully updated inventory");

        // Save booking
        record.setStatus(BookingStatus.BOOKING_CONFIRMED);
        Set<Passenger> passengers = record.getPassengers();
        passengers.forEach(passenger -> {
            passenger.setBookingRecord(record);
        });
        record.setBookingDate(new Date());
        long id = bookingRepository.save(record).getId();
        log.info("successfully save booking");

        // Send message to search to update inventory
        log.info("sending a booking event");
        Map<String, Object> bookingDetails = new HashMap<>();
        bookingDetails.put("FLIGHT_NUMBER", record.getFlightNunber());
        bookingDetails.put("FLIGHT_DATE", record.getFlightDate());
        bookingDetails.put("NEW_INVENTORY", inventory.getBookableInventory());
        sender.send(bookingDetails);
        log.info("booking event successfully delivered" + bookingDetails);

        return id;
    }

    public BookingRecord getBooking(long id) {
        return bookingRepository.findOne(id);
    }

    public void updateStatus(String status, long bookingId) {
        BookingRecord record = bookingRepository.findOne(bookingId);
        record.setStatus(status);
    }
}
