package com.example.multisimulationprofilinganalysisbackend.utils.csv.properties;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.example.multisimulationprofilinganalysisbackend.utils.StringUtils;
import com.example.multisimulationprofilinganalysisbackend.utils.csv.dataholders.SingleRowDataHolder;
import com.example.multisimulationprofilinganalysisbackend.utils.csv.exceptions.DelimiterException;

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


public class SingleRowCsvProperty extends CsvProperty<SingleRowDataHolder> {

    public SingleRowCsvProperty(String name, String ...delimiters) {
        super(name, delimiters);
    }



    @Override
    public SingleRowDataHolder parseProperty(List<String> foundLines) throws IOException, DelimiterException {
        String line = foundLines.get(0);
        var lineDelimiter = findDelimiter(line);
        String[] splittedLine = line.split( Pattern.quote(lineDelimiter) );
        String[] trimmedLine = StringUtils.trim(splittedLine);

        dataHolder.value = trimmedLine[1];
        setParsed(true);
        return dataHolder;
    }

    /**
     * checks if lines contains the property desired
     * @throws IOException If reading the csv was unsuccessful in any way
     * @return List of lines that contain the desired property
     */
    @Override
    public List<String> findProperty(BufferedReader reader) throws IOException {
        var foundLines = new ArrayList<String>();
        String line = reader.readLine();
        if (line != null && StringUtils.containsIgnoreCase(line, propertyName)) {
            foundLines.add(line);
        }
        return foundLines;
    }



    @Override
    protected SingleRowDataHolder onCreateDataHolder() {
        var holder = new SingleRowDataHolder(this.propertyName);
        return holder;
    }
}
