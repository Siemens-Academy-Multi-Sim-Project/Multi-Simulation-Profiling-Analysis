package com.example.multisimulationprofilinganalysisbackend.controller;

import com.example.multisimulationprofilinganalysisbackend.dao.ProfilingDataRepository;
import com.example.multisimulationprofilinganalysisbackend.model.ProfilingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfilingDataController {
    @Autowired
    private ProfilingDataRepository profilingDataRepository;

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

}
