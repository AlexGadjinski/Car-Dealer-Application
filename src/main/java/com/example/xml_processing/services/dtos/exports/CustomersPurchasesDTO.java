package com.example.xml_processing.services.dtos.exports;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Set;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersPurchasesDTO {
    @XmlElement(name = "customer")
    private Set<CustomerPurchasesDTO> customers;

    public CustomersPurchasesDTO() {}

    public CustomersPurchasesDTO(Set<CustomerPurchasesDTO> customers) {
        this.customers = customers;
    }

    public Set<CustomerPurchasesDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<CustomerPurchasesDTO> customers) {
        this.customers = customers;
    }
}
