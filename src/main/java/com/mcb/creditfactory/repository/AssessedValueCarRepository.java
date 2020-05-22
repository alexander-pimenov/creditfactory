package com.mcb.creditfactory.repository;

import com.mcb.creditfactory.model.AssessedValueCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessedValueCarRepository extends JpaRepository<AssessedValueCar, Long> {

    AssessedValueCar findMaxAssessmentDayByCarId(Long id);

}
