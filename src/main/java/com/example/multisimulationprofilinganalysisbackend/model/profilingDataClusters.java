package com.example.multisimulationprofilinganalysisbackend.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.UniqueElements;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ProfilingDataClusters",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "ClusterName")
        })
public class profilingDataClusters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ClusterName")
    @NotNull
    private String clusterName;


    private long Stored_date;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public long getStored_date() {
        return Stored_date;
    }

    public void setStored_date(long stored_date) {
        Stored_date = stored_date;
    }



    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

}
