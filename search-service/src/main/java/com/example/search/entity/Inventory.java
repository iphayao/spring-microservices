package com.example.search.entity;

import javax.persistence.*;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "inv_Id")
    private long id;

    private int count;

    public Inventory() {
        super();
    }

    public Inventory(int count) {
        super();
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", count=" + count +
                '}';
    }
}
