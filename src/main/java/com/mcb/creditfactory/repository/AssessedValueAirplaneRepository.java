package com.mcb.creditfactory.repository;

import com.mcb.creditfactory.model.AssessedValueAirplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessedValueAirplaneRepository extends JpaRepository<AssessedValueAirplane, Long> {

    AssessedValueAirplane findMaxAssessmentDayByAirplaneId(Long id);
}
