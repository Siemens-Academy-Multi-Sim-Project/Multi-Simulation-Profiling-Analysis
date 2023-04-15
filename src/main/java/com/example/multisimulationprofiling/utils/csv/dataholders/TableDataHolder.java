package com.example.multisimulationprofiling.utils.csv.dataholders;

import java.util.ArrayList;
import java.util.HashMap;

public class TableDataHolder extends BaseDataHolder {
    public ArrayList<HashMap<String, String>> table;

    public TableDataHolder(){
        table = new ArrayList<>();
    }
    
    @Override
    public void init() {
        table = new ArrayList<>();
    }

    public void insertRow(String[] headers, String[] row){
        int maxSize = Math.max(headers.length, row.length); // in case some values were missing, they will be filled by emtpy String
        HashMap<String, String> rowData = new HashMap<>();
        for(int i = 0; i < maxSize; i++){
            String header = i < headers.length? headers[i]: "";
            String value = i < row.length? row[i]: "";
            rowData.put(header, value);
        }
        table.add(rowData);
    }  
     
}
