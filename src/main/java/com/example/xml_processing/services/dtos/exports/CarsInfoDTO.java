package com.example.xml_processing.services.dtos.exports;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Set;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsInfoDTO {
    @XmlElement(name = "car")
    private Set<CarInfoDTO> cars;

    public CarsInfoDTO() {}

    public CarsInfoDTO(Set<CarInfoDTO> cars) {
        this.cars = cars;
    }

    public Set<CarInfoDTO> getCars() {
        return cars;
    }

    public void setCars(Set<CarInfoDTO> cars) {
        this.cars = cars;
    }
}
