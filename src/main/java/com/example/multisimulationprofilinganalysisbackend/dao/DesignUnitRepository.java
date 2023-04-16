package com.example.multisimulationprofilinganalysisbackend.dao;

import com.example.multisimulationprofilinganalysisbackend.model.DesignUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DesignUnitRepository extends JpaRepository<DesignUnit, Long> {

    @Query("SELECT i FROM DesignUnit i WHERE i.profilerId = :profilingDataId")
    List<DesignUnit> findAllDesignUnitsByProfilerID(@Param("profilingDataId") Long profilingDataId);


}
