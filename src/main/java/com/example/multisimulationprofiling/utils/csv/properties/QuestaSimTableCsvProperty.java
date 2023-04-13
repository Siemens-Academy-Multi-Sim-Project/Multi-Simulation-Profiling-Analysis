package com.example.multisimulationprofiling.utils.csv.properties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.multisimulationprofiling.utils.StringUtils;
import com.example.multisimulationprofiling.utils.csv.dataholders.TableDataHolder;
import com.example.multisimulationprofiling.utils.csv.exceptions.DelimiterException;

/**
 * The {@code QuestaSimTableCsvProperty} class is responsible for extracting tables from a csv files following QuestaSim format
 * <p>
 *  =====================================================</p><p>
 *  === '/Design Unit (Vsim Performance Profiler)' Report
 *  =====================================================
 *  </p>
 *  <p>
 *  Column 1 | Column 2 | Column 3 | Column 4
 *  </p>
 * @param name The name of the table you want to extract
 * @param delimiters One or more delimiter that is expected to be found in one table row
 * <p>Note: Only one delimiter can exist in one row at any time, and this functionality is purely meant for cases where the same row exists in multiple files but with different delimiter in each file</p>
 * 
 */
public class QuestaSimTableCsvProperty extends CsvProperty<TableDataHolder> {

    public QuestaSimTableCsvProperty(String name, String[] delimiters) {
        super(name, delimiters);
    }

    @Override
    public TableDataHolder parseProperty(File csvFile) throws DelimiterException, IOException {
        var foundLines = findProperty(csvFile);
        
        for(String line: foundLines){
            
        }
    }

    @Override
    protected List<String> findProperty(File csvFile) throws IOException {
        var reader = new BufferedReader(new FileReader(csvFile));
        var foundLines = new ArrayList<String>();
        String line = reader.readLine();
        while (line != null) {
            if(StringUtils.containsIgnoreCase(line, propertyName)){ // table found
                reader.readLine(); //skip lower delimiter row
                line = reader.readLine(); //read table header
                foundLines.add(line);
                line = reader.readLine(); //read first data row
                while(!line.isEmpty() || StringUtils.containsIgnoreCase(line, "End")){
                    foundLines.add(line);
                    line = reader.readLine();
                }
                return foundLines;
            }
            line = reader.readLine();
        }
        reader.close();
        return new ArrayList<>();
    }

}
