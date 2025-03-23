package com.example.xml_processing.services.dtos.exports;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Set;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SuppliersNotImporterDTO {
    @XmlElement(name = "supplier")
    private Set<SupplierNotImporterDTO> suppliers;

    public SuppliersNotImporterDTO() {}

    public SuppliersNotImporterDTO(Set<SupplierNotImporterDTO> suppliers) {
        this.suppliers = suppliers;
    }

    public Set<SupplierNotImporterDTO> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<SupplierNotImporterDTO> suppliers) {
        this.suppliers = suppliers;
    }
}
