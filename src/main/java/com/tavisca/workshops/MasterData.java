package com.tavisca.workshops;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MasterData {

    private Map<String,String> conversionData;
    private Map<String, Float> creditData;

    public MasterData()
    {
        this.conversionData = new HashMap<>();
        this.creditData = new HashMap<>();
    }

    public String getRomanFormatOf(String data) throws NoSuchWordException{
        if(this.conversionData.containsKey(data)){
            return this.conversionData.get(data);
        }
        else {
            throw new NoSuchWordException(data+" Is not a Valid Word");
        }
    }

    public void saveConversionData(String[] data)
    {
        conversionData.put(data[0],data[1]);
    }

    public void saveCreditData(String metal,Float price){
        this.creditData.put(metal,price);
    }

    public Float getCreditData(String metal) throws NoSuchMetalException{
        if(this.creditData.containsKey(metal)){
            return this.creditData.get(metal);
        }
        else{
            throw new NoSuchMetalException("No such Metal Data Found");
        }
    }

    public boolean hasCreditData(String metal) {
        return this.creditData.containsKey(metal);
    }
}
