package com.example.xml_processing.data.repositories;

import com.example.xml_processing.data.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Set<Car> findByMakeOrderByModelAscTravelledDistanceDesc(String make);
}
