package com.example.multisimulationprofilinganalysisbackend.service;

import com.example.multisimulationprofilinganalysisbackend.appuser.AppUserRepository;
import com.example.multisimulationprofilinganalysisbackend.dto.ProfilingDataDto;
import com.example.multisimulationprofilinganalysisbackend.utils.csv.CSVParser;
import com.example.multisimulationprofilinganalysisbackend.utils.csv.dataholders.SingleRowDataHolder;
import com.example.multisimulationprofilinganalysisbackend.utils.csv.exceptions.DelimiterException;
import com.example.multisimulationprofilinganalysisbackend.utils.csv.properties.QuestaSimTableCsvProperty;
import com.example.multisimulationprofilinganalysisbackend.utils.csv.properties.SingleRowCsvProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Service
public class ExtractCSVService {
   // private final ProfilingDataDto profilingdataDto;
//    public ExtractCSVService(ProfilingDataDto profilingdataDto){
//        this.profilingdataDto=profilingdataDto;
//    }
   private final Path root = Paths.get("uploads");


    public ExtractCSVService(){}



    public void extractfile( MultipartFile file ,String filePath) throws Exception {
        String VSIM_TIME = "Vsim Time";
        String VOPT_TIME = "Vopt Time";
        String VOPT_MEMORY = "Vopt Memory";
        String VSIM_MEMORY = "Vsim Memory";
        String VOPT_COMMAND_LINE = "Vopt Command Line";
        String VSIM_COMMAND_LINE="Vsim Command Line";
        String METHODOLOGY="Methodology";
        String DESIGN_TYPE="Design Type";
        String DESIGN_COMPOSITION="Design Composition";
        String TOOL_VERSION="Tool Version";
        String PLATFORM="Platform";
        String DATE_OF_COLLECTION="Date of Collection";
        String TOTAL_WALL_TIME="Total Wall Time";
        String SOLVER_WALL_TIME="Solver Wall Time";
        String SOLVER_MEMORY="Solver Memory";
        String RANDOMIZE_CALLS="Randomize Calls";


        String DESIGN_UNIT="Design Unit";
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
                new SingleRowCsvProperty(RANDOMIZE_CALLS, "|", ",")


              //  ,new QuestaSimTableCsvProperty(DESIGN_UNIT,"|", ",")
        );
     //   saveFile(file);
        String FileDstination = filePath;


        System.out.println(FileDstination);
        parser.parseFile(FileDstination);
        SingleRowDataHolder vsimTime = parser.getHolder(VSIM_TIME);
        System.out.println(vsimTime.propertyName + ": " + vsimTime.value);

        SingleRowDataHolder voptTime = parser.getHolder(VOPT_TIME);
        System.out.println(voptTime.propertyName + ": " + voptTime.value);

        SingleRowDataHolder vsimMemory = parser.getHolder(VSIM_MEMORY);
        System.out.println(vsimMemory.propertyName + ": " + vsimMemory.value);

        SingleRowDataHolder voptMemory = parser.getHolder(VOPT_MEMORY);
        System.out.println(voptMemory.propertyName + ": " + voptMemory.value);
        System.out.println("---------------------------------------------");

        SingleRowDataHolder voptCommand=parser.getHolder(VOPT_COMMAND_LINE);
        System.out.println(voptCommand.propertyName + ": " + voptCommand.value);
        System.out.println("---------------------------------------------");

        SingleRowDataHolder vsimCommand=parser.getHolder(VSIM_COMMAND_LINE);
        System.out.println(vsimCommand.propertyName + ": " + vsimCommand.value);
        System.out.println("---------------------------------------------");

        SingleRowDataHolder methodology=parser.getHolder(METHODOLOGY);
        System.out.println(methodology.propertyName + ": " + methodology.value);
        System.out.println("---------------------------------------------");

        SingleRowDataHolder designType=parser.getHolder(DESIGN_TYPE);
        System.out.println(designType.propertyName + ": " + designType.value);
        System.out.println("---------------------------------------------");

        SingleRowDataHolder designComposition=parser.getHolder(DESIGN_COMPOSITION);
        System.out.println(designComposition.propertyName + ": " + designComposition.value);
        System.out.println("---------------------------------------------");

        SingleRowDataHolder toolVersion=parser.getHolder(TOOL_VERSION);
        System.out.println(toolVersion.propertyName + ": " + toolVersion.value);
        System.out.println("---------------------------------------------");

        SingleRowDataHolder platform=parser.getHolder(PLATFORM);
        System.out.println(platform.propertyName + ": " + platform.value);
        System.out.println("---------------------------------------------");

        SingleRowDataHolder dateOfCollection=parser.getHolder(DATE_OF_COLLECTION);
        System.out.println(dateOfCollection.propertyName + ": " + dateOfCollection.value);
        System.out.println("---------------------------------------------");

        SingleRowDataHolder totalWallTime=parser.getHolder(TOTAL_WALL_TIME);
        System.out.println(totalWallTime.propertyName + ": " + totalWallTime.value);
        System.out.println("---------------------------------------------");

        SingleRowDataHolder solverWallTime=parser.getHolder(SOLVER_WALL_TIME);
        System.out.println(solverWallTime.propertyName + ": " + solverWallTime.value);
        System.out.println("---------------------------------------------");

        SingleRowDataHolder solvermem=parser.getHolder(SOLVER_MEMORY);
        System.out.println(solvermem.propertyName + ": " + solvermem.value);
        System.out.println("---------------------------------------------");

        SingleRowDataHolder randomizecall=parser.getHolder(RANDOMIZE_CALLS);
        System.out.println(randomizecall.propertyName + ": " + randomizecall.value);
        System.out.println("---------------------------------------------");


       // System.out.println(DESIGN_UNIT );
       // System.out.println("---------------------------------------------");
    }
    private void saveFile(MultipartFile file) throws Exception {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }
}
