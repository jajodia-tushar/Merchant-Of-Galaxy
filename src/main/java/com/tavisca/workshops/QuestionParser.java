package com.tavisca.workshops;

import java.util.regex.Pattern;

public class QuestionParser {

    public String[] parse(String string) throws Exception {
        String reformedString = string.replace(" ?","");

        if(reformedString.contains("how many Credits ")){
           return parseManyTypes(reformedString.replace("how many Credits is ",""));
        }
        else if(reformedString.contains("how much is ")){
            return parseMuchTypes(reformedString.replace("how much is ",""));
        }
        else{
            throw new Exception("I Have No Idea What you are taking about");
        }
    }

    private String[] parseManyTypes(String inputString){
        return inputString.split(" ");
    }

    private String[] parseMuchTypes(String inputString){
        return inputString.split(" ");
    }
}
