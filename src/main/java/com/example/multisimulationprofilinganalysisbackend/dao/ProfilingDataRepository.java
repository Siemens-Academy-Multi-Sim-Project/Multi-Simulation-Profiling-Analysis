package com.example.multisimulationprofilinganalysisbackend.dao;

import com.example.multisimulationprofilinganalysisbackend.model.DesignUnit;
import com.example.multisimulationprofilinganalysisbackend.model.ProfilingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfilingDataRepository extends JpaRepository<ProfilingData, Long> {
    @Query(value = "SELECT COUNT(*) FROM (SELECT design_Composition_Name, design_Composition_Modules,design_Composition_Packages,design_Composition_Interfaces,design_Composition_Instances FROM Profiling_Data GROUP BY design_Composition_Name, design_Composition_Modules,design_Composition_Packages,design_Composition_Interfaces,design_Composition_Instances HAVING COUNT(*) > 0) AS sub", nativeQuery = true)
    String getDesignsNumber();

    @Query(value = "SELECT * FROM Profiling_Data WHERE Profiling_Data.profiling_data_clusterid = :clusterId", nativeQuery = true)
    List<ProfilingData> findProfilingDatas(@Param("clusterId") Long clusterID);
}
