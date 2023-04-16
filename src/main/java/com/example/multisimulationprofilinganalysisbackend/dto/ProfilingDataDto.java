package com.example.multisimulationprofilinganalysisbackend.dto;

public class ProfilingDataDto {

    private Long id;
    private String Methodology;
    private String designType;
    private String toolVersion;
    private String platform;
    private String dateOfCollection;
    private String totalWallTime;
    private String solverWallTime;
    private String solverMemory;
    private String randomizeCall;
    private String voptTime;
    private String voptMemory;
    private String voptCMDCommand;
    private String vsimTime;
    private String vsimMemory;
    private String vsimCMDCommand;
    private String designCompositionName;
    private String designCompositionModules;
    private String designCompositionPackages;
    private String designCompositionInterfaces;

    private String designCompositionInstances;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMethodology() {
        return Methodology;
    }

    public void setMethodology(String methodology) {
        Methodology = methodology;
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
}
