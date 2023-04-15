package com.example.multisimulationprofiling.utils.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.multisimulationprofiling.utils.csv.dataholders.BaseDataHolder;
import com.example.multisimulationprofiling.utils.csv.exceptions.DelimiterException;
import com.example.multisimulationprofiling.utils.csv.properties.CsvProperty;
import com.example.multisimulationprofiling.utils.csv.properties.QuestaSimTableCsvProperty;
import com.example.multisimulationprofiling.utils.csv.properties.SingleRowValueProperty;

public class CSVParser {
    private HashMap<String, CsvProperty<BaseDataHolder>> properties;
    private HashMap<String, BaseDataHolder> parsedProperties;
    private final int BUFFERED_REDER_READ_LIMIT = 8192;

    @SafeVarargs
    public CSVParser(CsvProperty... properties){
        this.properties = new HashMap<>();
        this.parsedProperties = new HashMap<>();
        for(CsvProperty<BaseDataHolder> property: properties){
            this.properties.put(property.getPropertyName(), property);
        }
    }

    public ArrayList<BaseDataHolder> parseFile(String filePath) throws DelimiterException, IOException{
        ArrayList<BaseDataHolder> holders = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            for(String line = fileReader.readLine(); line != null; line = fileReader.readLine()){
                for(CsvProperty<BaseDataHolder> property: properties.values()){
                    if(property.isParsed()){
                        continue;
                    }
                    fileReader.mark(BUFFERED_REDER_READ_LIMIT); // mark where the property will search in the file
                    List<String> foundLines = property.findProperty(fileReader); // pass fileReader to property
                    if(foundLines.isEmpty()){ // property wasn't found, so reset to original position
                        fileReader.reset();
                    }else{
                        var parsedProperty = property.parseProperty(foundLines);
                        this.parsedProperties.put(property.getPropertyName(), parsedProperty);
                        holders.add(parsedProperty);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return holders;
    }

    public <DATA_HOLDER extends BaseDataHolder> DATA_HOLDER getHolder(String propertyName){
        return (DATA_HOLDER) properties.get(propertyName).getHolder();
    }

    public static void main(String[] args) throws DelimiterException, IOException {
        CSVParser parser = new CSVParser(
            new SingleRowValueProperty("Vsim Time", "|"),
            new SingleRowValueProperty("Vopt Time", "|"),
            new SingleRowValueProperty("Vopt Memory", "|"),
            new SingleRowValueProperty("Vsim Memory", "|"),
            new QuestaSimTableCsvProperty("/Design Unit", "|")
        );

        var w = parser.parseFile("sample-data/ethmac_554.csv");

        for(var q: w){
            q.print();
        }
    }
}
