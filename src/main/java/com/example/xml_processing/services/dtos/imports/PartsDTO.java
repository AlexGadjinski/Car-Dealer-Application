package com.example.xml_processing.services.dtos.imports;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Set;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartsDTO {
    @XmlElement(name = "part")
    private Set<PartDTO> parts;

    public PartsDTO() {}

    public Set<PartDTO> getParts() {
        return parts;
    }

    public void setParts(Set<PartDTO> parts) {
        this.parts = parts;
    }
}
