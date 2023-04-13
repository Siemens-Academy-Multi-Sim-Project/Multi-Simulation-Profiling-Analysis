package com.example.multisimulationprofiling.utils.csv.properties;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.multisimulationprofiling.utils.csv.dataholders.BaseDataHolder;
import com.example.multisimulationprofiling.utils.csv.exceptions.DelimiterException;

/**
 * The base class which all {@code CSVParser} properties inherits from
 * <p> Usage: </p>
 * To create a custom property parser, inherit a child class from this base class and override
 * {@code parseProperty} which is responsible for ectracting the desired properties and their values, 
 * and {@code findProperty} which is responsible for detecting the finding the lines where the desired property exist
 * 
 * @param name The key of the property you want to extract
 * @param delimiters One or more delimiter that is expected to be found in that csv row
 * @param DATA_HOLDER_TYPE extends {@code BaseDataHolder} A class type to hold the parsed data and fits the structure of the parsed data
 * <p>Note: Only one delimiter can exist in one row at any time, and this functionality is purely meant for cases where the same row exists in multiple files but with different delimiter in each file</p>
 */
public abstract class CsvProperty <DATA_HOLDER_TYPE extends BaseDataHolder> {
    protected final String propertyName;
    protected final List<String> possibleDelimiters;
    protected DATA_HOLDER_TYPE data;

    public CsvProperty(String name, String ...delimiters){
        this.propertyName = name;
        
        var delimitersArr = new ArrayList<String>();
        for(String delimiter: delimiters){
            delimitersArr.add(delimiter);
        }
        data.init();
        this.possibleDelimiters = delimitersArr;
    }

    /**
     * Main function that parses the row and returnes the associated dataholder
     * @throws IOException If reading the csv was unsuccessful in any way
     * @throws DelimiterException If zero or more than one delimiter was found in the same row
     * @param csvFilePath A path pointing to the csv file
     */
    public DATA_HOLDER_TYPE parseProperty(String csvFilePath) throws DelimiterException, IOException{
        File csvFile = new File(csvFilePath);
        return parseProperty(csvFile);
    }


    /**
     * Main function that parses the row and returnes the associated dataholder
     * @throws IOException If reading the csv was unsuccessful in any way
     * @throws DelimiterException If zero or more than one delimiter was found in the same row
     * @param csvFile A file object of the csv file itself
     */
    public abstract DATA_HOLDER_TYPE parseProperty(File csvFile) throws DelimiterException, IOException;


    /**
     * Abstract function responsible for searching the file for the desired property
     * @param csvFile
     * @return A list of the lines that fits the defined critera in the concrete impl
     * @throws IOException
     */
    protected abstract List<String> findProperty(File csvFile) throws IOException;

    /**
     * A helper function that finds the delimiter currently being used by the line. It also works as a guard in case a line had multiple or no delimiters
     * @param line The line to check and find the delimtier of
     * @throws DelimiterException If zero or more than one delimiter was found
     * @return The delimiter being used
     */
    protected String findDelimiter(String line) throws DelimiterException{
        String foundDelimiter = null;
        for(String delimiter: possibleDelimiters){
            if(line.contains(delimiter)){
                if(foundDelimiter != null){
                    throw new DelimiterException("Multiple delimiters found in: " + line);
                }
                foundDelimiter = delimiter;
            }
        }
        if(foundDelimiter == null) throw new DelimiterException("No delimiters found in: " + line);

        return foundDelimiter;
    }
}
