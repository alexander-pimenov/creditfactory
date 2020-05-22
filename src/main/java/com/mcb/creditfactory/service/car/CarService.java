package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    boolean approve(CarDto dto);
    Car save(Car car);
    Optional<Car> load(Long id);
    Car fromDto(CarDto dto);
    CarDto toDTO(Car car);
    Long getId(Car car);
    CarDto checkValuationDate(CarDto dto);
    List<Car> getListByBrand(String brand);
    List<Car> findAll();
}
