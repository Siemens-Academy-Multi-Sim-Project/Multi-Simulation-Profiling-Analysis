package com.example.multisimulationprofilinganalysisbackend.controller;

import com.example.multisimulationprofilinganalysisbackend.dao.DesignUnitRepository;
import com.example.multisimulationprofilinganalysisbackend.dao.ProfilingDataRepository;
import com.example.multisimulationprofilinganalysisbackend.model.DesignUnit;
import com.example.multisimulationprofilinganalysisbackend.model.ProfilingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DesignUnitController {
    @Autowired
    private DesignUnitRepository designUnitRepository;

    @Autowired
    private ProfilingDataRepository profilingDataRepository;


    @GetMapping("design-units/{profilingDataId}")
    public ResponseEntity<List<DesignUnit>> getDesignUnitByProfilerId(@PathVariable Long profilingDataId) {
        List<DesignUnit> designUnits = new ArrayList<>();
        designUnits = designUnitRepository.findAllDesignUnitsByProfilerID(profilingDataId);
        return ResponseEntity.ok(designUnits);
    }

    @PostMapping("design-units/add/")
    public ResponseEntity<DesignUnit> addDesignUnit(@RequestBody DesignUnit designUnitRequest) {
        try {
            ProfilingData profilingData = profilingDataRepository.findById(designUnitRequest.getProfilerId()).orElseThrow(() -> new EntityNotFoundException("Profiling Data not found"));
            designUnitRepository.save(designUnitRequest);
            return ResponseEntity.ok(designUnitRequest);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
