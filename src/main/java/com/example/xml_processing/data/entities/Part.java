package com.example.xml_processing.data.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "parts")
public class Part extends BaseEntity {
    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private BigDecimal price;

    @Basic(optional = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;

    public Part() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return quantity == part.quantity && Objects.equals(name, part.name) && Objects.equals(price, part.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, quantity);
    }
}
