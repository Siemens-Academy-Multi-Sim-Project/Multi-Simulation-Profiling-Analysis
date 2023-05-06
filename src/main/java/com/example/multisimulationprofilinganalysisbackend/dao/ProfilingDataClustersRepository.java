package com.example.multisimulationprofilinganalysisbackend.dao;
import com.example.multisimulationprofilinganalysisbackend.model.DesignUnit;
import com.example.multisimulationprofilinganalysisbackend.model.ProfilingData;
import com.example.multisimulationprofilinganalysisbackend.model.profilingDataClusters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfilingDataClustersRepository extends JpaRepository<profilingDataClusters, Long> {
    @Query(value = "SELECT COUNT(*) FROM profiling_data_clusters", nativeQuery = true)
    String getclustersNumber();

    
    @Query(value = "SELECT count(*) FROM (SELECT design_Composition_Name, design_Composition_Modules,design_Composition_Packages,design_Composition_Interfaces,design_Composition_Instances FROM Profiling_Data WHERE profiling_data_clusterid = :clusterId GROUP BY design_Composition_Name, design_Composition_Modules,design_Composition_Packages,design_Composition_Interfaces,design_Composition_Instances HAVING COUNT(*) > 0 )AS sub", nativeQuery = true)
    String getDesignsNumber(@Param("clusterId") Long clusterID);

    
}
