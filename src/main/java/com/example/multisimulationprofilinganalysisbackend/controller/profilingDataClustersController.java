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
      //  all.sort(Collections.reverseOrder());
        Collections.reverse(all);

        Iterable<profilingDataClusters>  listOfProfilingDataClusters =all;
        ArrayList<profilingDataClusters> RecentCluster = new ArrayList<profilingDataClusters>(); // Create an ArrayList object

        final int[] i = {0};
        listOfProfilingDataClusters.forEach((p) -> {
            if(i[0] <5){
                RecentCluster.add(p);

            }
            i[0]++;
        });
        for (int j = 0; j < RecentCluster.size(); j++) {
            System.out.println(RecentCluster.get(j));
        }

        return RecentCluster;


}
}
