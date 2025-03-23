package com.example.xml_processing.services.impls;

import com.example.xml_processing.data.entities.Car;
import com.example.xml_processing.data.entities.Customer;
import com.example.xml_processing.data.entities.Part;
import com.example.xml_processing.data.entities.Sale;
import com.example.xml_processing.data.repositories.CarRepository;
import com.example.xml_processing.data.repositories.CustomerRepository;
import com.example.xml_processing.data.repositories.SaleRepository;
import com.example.xml_processing.services.SaleService;
import com.example.xml_processing.services.dtos.exports.CarBasicInfoDTO;
import com.example.xml_processing.services.dtos.exports.SaleExportDTO;
import com.example.xml_processing.services.dtos.exports.SalesExportDTO;
import com.example.xml_processing.utils.XMLParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {
    private static final String XML_EXPORT_PATH = "src/main/resources/xml/export/sales-discounts.xml";
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;
    private final ModelMapper mapper;

    private final XMLParser xmlParser;

    public SaleServiceImpl(CarRepository carRepository, CustomerRepository customerRepository,
                           SaleRepository saleRepository, ModelMapper mapper, XMLParser xmlParser) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
        this.mapper = mapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean isImported() {
        return saleRepository.count() > 0;
    }

    @Override
    public void seedSales() {
        for (int i = 0; i < 35; i++) {
            Sale sale = new Sale();
            sale.setDiscount(getRandomDiscount());
            sale.setCar(getRandomCar());
            sale.setCustomer(getRandomCustomer());
            saleRepository.save(sale);
        }
    }

    private Customer getRandomCustomer() {
        long randomNum = new Random().nextInt(1, (int) customerRepository.count() + 1);
        return customerRepository.findById(randomNum).orElseThrow();
    }

    private Car getRandomCar() {
        long randomNum = new Random().nextInt(1, (int) carRepository.count() + 1);
        return carRepository.findById(randomNum).orElseThrow();
    }

    private double getRandomDiscount() {
        double[] discounts = {0.00, 0.05, 0.10, 0.15, 0.20, 0.30, 0.40, 0.50};
        int randomNum = new Random().nextInt(0, discounts.length);
        return discounts[randomNum];
    }

    @Override
    public void exportSales() {
        Set<SaleExportDTO> saleExportDTOs = saleRepository.findAll().stream()
                .map(s -> {
                    CarBasicInfoDTO carBasicInfoDTO = mapper.map(s.getCar(), CarBasicInfoDTO.class);

                    SaleExportDTO saleExportDTO = mapper.map(s, SaleExportDTO.class);
                    saleExportDTO.setCar(carBasicInfoDTO);
                    saleExportDTO.setCustomerName(s.getCustomer().getName());
                    saleExportDTO.setPrice(
                            s.getCar().getParts().stream()
                                    .map(Part::getPrice)
                                    .reduce(BigDecimal.ZERO, BigDecimal::add));
                    saleExportDTO.setPriceWithDiscount(
                            saleExportDTO.getPrice().multiply(BigDecimal.valueOf(1 - saleExportDTO.getDiscount())));
                    return saleExportDTO;
                }).collect(Collectors.toSet());

        SalesExportDTO salesExportDTO = new SalesExportDTO(saleExportDTOs);
        xmlParser.exportFile(salesExportDTO, XML_EXPORT_PATH);
    }
}
