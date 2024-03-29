package com.example.multisimulationprofilinganalysisbackend.utils.csv.dataholders;

import java.util.ArrayList;
import java.util.HashMap;

public class TableDataHolder extends BaseDataHolder {
    public ArrayList<HashMap<String, String>> table;

    public TableDataHolder(String name){
        super(name);
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


    @Override
    public void reset() {
        table.clear();
    }

    @Override
    public String toString() {
        String allRows = "";
        for(var row: this.table){
            allRows += row.toString();
        }
        return this.propertyName + "\n" + allRows;
    }
     
}
