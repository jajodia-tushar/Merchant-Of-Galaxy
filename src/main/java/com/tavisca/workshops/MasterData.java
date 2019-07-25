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

    protected void saveConversionData(String[] data)
    {
        conversionData.put(data[0],data[1]);
    }
}
