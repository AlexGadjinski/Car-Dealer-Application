package com.example.xml_processing.services.dtos.imports;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Set;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsDTO {
    @XmlElement(name = "car")
    private Set<CarDTO> cars;

    public CarsDTO() {}

    public Set<CarDTO> getCars() {
        return cars;
    }

    public void setCars(Set<CarDTO> cars) {
        this.cars = cars;
    }
}
