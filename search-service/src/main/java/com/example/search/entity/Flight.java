package com.example.search.entity;

import javax.persistence.*;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String flightNumber;
    private String origin;
    private String destination;
    private String flightDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fare_Id")
    private Fares fares;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inv_Id")
    private Inventory inventory;

    public Flight() {
        super();
    }

    public Flight(String flightNumber, String origin, String destination, String flightDate, Fares fares, Inventory inventory) {
        super();
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.flightDate = flightDate;
        this.fares = fares;
        this.inventory = inventory;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
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

    public Fares getFares() {
        return fares;
    }

    public void setFares(Fares fares) {
        this.fares = fares;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightNumber='" + flightNumber + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", flightDate='" + flightDate + '\'' +
                ", fares=" + fares +
                ", inventory=" + inventory +
                '}';
    }
}
