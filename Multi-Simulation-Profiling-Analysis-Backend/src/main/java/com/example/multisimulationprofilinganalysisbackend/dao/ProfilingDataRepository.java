package com.example.multisimulationprofilinganalysisbackend.dao;

import com.example.multisimulationprofilinganalysisbackend.model.ProfilingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfilingDataRepository extends JpaRepository<ProfilingData, Long> {

}
