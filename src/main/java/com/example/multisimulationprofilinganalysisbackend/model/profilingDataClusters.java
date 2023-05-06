package com.example.multisimulationprofilinganalysisbackend.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.UniqueElements;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ProfilingDataClusters", 
uniqueConstraints={
                   @UniqueConstraint(columnNames = "ClusterName")
                  })
public class profilingDataClusters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    
    
    @Column(name = "ClusterID")
    @NotNull
    private String clusterID;
    
    @Column(name = "ClusterName")
    @NotNull
    private String clusterName;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    
    public String getClusterID() {
        return clusterID;
    }

    public void setClusterID(String clusterID) {
        this.clusterID = clusterID;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }
    
}
