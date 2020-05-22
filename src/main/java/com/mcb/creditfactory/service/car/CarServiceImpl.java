package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.AssessedValueCar;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.repository.AssessedValueCarRepository;
import com.mcb.creditfactory.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final ExternalApproveService approveService;
    private final CarRepository carRepository;
    private final AssessedValueCarRepository assessedValueCarRepository;

    @Autowired
    public CarServiceImpl(ExternalApproveService approveService,
                          CarRepository carRepository,
                          AssessedValueCarRepository assessedValueCarRepository) {
        this.approveService = approveService;
        this.carRepository = carRepository;
        this.assessedValueCarRepository = assessedValueCarRepository;
    }

    @Override
    public boolean approve(CarDto dto) {
        return approveService.approve(new CarAdapter(dto)) == 0;
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Optional<Car> load(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public Car fromDto(CarDto dto) {
        return new Car(
                dto.getId(),
                dto.getBrand(),
                dto.getModel(),
                dto.getPower(),
                dto.getYear()
        );
    }

    @Override
    public CarDto toDTO(Car car) {
        AssessedValueCar maxAssessmentDayByCarId = assessedValueCarRepository.findMaxAssessmentDayByCarId(car.getId());
        return CarDto.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .model(car.getModel())
                .power(car.getPower())
                .year(car.getYear())
                .assessmentDate(maxAssessmentDayByCarId.getAssessment_date())
                .value(maxAssessmentDayByCarId.getValue())
                .build();
    }

    @Override
    public Long getId(Car car) {
        return car.getId();
    }

    @Override
    public CarDto checkValuationDate(CarDto dto) {
        AssessedValueCar maxAssessmentDayByCarId = assessedValueCarRepository.findMaxAssessmentDayByCarId(dto.getId());
        dto.setAssessmentDate(maxAssessmentDayByCarId.getAssessment_date());
        dto.setValue(maxAssessmentDayByCarId.getValue());
        return dto;
    }

    @Override
    public List<Car> getListByBrand(String brand) {
        List<Car> all = carRepository.findAll();
        List<Car> list = new ArrayList<>();
        for (Car car : all) {
            if (car.getBrand().equals(brand)) {
                list.add(car);
            }
        }
        return list;
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

}
