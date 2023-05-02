package com.example.multisimulationprofilinganalysisbackend.utils.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.example.multisimulationprofilinganalysisbackend.utils.csv.dataholders.BaseDataHolder;
import com.example.multisimulationprofilinganalysisbackend.utils.csv.dataholders.SingleRowDataHolder;
import com.example.multisimulationprofilinganalysisbackend.utils.csv.dataholders.TableDataHolder;
import com.example.multisimulationprofilinganalysisbackend.utils.csv.exceptions.DelimiterException;
import com.example.multisimulationprofilinganalysisbackend.utils.csv.properties.CsvProperty;
import com.example.multisimulationprofilinganalysisbackend.utils.csv.properties.QuestaSimTableCsvProperty;
import com.example.multisimulationprofilinganalysisbackend.utils.csv.properties.SingleRowCsvProperty;

/**
 * CSV driver class, this class is responsive for taking user defined properties
 * and a file,
 * then searches and extracts these properties
 */
public class CSVParser {
    private HashMap<String, CsvProperty<BaseDataHolder>> properties;
    private HashMap<String, BaseDataHolder> parsedProperties;
    private final int BUFFERED_REDER_READ_LIMIT = 8192;

    /**
     * Main constructor
     * 
     * @param properties collection of CsvProperties
     */
    @SafeVarargs
    public CSVParser(CsvProperty... properties) {
        this.properties = new HashMap<>();
        this.parsedProperties = new HashMap<>();
        for (CsvProperty<BaseDataHolder> property : properties) {
            this.properties.put(property.getPropertyName(), property);
        }
    }

    /**
     * 
     * @param filePath a path pointing to the file you want to parse
     * @return hashmap containing names and dataholders of successfully parsed
     *         properties
     */
    public HashMap<String, BaseDataHolder> parseFile(String filePath) throws DelimiterException, IOException {
        parsedProperties.clear();
        resetProperties();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            for (String line = fileReader.readLine(); line != null; line = fileReader.readLine()) {
                fileReader.mark(BUFFERED_REDER_READ_LIMIT); // mark where the property will start searching in the
                for (CsvProperty<BaseDataHolder> property : properties.values()) {
                    if (property.isParsed()) {
                        continue;
                    }

                                                                // file
                    List<String> foundLines = property.findProperty(fileReader); // pass fileReader to property to start
                                                                                 // the search
                    if (!foundLines.isEmpty()) { // property wasn't found, so reset to original position
                        var parsedProperty = property.parseProperty(foundLines);
                        this.parsedProperties.put(property.getPropertyName(), parsedProperty);
                    }
                    fileReader.reset();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.parsedProperties;
    }

    private void resetProperties() {
        for(CsvProperty property: properties.values()){
            property.reset();
        }
    }

    /**
     * @param propertyName  property name of the data holder you want to get
     * @param <DATA_HOLDER> Type of dataholder related to the property provided
     * @return data holder of the given property name
     */
    public <DATA_HOLDER extends BaseDataHolder> DATA_HOLDER getHolder(String propertyName) {
        return (DATA_HOLDER) properties.get(propertyName).getHolder();
    }

    public static void main(String[] args) throws DelimiterException, IOException {
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
                new SingleRowCsvProperty(RANDOMIZE_CALLS, "|", ","),
                new QuestaSimTableCsvProperty(DESIGN_UNIT,"|", ",")
        );

        String[] files = {
            "ethmac_3997.csv",
        };

        for(String file: files){
            parser.parseFile(file);

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

            System.out.println(DESIGN_UNIT);
            System.out.println("---------------------------------------------");
            

        }
    }
}
