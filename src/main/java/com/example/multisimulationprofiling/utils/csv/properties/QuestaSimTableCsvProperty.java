package com.example.multisimulationprofiling.utils.csv.properties;

import java.io.BufferedReader;
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
 *  <p>
 *  =====================================================</p><p>
 *  === End '/Design Unit (Vsim Performance Profiler)' Report
 *  =====================================================
 *  </p>
 * @param name The name of the table you want to extract
 * @param delimiters One or more delimiter that is expected to be found in one table row
 * <p>Note: Only one delimiter can exist in one row at any time, and this functionality is purely meant for cases where the same row exists in multiple files but with different delimiter in each file</p>
 * 
 */
public class QuestaSimTableCsvProperty extends CsvProperty<TableDataHolder> {

    public QuestaSimTableCsvProperty(String name, String... delimiters) {
        super(name, delimiters);
    }

    @Override
    public TableDataHolder parseProperty(List<String> foundLines) throws DelimiterException, IOException {
        var usedDelimiter = findDelimiter(foundLines.get(0));
        String headerLine = foundLines.get(0);
        String[] trimmedHeaders = StringUtils.splitAndTrim(headerLine, usedDelimiter);
        for(int i = 1; i < foundLines.size(); i++){
            String rowLine = foundLines.get(i);
            String[] trimmedRow = StringUtils.splitAndTrim(rowLine, usedDelimiter);
            dataHolder.insertRow(trimmedHeaders, trimmedRow);
        }
        setParsed(true);
        return dataHolder;
    }

    @Override
    public List<String> findProperty(BufferedReader reader) throws IOException {
        var foundLines = new ArrayList<String>();
        String line = reader.readLine();
        if (line != null && StringUtils.containsIgnoreCase(line, propertyName)) {
            reader.readLine(); //skip lower delimiter row
            line = reader.readLine(); //read table header
            foundLines.add(line);
            line = reader.readLine();
            if(line.contains("-----")) line = reader.readLine(); // skip header divider if detected
            while(!line.isEmpty() 
                && !line.contains("End")
            ){
                foundLines.add(line);
                line = reader.readLine();
            }
                        
        }
        return foundLines;
    }

    @Override
    public TableDataHolder onCreateDataHolder() {
        return new TableDataHolder(this.propertyName);
    }

}
