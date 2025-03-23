package com.example.xml_processing.services.impls;

import com.example.xml_processing.data.entities.Car;
import com.example.xml_processing.data.entities.Part;
import com.example.xml_processing.data.repositories.CarRepository;
import com.example.xml_processing.data.repositories.PartRepository;
import com.example.xml_processing.services.CarService;
import com.example.xml_processing.services.dtos.exports.*;
import com.example.xml_processing.services.dtos.imports.CarsDTO;
import com.example.xml_processing.utils.XMLParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private static final String XML_IMPORT_PATH = "src/main/resources/xml/import/cars.xml";
    private static final String XML_EXPORT_TOYOTA_PATH = "src/main/resources/xml/export/toyota-cars.xml";
    private static final String XML_EXPORT_CARS_WITH_PARTS_PATH = "src/main/resources/xml/export/cars-and-parts.xml";
    private final CarRepository carRepository;
    private final PartRepository partRepository;
    private final ModelMapper mapper;
    private final XMLParser xmlParser;

    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository, ModelMapper mapper, XMLParser xmlParser) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.mapper = mapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean isImported() {
        return carRepository.count() > 0;
    }

    @Override
    public void seedCars() {
        CarsDTO carsDTO = xmlParser.importFile(CarsDTO.class, XML_IMPORT_PATH);
        carsDTO.getCars().stream()
                .map(c -> mapper.map(c, Car.class))
                .forEach(c -> {
                    c.setParts(getRandomParts());
                    carRepository.save(c);
                });
    }

    private Set<Part> getRandomParts() {
        Set<Part> randomParts = new HashSet<>();
        int randomCount = new Random().nextInt(3, 6);

        for (int i = 0; i < randomCount; i++) {
            long randomNum = new Random().nextInt(1, (int) partRepository.count() + 1);
            randomParts.add(partRepository.findById(randomNum).orElseThrow());
        }

        return randomParts;
    }

    @Override
    public void exportToyotaCars() {
        Set<CarToyotaDTO> carToyotaDTOs =
                carRepository.findByMakeOrderByModelAscTravelledDistanceDesc("Toyota").stream()
                        .map(c -> mapper.map(c, CarToyotaDTO.class))
                        .collect(Collectors.toCollection(LinkedHashSet::new));
        CarsToyotaDTO carsToyotaDTO = new CarsToyotaDTO(carToyotaDTOs);
        xmlParser.exportFile(carsToyotaDTO, XML_EXPORT_TOYOTA_PATH);
    }

    @Override
    public void exportCarsWithParts() {
        Set<CarInfoDTO> carInfoDTOs = carRepository.findAll().stream()
                .map(c -> {
                    Set<PartInfoDTO> partInfoDTOs = c.getParts().stream()
                            .map(p -> mapper.map(p, PartInfoDTO.class))
                            .collect(Collectors.toCollection(LinkedHashSet::new));
                    CarInfoDTO carInfoDTO = mapper.map(c, CarInfoDTO.class);
                    carInfoDTO.setParts(partInfoDTOs);
                    return carInfoDTO;
                })
                .collect(Collectors.toCollection(LinkedHashSet::new));
        CarsInfoDTO carsInfoDTO = new CarsInfoDTO(carInfoDTOs);
        xmlParser.exportFile(carsInfoDTO, XML_EXPORT_CARS_WITH_PARTS_PATH);
    }
}
