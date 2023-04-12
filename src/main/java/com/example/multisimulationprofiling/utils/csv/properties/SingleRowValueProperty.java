package com.example.multisimulationprofiling.utils.csv.properties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import com.example.multisimulationprofiling.utils.StringUtils;
import com.example.multisimulationprofiling.utils.csv.exceptions.DelimiterException;

/**
 * The {@code SingleRowValueProperty} class is responsible for extracting single key value pairs from a CSV row with a specified delimiter.<br>
 * 
 * It's meant to be used along with {@code CSVParser} class for Csv files parsing
 * <p>
 * An example usecase for this class is when parsing
 * {@code Vsim Time | 102 sec}. Where a row only contains two values seprated by a delimiter
 * </p>
 * 
 * @param name The key of the property you want to extract
 * @param delimiters One or more delimiter that is expected to be found in that csv row
 * <p>Note: Only one delimiter can exist in one row at any time, and this functionality is purely meant for cases where the same row exists in multiple files but with different delimiter in each file</p>
 * 
 */
public class SingleRowValueProperty extends CsvProperty {

    public SingleRowValueProperty(String name, String ...delimiters) {
        super(name, delimiters);
    }



    @Override
    public HashMap<String, String> parseProperty(File csvFile) throws IOException, DelimiterException {
        var foundLines = findProperty(csvFile);
        var returnHashMap = new HashMap<String, String>();
        for(String line: foundLines){
            var lineDelimiter = findDelimiter(line);
            String[] splittedLine = line.split( Pattern.quote(lineDelimiter) );
            String[] trimmedLine = StringUtils.trim(splittedLine);

            returnHashMap.put(propertyName, trimmedLine[1]);
        }

        return returnHashMap;
    }

    /**
     * Loops over the file line by line and checks if the property is a substring of the current line, ignoring case
     * @throws IOException If reading the csv was unsuccessful in any way
     * @return List of lines that contain the desired property
     */
    @Override
    protected List<String> findProperty(File csvFile) throws IOException {
        var reader = new BufferedReader(new FileReader(csvFile));
        var foundLines = new ArrayList<String>();
        String line = reader.readLine();
        while (line != null) {
            if(StringUtils.containsIgnoreCase(line, propertyName)){
                foundLines.add(line);
                break;
            }
            line = reader.readLine();
        }
        reader.close();
        return foundLines;
    }
    
}
