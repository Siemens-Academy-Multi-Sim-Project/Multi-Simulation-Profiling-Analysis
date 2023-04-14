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
        ProfilingData res = new ProfilingData();
        res.setId(profilingData.getId());
        res.setMethodology(profilingData.getMethodology());
        res.setDesignType(profilingData.getDesignType());
        res.setToolVersion(profilingData.getToolVersion());
        res.setPlatform(profilingData.getPlatform());
        res.setDateOfCollection(profilingData.getDateOfCollection());
        res.setTotalWallTime(profilingData.getTotalWallTime());
        res.setSolverWallTime(profilingData.getSolverWallTime());
        res.setSolverMemory(profilingData.getSolverMemory());
        res.setRandomizeCall(profilingData.getRandomizeCall());
        res.setVoptTime(profilingData.getVoptTime());
        res.setVoptMemory(profilingData.getVoptMemory());
        res.setVoptCMDCommand(profilingData.getVoptCMDCommand());
        res.setVsimTime(profilingData.getVsimTime());
        res.setVsimMemory(profilingData.getVsimMemory());
        res.setVsimCMDCommand(profilingData.getVsimCMDCommand());
        res.setDesignCompositionName(profilingData.getDesignCompositionName());
        res.setDesignCompositionModules(profilingData.getDesignCompositionModules());
        res.setDesignCompositionPackages(profilingData.getDesignCompositionPackages());
        res.setDesignCompositionInterfaces(profilingData.getDesignCompositionInterfaces());
        res.setDesignCompositionInstances(profilingData.getDesignCompositionInstances());

        profilingDataRepository.save(res);
        return ResponseEntity.ok(res);
    }

}
