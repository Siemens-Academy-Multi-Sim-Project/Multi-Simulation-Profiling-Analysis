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
    @Query(value = "SELECT COUNT(*) FROM (SELECT designcompositionname, designcompositionmodules,designcompositionpackages,designcompositioninterfaces,designcompositioninstances FROM profilingdata GROUP BY designcompositionname, designcompositionmodules,designcompositionpackages,designcompositioninterfaces,designcompositioninstances HAVING COUNT(*) > 0) AS sub", nativeQuery = true)
    Long getDesignsNumber();

    List<ProfilingData> findByProfilingDataClustersId(long Id);
}
