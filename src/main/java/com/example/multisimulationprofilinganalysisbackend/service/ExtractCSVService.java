package com.example.multisimulationprofilinganalysisbackend.service;

import com.example.multisimulationprofilinganalysisbackend.dao.DesignUnitRepository;
import com.example.multisimulationprofilinganalysisbackend.dao.ProfilingDataClustersRepository;
import com.example.multisimulationprofilinganalysisbackend.dao.ProfilingDataRepository;
import com.example.multisimulationprofilinganalysisbackend.model.DesignUnit;
import com.example.multisimulationprofilinganalysisbackend.model.ProfilingData;
import com.example.multisimulationprofilinganalysisbackend.model.profilingDataClusters;
import com.example.multisimulationprofilinganalysisbackend.utils.csv.CSVParser;
import com.example.multisimulationprofilinganalysisbackend.utils.csv.dataholders.SingleRowDataHolder;
import com.example.multisimulationprofilinganalysisbackend.utils.csv.dataholders.TableDataHolder;
import com.example.multisimulationprofilinganalysisbackend.utils.csv.properties.QuestaSimTableCsvProperty;
import com.example.multisimulationprofilinganalysisbackend.utils.csv.properties.SingleRowCsvProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
public class ExtractCSVService {
    private final ProfilingDataRepository profilingDataRepository;
    private final DesignUnitRepository designUnitRepository;
    private final ProfilingDataClustersRepository profilingDataClustersRepository;

    public ExtractCSVService(ProfilingDataRepository profilingDataRepository, DesignUnitRepository designUnitRepository, ProfilingDataClustersRepository profilingDataClustersRepository) {
        this.profilingDataRepository = profilingDataRepository;
        this.designUnitRepository = designUnitRepository;
        this.profilingDataClustersRepository = profilingDataClustersRepository;
    }

