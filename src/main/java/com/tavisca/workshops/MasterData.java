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

    public String getRomanFormatOf(String data) throws Exception{
        if(this.conversionData.containsKey(data)){
            return this.conversionData.get(data);
        }
        else {
            throw new Exception("The Requested Word Doesn't Has Entry");
        }
    }

    public void saveConversionData(String[] data)
    {
        conversionData.put(data[0],data[1]);
    }

    public void saveCreditData(String metal,Float price){
        this.creditData.put(metal,price);
    }

    public Float getCreditData(String metal) throws Exception{
        if(this.creditData.containsKey(metal)){
            return this.creditData.get(metal);
        }
        else{
            throw new  Exception("No such Metal Data Found");
        }
    }
}
