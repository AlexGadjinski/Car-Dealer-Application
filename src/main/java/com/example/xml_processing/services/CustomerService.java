package com.example.xml_processing.services;

public interface CustomerService extends BaseService {
    void seedCustomers();
    void exportCustomersOrdered();
    void exportCustomersWithPurchases();
}
