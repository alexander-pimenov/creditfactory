package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.model.Airplane;

import java.util.List;
import java.util.Optional;

public interface AirplaneService {

    boolean approve(AirplaneDto dto);
    Airplane save(Airplane airplane);
    Optional<Airplane> load(Long id);
    Airplane fromDto(AirplaneDto dto);
    AirplaneDto toDto(Airplane airplane);
    Long getId(Airplane airplane);
    AirplaneDto checkValuationDate(AirplaneDto dto);
    List<Airplane> getListByBrand(String brand);
    List<Airplane> findAll();


}
