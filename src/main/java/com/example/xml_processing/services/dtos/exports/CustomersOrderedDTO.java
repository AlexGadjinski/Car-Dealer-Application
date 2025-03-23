package com.example.xml_processing.services.dtos.exports;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Set;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersOrderedDTO {
    @XmlElement(name = "customer")
    private Set<CustomerOrderedDTO> customers;

    public CustomersOrderedDTO() {}

    public CustomersOrderedDTO(Set<CustomerOrderedDTO> customers) {
        this.customers = customers;
    }

    public Set<CustomerOrderedDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<CustomerOrderedDTO> customers) {
        this.customers = customers;
    }
}
