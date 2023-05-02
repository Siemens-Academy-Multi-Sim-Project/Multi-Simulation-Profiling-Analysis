package com.example.multisimulationprofilinganalysisbackend.utils.csv.properties;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.multisimulationprofilinganalysisbackend.utils.csv.dataholders.BaseDataHolder;
import com.example.multisimulationprofilinganalysisbackend.utils.csv.exceptions.DelimiterException;

/**
 * The base class which all {@code CSVParser} properties inherits from
 * <p> Usage: </p>
 * To create a custom property parser, inherit a child class from this base class and override
 * {@code parseProperty} which is responsible for ectracting the desired properties and their values, 
 * and {@code findProperty} which is responsible for detecting the finding the lines where the desired property exist
 * <p>Note: Only one delimiter can exist in one row at any time, and this functionality is purely meant for cases where the same row exists in multiple files but with different delimiter in each file</p>
 *  
 * @param name The key of the property you want to extract
 * @param delimiters One or more delimiter that is expected to be found in that csv row
 * @param <DATA_HOLDER_TYPE> extends {@code BaseDataHolder} A class type to hold the parsed data and fits the structure of the parsed data
 */
public abstract class CsvProperty <DATA_HOLDER_TYPE extends BaseDataHolder> {
    protected final String propertyName;
    protected final List<String> possibleDelimiters;
    protected DATA_HOLDER_TYPE dataHolder;
    private boolean isParsed = false;

    public boolean isParsed() {
        return isParsed;
    }

    public void setParsed(boolean isParsed) {
        this.isParsed = isParsed;
    }

    public String getPropertyName(){ return this.propertyName; }
    
    public CsvProperty(String name, String ...delimiters){
        this.propertyName = name;
        
        var delimitersArr = new ArrayList<String>();
        for(String delimiter: delimiters){
            delimitersArr.add(delimiter);
        }
        dataHolder = onCreateDataHolder();
        this.possibleDelimiters = delimitersArr;
    }

    /**
     * abstract initilization function to init the data holder
     * @return the initilized dataholder 
     */
    protected abstract DATA_HOLDER_TYPE onCreateDataHolder();

    public DATA_HOLDER_TYPE getHolder(){ return dataHolder; }


    /**
     * Main function that parses the row and returnes the associated dataholder
     * @param foundLines a list of line that requires parsing
     * @throws IOException If reading the csv was unsuccessful in any way
     * @throws DelimiterException If zero or more than one delimiter was found in the same row
     */
    public abstract DATA_HOLDER_TYPE parseProperty(List<String> foundLines) throws DelimiterException, IOException;


    /**
     * Abstract function responsible for searching the file for the desired property
     * @param csvFile
     * @return A list of the lines that fits the defined critera in the concrete impl
     * @throws IOException
     */
    public abstract List<String> findProperty(BufferedReader csvFile) throws IOException;

    /**
     * A helper function that finds the delimiter currently being used by the line. 
     * It returnes the first delimiter found, if there are multiple delimiters, only the first
     * recognized one is returned
     * delimiters are recognized by the order they are provided in the constructor
     * @param line The line to check and find the delimtier of
     * @throws DelimiterException If zero or more than one delimiter was found
     * @return The delimiter being used
     */
    protected String findDelimiter(String line) throws DelimiterException{
        for(String delimiter: possibleDelimiters){
            if(line.contains(delimiter)){
                return delimiter;
            }
        }
        throw new DelimiterException("No delimiters found for: " + propertyName + "\nin " + line);
    }

    public void reset(){
        setParsed(false);
        dataHolder.reset();
    }
}
