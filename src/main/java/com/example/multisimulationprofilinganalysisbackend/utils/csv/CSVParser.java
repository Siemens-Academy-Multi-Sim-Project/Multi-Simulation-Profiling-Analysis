package com.example.multisimulationprofilinganalysisbackend.utils.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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
                for (CsvProperty<BaseDataHolder> property : properties.values()) {
                    if (property.isParsed()) {
                        continue;
                    }

                    fileReader.mark(BUFFERED_REDER_READ_LIMIT); // mark where the property will start searching in the
                                                                // file
                    List<String> foundLines = property.findProperty(fileReader); // pass fileReader to property to start
                                                                                 // the search
                    if (foundLines.isEmpty()) { // property wasn't found, so reset to original position
                        fileReader.reset();
                    } else {
                        var parsedProperty = property.parseProperty(foundLines);
                        this.parsedProperties.put(property.getPropertyName(), parsedProperty);
                    }
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
        CSVParser parser = new CSVParser(
            new SingleRowCsvProperty(VSIM_TIME, "|", ","),
            new SingleRowCsvProperty(VOPT_TIME, "|", ","),
            new SingleRowCsvProperty(VOPT_MEMORY, "|", ","),
            new SingleRowCsvProperty(VSIM_MEMORY, "|", ",")
        );

        String[] files = {
            "ethmac_3997.csv",
            "Silabs_design_usage_profiler_data.csv"
        };

        for(String file: files){
            System.out.println(file);
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
        }
    }
}