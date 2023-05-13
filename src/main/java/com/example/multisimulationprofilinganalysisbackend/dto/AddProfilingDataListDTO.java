package com.example.multisimulationprofilinganalysisbackend.dto;
import com.example.multisimulationprofilinganalysisbackend.model.ProfilingData;
import java.util.List;

public class AddProfilingDataListDTO {
    private String clusterName;
    private List<ProfilingData> ProfilingDataList;

    public String getClusterName() {
        return clusterName;
    }
    
    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }
    public List<ProfilingData> getProfilingDataList() {
        return ProfilingDataList;
    }
    
    public void setProfilingDataList(List<ProfilingData> ProfilingDataList) {
        this.ProfilingDataList = ProfilingDataList;
    }
}
