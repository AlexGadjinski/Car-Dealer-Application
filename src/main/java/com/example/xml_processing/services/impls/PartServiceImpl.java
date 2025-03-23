package com.example.xml_processing.services.impls;

import com.example.xml_processing.data.entities.Part;
import com.example.xml_processing.data.entities.Supplier;
import com.example.xml_processing.data.repositories.PartRepository;
import com.example.xml_processing.data.repositories.SupplierRepository;
import com.example.xml_processing.services.PartService;
import com.example.xml_processing.services.dtos.imports.PartsDTO;
import com.example.xml_processing.utils.XMLParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PartServiceImpl implements PartService {
    private static final String XML_IMPORT_PATH = "src/main/resources/xml/import/parts.xml";
    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;
    private final ModelMapper mapper;
    private final XMLParser xmlParser;

    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository,
                           ModelMapper mapper, XMLParser xmlParser) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
        this.mapper = mapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean isImported() {
        return partRepository.count() > 0;
    }

    @Override
    public void seedParts() {
        PartsDTO partsDTO = xmlParser.importFile(PartsDTO.class, XML_IMPORT_PATH);
        partsDTO.getParts().stream()
                .map(p -> mapper.map(p, Part.class))
                .forEach(p -> {
                    p.setSupplier(getRandomSupplier());
                    partRepository.save(p);
                });
    }

    private Supplier getRandomSupplier() {
        long randomNum = new Random().nextInt(1, (int) supplierRepository.count() + 1);
        return supplierRepository.findById(randomNum).orElseThrow();
    }
}
