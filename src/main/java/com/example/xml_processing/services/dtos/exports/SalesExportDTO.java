package com.example.xml_processing.services.dtos.exports;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Set;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesExportDTO {
    @XmlElement(name = "sale")
    private Set<SaleExportDTO> sales;

    public SalesExportDTO() {}

    public SalesExportDTO(Set<SaleExportDTO> sales) {
        this.sales = sales;
    }

    public Set<SaleExportDTO> getSales() {
        return sales;
    }

    public void setSales(Set<SaleExportDTO> sales) {
        this.sales = sales;
    }
}
