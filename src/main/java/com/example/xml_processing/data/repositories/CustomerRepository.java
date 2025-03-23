package com.example.xml_processing.data.repositories;

import com.example.xml_processing.data.entities.Customer;
import com.example.xml_processing.services.dtos.exports.CustomerPurchasesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Set<Customer> findByOrderByBirthDateAsc();

    @Query(value = "SELECT " +
            "   cm.name AS fullName, " +
            "   (SELECT COUNT(*) FROM customers " +
            "   JOIN sales ON customers.id = sales.customer_id " +
            "   JOIN cars ON sales.car_id = cars.id " +
            "   WHERE customers.name = fullName " +
            "   GROUP BY customers.id) AS boughtCars, " +
            "   (SELECT SUM(price) FROM customers " +
            "    JOIN sales ON customers.id = sales.customer_id " +
            "    JOIN cars ON sales.car_id = cars.id " +
            "    JOIN cars_parts ON cars_parts.car_id = cars.id " +
            "    JOIN parts ON parts.id = cars_parts.part_id " +
            "    WHERE customers.name = fullName " +
            "    GROUP BY customers.id) AS moneySpent " +
            "FROM customers AS cm " +
            "JOIN sales AS s ON cm.id = s.customer_id " +
            "JOIN cars AS c ON s.car_id = c.id " +
            "JOIN cars_parts AS cp ON cp.car_id = c.id " +
            "JOIN parts AS p ON p.id = cp.part_id " +
            "GROUP BY cm.id " +
            "ORDER BY moneySpent DESC, boughtCars DESC" +
            "", nativeQuery = true)
    Set<CustomerPurchasesDTO> findBySalesCountGreaterThanZero();
}
