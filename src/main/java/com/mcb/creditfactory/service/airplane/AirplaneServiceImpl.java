package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Airplane;
import com.mcb.creditfactory.model.AssessedValueAirplane;
import com.mcb.creditfactory.repository.AirplaneRepository;
import com.mcb.creditfactory.repository.AssessedValueAirplaneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AirplaneServiceImpl implements AirplaneService {
    private final ExternalApproveService approveService;
    private final AirplaneRepository airplaneRepository;
    private final AssessedValueAirplaneRepository assessedValueAirplaneRepository;

    @Autowired
    public AirplaneServiceImpl(ExternalApproveService approveService,
                               AirplaneRepository airplaneRepository,
                               AssessedValueAirplaneRepository assessedValueAirplaneRepository) {
        this.approveService = approveService;
        this.airplaneRepository = airplaneRepository;
        this.assessedValueAirplaneRepository = assessedValueAirplaneRepository;
    }

    @Override
    public boolean approve(AirplaneDto dto) {
        return approveService.approve(new AirplaneAdapter(dto)) == 0;
    }

    @Override
    public Airplane save(Airplane airplane) {
        return airplaneRepository.save(airplane);
    }

    @Override
    public Optional<Airplane> load(Long id) {
        return airplaneRepository.findById(id);
    }

    @Override
    public Airplane fromDto(AirplaneDto dto) {
        return new Airplane(
                dto.getId(),
                dto.getBrand(),
                dto.getModel(),
                dto.getManufacturer(),
                dto.getYear(),
                dto.getFuelCapacity(),
                dto.getSeats()
        );
    }

    @Override
    public AirplaneDto toDto(Airplane airplane) {
        AssessedValueAirplane maxAssessmentDayByAirplaneId = assessedValueAirplaneRepository.findMaxAssessmentDayByAirplaneId(airplane.getId());
        return AirplaneDto.builder()
                .id(airplane.getId())
                .brand(airplane.getBrand())
                .model(airplane.getModel())
                .manufacturer(airplane.getManufacturer())
                .year(airplane.getYear())
                .fuelCapacity(airplane.getFuelCapacity())
                .seats(airplane.getSeats())
                .assessmentDate(maxAssessmentDayByAirplaneId.getAssessment_date())
                .value(maxAssessmentDayByAirplaneId.getValue())
                .build();
    }

    @Override
    public Long getId(Airplane airplane) {
        return airplane.getId();
    }

    @Override
    public AirplaneDto checkValuationDate(AirplaneDto dto) {
        AssessedValueAirplane maxAssessmentDayByAirplaneId = assessedValueAirplaneRepository.findMaxAssessmentDayByAirplaneId(dto.getId());
        dto.setValue(maxAssessmentDayByAirplaneId.getValue());
        dto.setAssessmentDate(maxAssessmentDayByAirplaneId.getAssessment_date());
        return dto;
    }

    @Override
    public List<Airplane> getListByBrand(String brand) {
        return airplaneRepository.findByBrand(brand);
    }

    @Override
    public List<Airplane> findAll() {
        return airplaneRepository.findAll();
    }
}
