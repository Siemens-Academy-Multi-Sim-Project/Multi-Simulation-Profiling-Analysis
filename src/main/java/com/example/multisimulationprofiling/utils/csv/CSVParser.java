package com.example.multisimulationprofiling.utils.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.example.multisimulationprofiling.utils.csv.dataholders.BaseDataHolder;
import com.example.multisimulationprofiling.utils.csv.dataholders.SingleRowDataHolder;
import com.example.multisimulationprofiling.utils.csv.dataholders.SingleRowValueDataHolder;
import com.example.multisimulationprofiling.utils.csv.dataholders.TableDataHolder;
import com.example.multisimulationprofiling.utils.csv.exceptions.DelimiterException;
import com.example.multisimulationprofiling.utils.csv.properties.CsvProperty;
import com.example.multisimulationprofiling.utils.csv.properties.QuestaSimTableCsvProperty;
import com.example.multisimulationprofiling.utils.csv.properties.SingleRowCsvProperty;

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

    /**
     * @param propertyName  property name of the data holder you want to get
     * @param <DATA_HOLDER> Type of dataholder related to the property provided
     * @return data holder of the given property name
     */
    public <DATA_HOLDER extends BaseDataHolder> DATA_HOLDER getHolder(String propertyName) {
        return (DATA_HOLDER) properties.get(propertyName).getHolder();
    }

    public static void main(String[] args) throws DelimiterException, IOException {
        CSVParser parser = new CSVParser(
            new SingleRowCsvProperty("Vsim Time", ","),
            new SingleRowCsvProperty("Vopt Time", ","),
            new SingleRowCsvProperty("Vopt Memory", ","),
            new SingleRowCsvProperty("Vsim Memory", ","),
            new QuestaSimTableCsvProperty("'/Design Unit (Vsim Performance Profiler)' Report", "|")
        );

        parser.parseFile("ethmac_554.csv");
        SingleRowDataHolder vsimTime = parser.getHolder("Vsim Time");
        System.out.println("Vsim Time: " + vsimTime.value);

        SingleRowDataHolder voptTime = parser.getHolder("Vopt Time");
        System.out.println("Vopt Time: " + voptTime.value);

        TableDataHolder designUnits = parser.getHolder("'/Design Unit (Vsim Performance Profiler)' Report");
        System.out.println(designUnits.table);
    }
}
