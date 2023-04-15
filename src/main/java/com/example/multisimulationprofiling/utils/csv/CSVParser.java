package com.example.multisimulationprofiling.utils.csv;

import java.io.IOException;

import com.example.multisimulationprofiling.utils.csv.dataholders.TableDataHolder;
import com.example.multisimulationprofiling.utils.csv.exceptions.DelimiterException;
import com.example.multisimulationprofiling.utils.csv.properties.QuestaSimTableCsvProperty;

public class CSVParser {
    public static void main(String[] args) throws DelimiterException, IOException {
        QuestaSimTableCsvProperty w = new QuestaSimTableCsvProperty("/Design Unit", ",");
        TableDataHolder h =  w.parseProperty("sample-data/Silabs_performance_design_unit.csv");
        
    }
}
