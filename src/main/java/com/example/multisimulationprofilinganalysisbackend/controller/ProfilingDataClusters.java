package com.example.multisimulationprofilinganalysisbackend.controller;
import com.example.multisimulationprofilinganalysisbackend.dao.ProfilingDataClustersRepository;
import com.example.multisimulationprofilinganalysisbackend.dao.ProfilingDataRepository;
import com.example.multisimulationprofilinganalysisbackend.model.ProfilingData;
import com.example.multisimulationprofilinganalysisbackend.model.profilingDataClusters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfilingDataClusters {
    @Autowired
    private ProfilingDataClustersRepository profilingDataClustersRepository;

    // @PostMapping("profiling-data-clusters/add")
    // public ResponseEntity<profilingDataClusters> addProfilingDataClusters(@RequestBody String clusterName,@RequestBody List<ProfilingData>pd) {
    //     String clusterid=profilingDataClustersRepository.getclustersNumber();
    //     profilingDataClusters profilingDataCluster=new profilingDataClusters();
    //     profilingDataCluster.setClusterID(clusterid);
    //     profilingDataCluster.setClusterName(clusterName);
    //     profilingDataClustersRepository.save(profilingDataCluster);
    //     for (ProfilingData profilingData : pd) {
            
    //     }
    //     return ResponseEntity.ok(profilingDataCluster);
    // }
}
