package com.example.xml_processing.services.dtos.exports;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Set;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsToyotaDTO {
    @XmlElement(name = "car")
    private Set<CarToyotaDTO> cars;

    public CarsToyotaDTO() {}

    public CarsToyotaDTO(Set<CarToyotaDTO> cars) {
        this.cars = cars;
    }

    public Set<CarToyotaDTO> getCars() {
        return cars;
    }

    public void setCars(Set<CarToyotaDTO> cars) {
        this.cars = cars;
    }
}
