package com.example.multisimulationprofiling.utils.csv.dataholders;

public class SingleRowValueDataHolder extends BaseDataHolder {
    public String key;
    public String value;
    
    @Override
    public void init() {
        key = "";
        value = "";
    }
    
}
