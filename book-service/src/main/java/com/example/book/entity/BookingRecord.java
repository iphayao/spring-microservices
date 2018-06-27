package com.example.book.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class BookingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String flightNunber;
    private String origin;
    private String destination;
    private String flightDate;
    private Date bookingDate;
    private String fare;
    private String status;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "bookingRecord")
    Set<Passenger> passengers;

    public BookingRecord() {
    }

    public BookingRecord(String flightNunber, String origin, String destination, String flightDate, Date bookingDate, String fare, String status) {
        this.flightNunber = flightNunber;
        this.origin = origin;
        this.destination = destination;
        this.flightDate = flightDate;
        this.bookingDate = bookingDate;
        this.fare = fare;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFlightNunber() {
        return flightNunber;
    }

    public void setFlightNunber(String flightNunber) {
        this.flightNunber = flightNunber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        return "BookingRecord{" +
                "id=" + id +
                ", flightNunber='" + flightNunber + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", flightDate='" + flightDate + '\'' +
                ", bookingDate=" + bookingDate +
                ", fare='" + fare + '\'' +
                ", status='" + status + '\'' +
                ", passengers=" + passengers +
                '}';
    }
}
