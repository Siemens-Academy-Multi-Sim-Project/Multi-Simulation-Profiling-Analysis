package com.example.multisimulationprofilinganalysisbackend.dto;
import com.example.multisimulationprofilinganalysisbackend.model.ProfilingData;
import java.util.List;

public class AddProfilingDataRequest {
    private String clusterName;
    private List<ProfilingData> pd;

    public String getClusterName() {
        return clusterName;
    }
    
    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }
    public List<ProfilingData> getPd() {
        return pd;
    }
    
    public void setPd(List<ProfilingData> pd) {
        this.pd = pd;
    }
}
