package com.example.multisimulationprofilinganalysisbackend.controller;

import com.example.multisimulationprofilinganalysisbackend.dao.ProfilingDataClustersRepository;
import com.example.multisimulationprofilinganalysisbackend.dao.ProfilingDataRepository;
import com.example.multisimulationprofilinganalysisbackend.dto.AddProfilingDataRequest;
import com.example.multisimulationprofilinganalysisbackend.model.ProfilingData;
import com.example.multisimulationprofilinganalysisbackend.model.profilingDataClusters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfilingDataController {
    @Autowired
    private ProfilingDataRepository profilingDataRepository;
    @Autowired
    private ProfilingDataClustersRepository profilingDataClustersRepository;

    @GetMapping("profiling-data/all")
    public ResponseEntity<List<ProfilingData>> getAllProfilingData() {
        List<ProfilingData> res = profilingDataRepository.findAll();
        return ResponseEntity.ok(res);
    }

    @PostMapping("profiling-data/add")
    public ResponseEntity<ProfilingData> addProfilingData(@RequestBody ProfilingData profilingData) {

        profilingDataRepository.save(profilingData);
        return ResponseEntity.ok(profilingData);
    }


    @GetMapping("profiling-data/getDesignsNumber")
    public ResponseEntity<String> getProfilingDataDesignNumber() {
        String res = profilingDataRepository.getDesignsNumber();
        return ResponseEntity.ok(res);
    }

    @GetMapping("profiling-data-clusters/getDesignsNumber")
    public ResponseEntity<String> getProfilingDataClusterDesignNumber(@RequestBody Long clusterID) {
        String res = profilingDataClustersRepository.getDesignsNumber(clusterID);
        return ResponseEntity.ok(res);
    }

    @PostMapping("profiling-data-clusters/add")
    public ResponseEntity<profilingDataClusters> addProfilingDataClusters(@RequestBody AddProfilingDataRequest request) {
        profilingDataClusters profilingDataCluster = new profilingDataClusters();
        profilingDataCluster.setClusterName(request.getClusterName());
        profilingDataClustersRepository.save(profilingDataCluster);
        ProfilingData profilingData;
        List<ProfilingData> pd = request.getPd();
        for (ProfilingData i : pd) {
            profilingData = i;
            profilingData.setProfilingDataCluster(profilingDataCluster);
            profilingDataRepository.save(profilingData);
            System.out.println(profilingData);
        }
        return ResponseEntity.ok(profilingDataCluster);
    }

    @GetMapping("profiling-data-clusters/getProfilingData")
    public ResponseEntity<List<ProfilingData>> getProfilingDataClusterData(@RequestBody Long clusterID) {
        List<ProfilingData> res = profilingDataRepository.findProfilingDatas(clusterID);
        return ResponseEntity.ok(res);
    }

    @GetMapping("profiling-data-clusters/getByClusterName/{clusterName}")
    public ResponseEntity<profilingDataClusters> getProfilingDataClusterData(@PathVariable String clusterName) {
        long id = profilingDataClustersRepository.getClusterId(clusterName);
        profilingDataClusters res = profilingDataClustersRepository.getById(id);
        return ResponseEntity.ok(res);
    }
}
