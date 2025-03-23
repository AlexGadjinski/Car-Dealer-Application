package com.example.xml_processing.data.repositories;

import com.example.xml_processing.data.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    @Query("SELECT s FROM Supplier AS s WHERE s.isImporter = FALSE")
    Set<Supplier> findByIsNotImporter();
}
