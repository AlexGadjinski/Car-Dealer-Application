package com.example.xml_processing.services.impls;

import com.example.xml_processing.data.entities.Customer;
import com.example.xml_processing.data.repositories.CustomerRepository;
import com.example.xml_processing.services.CustomerService;
import com.example.xml_processing.services.dtos.exports.CustomerOrderedDTO;
import com.example.xml_processing.services.dtos.exports.CustomerPurchasesDTO;
import com.example.xml_processing.services.dtos.exports.CustomersOrderedDTO;
import com.example.xml_processing.services.dtos.exports.CustomersPurchasesDTO;
import com.example.xml_processing.services.dtos.imports.CustomersDTO;
import com.example.xml_processing.utils.XMLParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final String XML_IMPORT_PATH = "src/main/resources/xml/import/customers.xml";
    private static final String XML_EXPORT_ORDERED_PATH = "src/main/resources/xml/export/ordered-customers.xml";
    private static final String XML_EXPORT_WITH_PURCHASES_PATH = "src/main/resources/xml/export/customers-total-sales.xml";
    private final CustomerRepository customerRepository;
    private final ModelMapper mapper;
    private final XMLParser xmlParser;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper mapper, XMLParser xmlParser) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean isImported() {
        return customerRepository.count() > 0;
    }

    @Override
    public void seedCustomers() {
        CustomersDTO customersDTO = xmlParser.importFile(CustomersDTO.class, XML_IMPORT_PATH);
        customersDTO.getCustomers().stream()
                .map(c -> mapper.map(c, Customer.class))
                .forEach(customerRepository::save);
    }

    @Override
    public void exportCustomersOrdered() {
        Set<CustomerOrderedDTO> customerOrderedDTOs = customerRepository.findByOrderByBirthDateAsc().stream()
                .map(c -> mapper.map(c, CustomerOrderedDTO.class))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        CustomersOrderedDTO customersOrderedDTO =
                new CustomersOrderedDTO(customerOrderedDTOs);
        xmlParser.exportFile(customersOrderedDTO, XML_EXPORT_ORDERED_PATH);
    }

    @Override
    public void exportCustomersWithPurchases() {
        Set<CustomerPurchasesDTO> customerPurchasesDTOs = customerRepository.findBySalesCountGreaterThanZero();
        CustomersPurchasesDTO customersPurchasesDTO = new CustomersPurchasesDTO(customerPurchasesDTOs);
        xmlParser.exportFile(customersPurchasesDTO, XML_EXPORT_WITH_PURCHASES_PATH);
    }
}
