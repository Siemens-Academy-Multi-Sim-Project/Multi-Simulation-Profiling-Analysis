package com.example.multisimulationprofilinganalysisbackend.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "DesignUnit")
public class DesignUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long profilerId;

    @Column(name = "Name")
    @NotNull
    private String name;

    @Column(name = "LocalHits")
    @NotNull
    private Integer localHits;

    @Column(name = "LocalPercentage")
    @NotNull
    private Double localPercentage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProfilerId() {
        return profilerId;
    }

    public void setProfilerId(Long profilerId) {
        this.profilerId = profilerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLocalHits() {
        return localHits;
    }

    public void setLocalHits(int localHits) {
        this.localHits = localHits;
    }

    public Double getLocalPercentage() {
        return localPercentage;
    }

    public void setLocalPercentage(double localPercentage) {
        this.localPercentage = localPercentage;
    }
}


