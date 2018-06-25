package com.example.search.entity;

import javax.persistence.*;

@Entity
public class Fares {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fare_Id")
    private long id;

    private String fare;
    private String currency;

    public Fares() {
        super();
    }

    public Fares(String fare, String currency) {
        super();
        this.fare = fare;
        this.currency = currency;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Fares{" +
                "id=" + id +
                ", fare='" + fare + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
