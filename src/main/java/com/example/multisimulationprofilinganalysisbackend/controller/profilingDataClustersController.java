package com.example.multisimulationprofilinganalysisbackend.controller;

import com.example.multisimulationprofilinganalysisbackend.model.profilingDataClusters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.multisimulationprofilinganalysisbackend.dao.ProfilingDataClustersRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController

public class profilingDataClustersController {
    @Autowired
    ProfilingDataClustersRepository ProfilingDataClustersRepository;


    @GetMapping("/GetRecentClusters")
    public  ArrayList<profilingDataClusters> GetRecentClusters() throws Exception {
        List<profilingDataClusters> all = new ArrayList<profilingDataClusters>(); // Create an ArrayList object
        all=this.ProfilingDataClustersRepository.findAll();
        Collections.reverse(all);
        ArrayList<profilingDataClusters> RecentCluster = new ArrayList<profilingDataClusters>(); // Create an ArrayList object

        int numberOfClustrers=Math.min(all.size(),5);
        for (int i = 0; i < numberOfClustrers; i++) {
            RecentCluster.add(all.get(i));

        }
        return RecentCluster;


}
}
