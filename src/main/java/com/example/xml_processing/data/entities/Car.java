package com.example.xml_processing.data.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity {
    @Basic(optional = false)
    private String make;

    @Basic(optional = false)
    private String model;

    @Column(name = "travelled_distance", nullable = false)
    private long travelledDistance;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cars_parts",
            joinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "part_id", referencedColumnName = "id")
    )
    private Set<Part> parts;

    public Car() {}

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }
}
