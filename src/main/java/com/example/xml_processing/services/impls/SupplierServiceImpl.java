package com.example.xml_processing.services.impls;

import com.example.xml_processing.data.entities.Supplier;
import com.example.xml_processing.data.repositories.SupplierRepository;
import com.example.xml_processing.services.SupplierService;
import com.example.xml_processing.services.dtos.exports.SupplierNotImporterDTO;
import com.example.xml_processing.services.dtos.exports.SuppliersNotImporterDTO;
import com.example.xml_processing.services.dtos.imports.SuppliersDTO;
import com.example.xml_processing.utils.XMLParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    private static final String XML_IMPORT_PATH = "src/main/resources/xml/import/suppliers.xml";
    private static final String XML_EXPORT_NOT_IMPORTERS_PATH = "src/main/resources/xml/export/local-suppliers.xml";
    private final SupplierRepository supplierRepository;
    private final ModelMapper mapper;
    private final XMLParser xmlParser;

    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper mapper, XMLParser xmlParser) {
        this.supplierRepository = supplierRepository;
        this.mapper = mapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean isImported() {
        return supplierRepository.count() > 0;
    }

    @Override
    public void seedSuppliers() {
        SuppliersDTO suppliersDTO = xmlParser.importFile(SuppliersDTO.class, XML_IMPORT_PATH);
        suppliersDTO.getSuppliers().stream()
                .map(s -> mapper.map(s, Supplier.class))
                .forEach(supplierRepository::save);
    }

    @Override
    public void exportSuppliersNotImporters() {
        Set<SupplierNotImporterDTO> supplierNotImporterDTOs = supplierRepository.findByIsNotImporter().stream()
                .map(s -> {
                    SupplierNotImporterDTO supplierNotImporterDTO = mapper.map(s, SupplierNotImporterDTO.class);
                    supplierNotImporterDTO.setPartsCount(s.getParts().size());
                    return supplierNotImporterDTO;
                })
                .collect(Collectors.toCollection(LinkedHashSet::new));

        SuppliersNotImporterDTO suppliersNotImporterDTO = new SuppliersNotImporterDTO(supplierNotImporterDTOs);
        xmlParser.exportFile(suppliersNotImporterDTO, XML_EXPORT_NOT_IMPORTERS_PATH);
    }
}
