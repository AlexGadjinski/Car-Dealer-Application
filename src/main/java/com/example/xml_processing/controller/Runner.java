package com.example.xml_processing.controller;

import com.example.xml_processing.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {
    private final CarService carService;
    private final CustomerService customerService;
    private final PartService partService;
    private final SaleService saleService;
    private final SupplierService supplierService;

    public Runner(CarService carService, CustomerService customerService, PartService partService, SaleService saleService, SupplierService supplierService) {
        this.carService = carService;
        this.customerService = customerService;
        this.partService = partService;
        this.saleService = saleService;
        this.supplierService = supplierService;
    }

    @Override
    public void run(String... args) {
//        seedData();
//        customerService.exportCustomersOrdered();
//        carService.exportToyotaCars();
//        supplierService.exportSuppliersNotImporters();
//        carService.exportCarsWithParts();
//        customerService.exportCustomersWithPurchases();
//        saleService.exportSales();
    }

    private void seedData() {
        if (!supplierService.isImported()) {
            supplierService.seedSuppliers();
        }
        if (!partService.isImported()) {
            partService.seedParts();
        }
        if (!carService.isImported()) {
            carService.seedCars();
        }
        if (!customerService.isImported()) {
            customerService.seedCustomers();
        }
        if (!saleService.isImported()) {
            saleService.seedSales();
        }
    }
}
