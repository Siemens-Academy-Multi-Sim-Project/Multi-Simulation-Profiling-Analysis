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
    @Query(value = "SELECT COUNT(*) FROM profilingdataclusters", nativeQuery = true)
    int getclustersNumber();

    
    @Query(value = "SELECT count(*) FROM (SELECT designcompositioninstances, designcompositionmodules,designcompositionpackages,designcompositioninterfaces,designcompositioninstances FROM profilingdata WHERE profilingdataclusterid = :clusterId GROUP BY designcompositionname, designcompositionmodules,designcompositionpackages,designcompositioninterfaces,designcompositioninstances HAVING COUNT(*) > 0 )AS sub", nativeQuery = true)
    String getDesignsNumber(@Param("clusterId") Long clusterID);

    
}
