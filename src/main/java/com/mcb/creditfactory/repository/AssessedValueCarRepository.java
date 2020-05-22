package com.mcb.creditfactory.repository;

import com.mcb.creditfactory.model.AssessedValueCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessedValueCarRepository extends JpaRepository<AssessedValueCar, Long> {

//        @Query("FROM AssessedValueCar r WHERE r.car_id=:id AND r.assessment_date = " +
//            "(SELECT MAX(r.assessment_date) FROM AssessedValueCar r WHERE r.car_id=:id)")
//    @Query("FROM Assessed_Value_Car r WHERE r.car_Id=:id AND r.assessment_date = " +
//            "(SELECT MAX(r.assessment_date) FROM Assessed_Value_Car r WHERE r.car_Id=:id)")
    AssessedValueCar findMaxAssessmentDayByCarId(Long id);

}
