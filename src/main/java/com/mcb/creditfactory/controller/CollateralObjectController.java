package com.mcb.creditfactory.controller;

import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.model.Airplane;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.service.CollateralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CollateralObjectController {

    private final CollateralService service;
    @Autowired
    public CollateralObjectController(CollateralService service) {
        this.service = service;
    }

    @PostMapping("/collateral/save")
    public HttpEntity<Long> save(@RequestBody Collateral object) {
        Long id = service.saveCollateral(object);
        return id != null ? ResponseEntity.ok(id) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/collateral/info")
    public HttpEntity<Collateral> getInfo(@RequestBody Collateral object) {
        Collateral info = service.getInfo(object);
        return info != null ? ResponseEntity.ok(info) : ResponseEntity.notFound().build();
    }

    @GetMapping("/cars/{brand}")
    public List<Car> getListCars(@PathVariable String brand) {
        return service.getListCarByBrand(brand);
    }

    @GetMapping("/cars")
    public List<Car> getListAllCars() {
        return service.findAllCars();
    }

    @GetMapping("/airplanes/{brand}")
    public List<Airplane> getListAirplanes(@PathVariable String brand) {
        return service.getListAirplaneByBrand(brand);
    }

    @GetMapping("/airplanes")
    public List<Airplane> getListAllAirplanes() {
        return service.findAllAirplances();
    }
}
