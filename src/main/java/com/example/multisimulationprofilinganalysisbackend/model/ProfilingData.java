package com.example.multisimulationprofilinganalysisbackend.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ProfilingData")
public class ProfilingData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "Methodology")
    @NotNull
    private String methodology;


    @Column(name = "FileName",length = 10000)
    @NotNull
    private String FileName;

    @Column(name = "DesignType")
    @NotNull
    private String designType;


    @Column(name = "ToolVersion")
    @NotNull
    private String toolVersion;

    @Column(name = "Platform")
    @NotNull
    private String platform;

    @Column(name = "DateOfCollection")
    @NotNull
    private String dateOfCollection;

    @Column(name = "TotalWallTime")
    @NotNull
    private String totalWallTime;

    @Column(name = "SolverWallTime")
    @NotNull
    private String solverWallTime;

    @Column(name = "SolverMemory")
    @NotNull
    private String solverMemory;

    @Column(name = "RandomizeCall")
    @NotNull
    private String randomizeCall;

    //VOPT DATA
    @Column(name = "VoptTime")
    @NotNull
    private String voptTime;

    @Column(name = "VoptMemory")
    @NotNull
    private String voptMemory;

    @Column(name = "VoptCMDCommand",length=10000)
    @NotNull
    private String voptCMDCommand;

    // VSIM DATA

    @Column(name = "VsimTime")
    @NotNull
    private String vsimTime;

    @Column(name = "VsimMemory")
    @NotNull
    private String vsimMemory;

    @Column(name = "VsimCMDCommand",length=10000)
    @NotNull
    private String vsimCMDCommand;


    //DESIGN COMPOSITION DATA

    @Column(name = "DesignCompositionName", nullable = true)
    private String designCompositionName;

    @Column(name = "DesignCompositionModules", nullable = true)
    private String designCompositionModules;

    @Column(name = "DesignCompositionPackages", nullable = true)
    private String designCompositionPackages;

    @Column(name = "DesignCompositionInterfaces", nullable = true)
    private String designCompositionInterfaces;

    @Column(name = "DesignCompositionInstances", nullable = true)
    private String designCompositionInstances;


    @OneToMany(mappedBy = "profilerId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DesignUnit> designUnits = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMethodology() {
        return methodology;
    }

    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }

    public String getDesignType() {
        return designType;
    }

    public void setDesignType(String designType) {
        this.designType = designType;
    }

    public String getToolVersion() {
        return toolVersion;
    }

    public void setToolVersion(String toolVersion) {
        this.toolVersion = toolVersion;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDateOfCollection() {
        return dateOfCollection;
    }

    public void setDateOfCollection(String dateOfCollection) {
        this.dateOfCollection = dateOfCollection;
    }

    public String getTotalWallTime() {
        return totalWallTime;
    }

    public void setTotalWallTime(String totalWallTime) {
        this.totalWallTime = totalWallTime;
    }

    public String getSolverWallTime() {
        return solverWallTime;
    }

    public void setSolverWallTime(String solverWallTime) {
        this.solverWallTime = solverWallTime;
    }

    public String getSolverMemory() {
        return solverMemory;
    }

    public void setSolverMemory(String solverMemory) {
        this.solverMemory = solverMemory;
    }

    public String getRandomizeCall() {
        return randomizeCall;
    }

    public void setRandomizeCall(String randomizeCall) {
        this.randomizeCall = randomizeCall;
    }

    public String getVoptTime() {
        return voptTime;
    }

    public void setVoptTime(String voptTime) {
        this.voptTime = voptTime;
    }

    public String getVoptMemory() {
        return voptMemory;
    }

    public void setVoptMemory(String voptMemory) {
        this.voptMemory = voptMemory;
    }

    public String getVoptCMDCommand() {
        return voptCMDCommand;
    }

    public void setVoptCMDCommand(String voptCMDCommand) {
        this.voptCMDCommand = voptCMDCommand;
    }

    public String getVsimTime() {
        return vsimTime;
    }

    public void setVsimTime(String vsimTime) {
        this.vsimTime = vsimTime;
    }

    public String getVsimMemory() {
        return vsimMemory;
    }

    public void setVsimMemory(String vsimMemory) {
        this.vsimMemory = vsimMemory;
    }

    public String getVsimCMDCommand() {
        return vsimCMDCommand;
    }

    public void setVsimCMDCommand(String vsimCMDCommand) {
        this.vsimCMDCommand = vsimCMDCommand;
    }

    public String getDesignCompositionName() {
        return designCompositionName;
    }

    public void setDesignCompositionName(String designCompositionName) {
        this.designCompositionName = designCompositionName;
    }

    public String getDesignCompositionModules() {
        return designCompositionModules;
    }

    public void setDesignCompositionModules(String designCompositionModules) {
        this.designCompositionModules = designCompositionModules;
    }

    public String getDesignCompositionPackages() {
        return designCompositionPackages;
    }

    public void setDesignCompositionPackages(String designCompositionPackages) {
        this.designCompositionPackages = designCompositionPackages;
    }

    public String getDesignCompositionInterfaces() {
        return designCompositionInterfaces;
    }

    public void setDesignCompositionInterfaces(String designCompositionInterfaces) {
        this.designCompositionInterfaces = designCompositionInterfaces;
    }

    public String getDesignCompositionInstances() {
        return designCompositionInstances;
    }

    public void setDesignCompositionInstances(String designCompositionInstances) {
        this.designCompositionInstances = designCompositionInstances;
    }
    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public List<DesignUnit> getDesignUnits() {
        return designUnits;
    }

    public void setDesignUnits(List<DesignUnit> designUnits) {
        this.designUnits = designUnits;
    }
}
