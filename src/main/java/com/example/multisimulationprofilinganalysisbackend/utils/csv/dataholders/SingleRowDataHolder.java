package com.example.multisimulationprofilinganalysisbackend.utils.csv.dataholders;

public class SingleRowDataHolder extends BaseDataHolder {
    public String value;    

    public SingleRowDataHolder(String propertyName) {
        super(propertyName);
        value = "";
    }

    @Override
    public void reset() {
        value = "";
    }

    @Override
    public String toString() {
        return this.propertyName + ": " + this.value;
    }

}
