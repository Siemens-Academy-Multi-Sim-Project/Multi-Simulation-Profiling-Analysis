package com.example.multisimulationprofilinganalysisbackend.dao;

import com.example.multisimulationprofilinganalysisbackend.model.profilingDataClusters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilingDataClustersRepository extends JpaRepository<profilingDataClusters, Long> {
    
    @Query(value = "SELECT count(*) FROM (SELECT designcompositioninstances, designcompositionmodules,designcompositionpackages,designcompositioninterfaces,designcompositioninstances FROM profilingdata WHERE profilingdataclusterid = :clusterId GROUP BY designcompositionname, designcompositionmodules,designcompositionpackages,designcompositioninterfaces,designcompositioninstances HAVING COUNT(*) > 0 )AS sub", nativeQuery = true)
    Long getDesignsNumber(@Param("clusterId") Long clusterID);

    @Query(value = "SELECT id FROM profilingdataclusters where clustername = :clusterName", nativeQuery = true)
    Long getClusterId(@Param("clusterName") String clusterName);

    Long findIdByClusterName(String clusterName);


    @Query(value = "SELECT * FROM profilingdataclusters WHERE profilingdataclusters.id = :clusterId", nativeQuery = true)
    profilingDataClusters getClusterByID(@Param("clusterId") Long clusterID);




    
}
