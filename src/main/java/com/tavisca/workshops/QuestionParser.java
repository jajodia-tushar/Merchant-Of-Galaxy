package com.tavisca.workshops;

import java.util.regex.Pattern;

public class QuestionParser {

    public String[] parse(String string) throws QuestionUnParsableException {
        String reformedString = string.replace(" ?","");

        if(reformedString.contains("how many Credits ")){
           return reformedString.replace("how many Credits is ","").split(" ");
        }
        else if(reformedString.contains("how much is ")){
            return reformedString.replace("how much is ","").split(" ");
        }
        else{
            throw new QuestionUnParsableException("I have no idea what you are talking about");
        }
    }
}
