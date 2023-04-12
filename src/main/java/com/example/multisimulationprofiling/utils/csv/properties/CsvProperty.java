package com.example.multisimulationprofiling.utils.csv.properties;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.multisimulationprofiling.utils.csv.exceptions.DelimiterException;

/**
 * The base class which all {@code CSVParser} properties inherits from
 * @param name The key of the property you want to extract
 * @param delimiters One or more delimiter that is expected to be found in that csv row
 * <p>Note: Only one delimiter can exist in one row at any time, and this functionality is purely meant for cases where the same row exists in multiple files but with different delimiter in each file</p>
 */
public abstract class CsvProperty {
    protected final String propertyName;
    protected final List<String> possibleDelimiters;
    
    public String getPropertyName(){ return propertyName; }
    public List<String> getDelimiters(){ return possibleDelimiters; }

    public CsvProperty(String name, String ...delimiters){
        this.propertyName = name;
        
        var delimitersArr = new ArrayList<String>();
        for(String delimiter: delimiters){
            delimitersArr.add(delimiter);
        }

        this.possibleDelimiters = delimitersArr;
    }

    /**
     * Main function that parses the row and returnes a hashmap of property name and its value
     * @throws IOException If reading the csv was unsuccessful in any way
     * @throws DelimiterException If zero or more than one delimiter was found in the same row
     * @param csvFilePath A path pointing to the csv file
     */
    public HashMap<String, String> parseProperty(String csvFilePath) throws DelimiterException, IOException{
        File csvFile = new File(csvFilePath);
        return parseProperty(csvFile);
    }
    /**
     * Main function that parses the row and returnes a hashmap of property name and its value
     * @throws IOException If reading the csv was unsuccessful in any way
     * @throws DelimiterException If zero or more than one delimiter was found in the same row
     * @param csvFile A file object of the csv file itself
     */
    public abstract HashMap<String, String> parseProperty(File csvFile) throws DelimiterException, IOException;

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
