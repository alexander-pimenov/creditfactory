package com.mcb.creditfactory.repository;

import com.mcb.creditfactory.model.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
    @Override
    List<Airplane> findAll();

    List<Airplane> findByBrand(String brand);
}
