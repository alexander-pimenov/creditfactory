package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.model.Airplane;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.service.airplane.AirplaneService;
import com.mcb.creditfactory.service.car.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// TODO: reimplement this
@Slf4j
@Service
public class CollateralService {
    private final CarService carService;
    private final AirplaneService airplaneService;

    @Autowired
    public CollateralService(CarService carService,
                             AirplaneService airplaneService) {
        this.carService = carService;
        this.airplaneService = airplaneService;
    }

    @SuppressWarnings("ConstantConditions")
    public Long saveCollateral(Collateral object) {

        if (object instanceof CarDto) {
            CarDto car = (CarDto) object;
            boolean approved = carService.approve(car);
            if (!approved) {
                return null;
            }
            return Optional.of(car)
                    .map(carService::fromDto)
                    .map(carService::save)
                    .map(carService::getId)
                    .orElse(null);

        } else if (object instanceof AirplaneDto) {
            AirplaneDto airplane = (AirplaneDto) object;
            boolean approved = airplaneService.approve(airplane);
            if (!approved) {
                return null;
            }

            return Optional.of(airplane)
                    .map(airplaneService::fromDto)
                    .map(airplaneService::save)
                    .map(airplaneService::getId)
                    .orElse(null);

        } else {
            throw new IllegalArgumentException();
        }
    }


    public Collateral getInfo(Collateral object) {
        if (object instanceof CarDto) {
            return Optional.of((CarDto) object)
                    .map(carService::fromDto)
                    .map(carService::getId)
                    .flatMap(carService::load)
                    .map(carService::toDTO)
                    .orElse(null);
        } else if (object instanceof AirplaneDto) {
            return Optional.of((AirplaneDto) object)
                    .map(airplaneService::fromDto)
                    .map(airplaneService::getId)
                    .flatMap(airplaneService::load)
                    .map(airplaneService::toDto)
                    .orElse(null);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public List<Car> getListCarByBrand(String brand){
        return carService.getListByBrand(brand);
    }

    public List<Car> findAllCars(){
        return carService.findAll();
    }

    public List<Airplane> getListAirplaneByBrand(String brand){
        return airplaneService.getListByBrand(brand);
    }

    public List<Airplane> findAllAirplances(){
        return airplaneService.findAll();
    }
}
