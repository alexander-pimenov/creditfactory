package com.mcb.creditfactory.repository;

import com.mcb.creditfactory.model.AssessedValueAirplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessedValueAirplaneRepository extends JpaRepository<AssessedValueAirplane, Long> {
//    @Query("FROM AssessedValueAirplane r WHERE r.airplane_id=:id AND r.assessment_date = " +
//            "(SELECT MAX(r.assessment_date) FROM AssessedValueAirplane r WHERE r.airplane_id=:id)")
//    @Query("FROM Assessed_Value_Airplane r WHERE r.airplane_Id=:id AND r.assessment_date = " +
//            "(SELECT MAX(r.assessment_date) FROM Assessed_Value_Airplane r WHERE r.airplane_Id=:id)")
    AssessedValueAirplane findMaxAssessmentDayByAirplaneId(Long id);
}