    public void extractfile(MultipartFile file, String filePath, String ClusterName) throws Exception {
        String VSIM_TIME = "Vsim Time";
        String VOPT_TIME = "Vopt Time";
        String VOPT_MEMORY = "Vopt Memory";
        String VSIM_MEMORY = "Vsim Memory";
        String VOPT_COMMAND_LINE = "Vopt Command Line";
        String VSIM_COMMAND_LINE = "Vsim Command Line";
        String METHODOLOGY = "Methodology";
        String DESIGN_TYPE = "Design Type";
        String DESIGN_COMPOSITION = "Design Composition";
        String TOOL_VERSION = "Tool Version";
        String PLATFORM = "Platform";
        String DATE_OF_COLLECTION = "Date of Collection";
        String TOTAL_WALL_TIME = "Total Wall Time";
        String SOLVER_WALL_TIME = "Solver Wall Time";
        String SOLVER_MEMORY = "Solver Memory";
        String RANDOMIZE_CALLS = "Randomize Calls";
        String TOTAL_SAMPLES = "Total Samples";

        String DC_MODULES = "Modules";
        String DC_PACKAGES = "Packages";
        String DC_INTERFACES = "Interfaces";
        String DC_INSTANCES = "Module Instances";

        String DESIGN_UNIT = "'/Design Unit (Vsim Performance Profiler)' Report";

        CSVParser parser = new CSVParser(
                new SingleRowCsvProperty(VSIM_TIME, "|", ","),
                new SingleRowCsvProperty(VOPT_TIME, "|", ","),
                new SingleRowCsvProperty(VOPT_MEMORY, "|", ","),
                new SingleRowCsvProperty(VSIM_MEMORY, "|", ","),
                new SingleRowCsvProperty(VOPT_COMMAND_LINE, "|", ","),
                new SingleRowCsvProperty(VSIM_COMMAND_LINE, "|", ","),
                new SingleRowCsvProperty(METHODOLOGY, "|", ","),
                new SingleRowCsvProperty(DESIGN_TYPE, "|", ","),
                new SingleRowCsvProperty(DESIGN_COMPOSITION, "|", ","),
                new SingleRowCsvProperty(TOOL_VERSION, "|", ","),
                new SingleRowCsvProperty(PLATFORM, "|", ","),
                new SingleRowCsvProperty(DATE_OF_COLLECTION, "|", ","),
                new SingleRowCsvProperty(TOTAL_WALL_TIME, "|", ","),
                new SingleRowCsvProperty(SOLVER_WALL_TIME, "|", ","),
                new SingleRowCsvProperty(SOLVER_MEMORY, "|", ","),
                new SingleRowCsvProperty(RANDOMIZE_CALLS, "|", ","),
                new SingleRowCsvProperty(TOTAL_SAMPLES, "|", ","),


                new SingleRowCsvProperty(DC_MODULES, "|", ","),
                new SingleRowCsvProperty(DC_PACKAGES, "|", ","),
                new SingleRowCsvProperty(DC_INTERFACES, "|", ","),
                new SingleRowCsvProperty(DC_INSTANCES, "|", ","),

                new QuestaSimTableCsvProperty(DESIGN_UNIT, "|", ","));

        String FileDstination = filePath;


        parser.parseFile(FileDstination);
        SingleRowDataHolder vsimTime = parser.getHolder(VSIM_TIME);
        SingleRowDataHolder voptTime = parser.getHolder(VOPT_TIME);
        SingleRowDataHolder vsimMemory = parser.getHolder(VSIM_MEMORY);
        SingleRowDataHolder voptMemory = parser.getHolder(VOPT_MEMORY);
        SingleRowDataHolder voptCommand = parser.getHolder(VOPT_COMMAND_LINE);
        SingleRowDataHolder vsimCommand = parser.getHolder(VSIM_COMMAND_LINE);
        SingleRowDataHolder methodology = parser.getHolder(METHODOLOGY);
        SingleRowDataHolder designType = parser.getHolder(DESIGN_TYPE);
        SingleRowDataHolder designComposition = parser.getHolder(DESIGN_COMPOSITION);
        SingleRowDataHolder toolVersion = parser.getHolder(TOOL_VERSION);
        SingleRowDataHolder platform = parser.getHolder(PLATFORM);
        SingleRowDataHolder dateOfCollection = parser.getHolder(DATE_OF_COLLECTION);
        SingleRowDataHolder totalWallTime = parser.getHolder(TOTAL_WALL_TIME);
        SingleRowDataHolder solverWallTime = parser.getHolder(SOLVER_WALL_TIME);
        SingleRowDataHolder solvermem = parser.getHolder(SOLVER_MEMORY);
        SingleRowDataHolder randomizecall = parser.getHolder(RANDOMIZE_CALLS);
        SingleRowDataHolder totalsamples = parser.getHolder(TOTAL_SAMPLES);
        SingleRowDataHolder DC_modules = parser.getHolder(DC_MODULES);
        SingleRowDataHolder DC_Packages = parser.getHolder(DC_PACKAGES);
        SingleRowDataHolder DC_interface = parser.getHolder(DC_INTERFACES);
        SingleRowDataHolder DC_inectance = parser.getHolder(DC_INSTANCES);


        ProfilingData profilingData = new ProfilingData();
        profilingData.setVsimTime(String.valueOf(vsimTime.value));
        profilingData.setVoptTime(String.valueOf(voptTime.value));
        profilingData.setVoptMemory(String.valueOf(voptMemory.value));
        profilingData.setVsimMemory(String.valueOf(vsimMemory.value));
        profilingData.setVoptCMDCommand(String.valueOf(voptCommand.value));
        profilingData.setVsimCMDCommand(String.valueOf(vsimCommand.value));
        profilingData.setMethodology(String.valueOf(methodology.value));
        profilingData.setDesignType(String.valueOf(designType.value));
        profilingData.setDesignCompositionName(String.valueOf(designComposition.value));
        profilingData.setToolVersion(String.valueOf(toolVersion.value));
        profilingData.setPlatform(String.valueOf(platform.value));
        profilingData.setDateOfCollection(String.valueOf(dateOfCollection.value));
        profilingData.setTotalWallTime(String.valueOf(totalWallTime.value));
        profilingData.setSolverWallTime(String.valueOf(solverWallTime.value));
        profilingData.setSolverMemory(String.valueOf(solvermem.value));
        profilingData.setRandomizeCall(String.valueOf(randomizecall.value));
        profilingData.setDesignCompositionModules(String.valueOf(DC_modules.value));
        profilingData.setDesignCompositionPackages(String.valueOf(DC_Packages.value));
        profilingData.setDesignCompositionInterfaces(String.valueOf(DC_interface.value));
        profilingData.setDesignCompositionInstances(String.valueOf(DC_inectance.value));
        if (Objects.equals(totalsamples.value, "")) {
            totalsamples.value = "0";
        }
        profilingData.setTotalSamples(Integer.parseInt(totalsamples.value));
        profilingData.setFileName(file.getOriginalFilename());
        profilingDataClusters profilingDataCluster;

        Long ClusterID = profilingDataClustersRepository.getClusterId(ClusterName);
        if (ClusterID != null) {
            profilingDataCluster = profilingDataClustersRepository.getClusterByID(ClusterID);
            profilingData.setProfilingDataCluster(profilingDataCluster);
        } else {
            profilingDataCluster = new profilingDataClusters();
            profilingDataCluster.setClusterName(ClusterName);
            profilingData.setProfilingDataCluster(profilingDataCluster);
            profilingDataClustersRepository.save(profilingDataCluster);
        }

        profilingDataRepository.save(profilingData);
        TableDataHolder designu = parser.getHolder(DESIGN_UNIT);
        if (designu.table.size() == 0) {
            return;
        }
        DesignUnit du = new DesignUnit();
        for (int i = 0; i < designu.table.size(); i++) {
            String du_name = designu.table.get(i).get("Design Unit");
            du.setName(du_name);
            String localHits = designu.table.get(i).get("Local Hits");

            if (localHits.equals("")) {
                localHits = "0";
            }

            du.setLocalHits(Integer.parseInt(localHits));
            String localPercentage = designu.table.get(i).get("Local Percentage");
            if (localPercentage.equals("")) {
                localPercentage = "0";
            }
            du.setLocalPercentage(localPercentage);
            du.setProfilerId(profilingData.getId());
        }
        designUnitRepository.save(du);
    }

}
