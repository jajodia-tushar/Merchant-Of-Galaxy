package com.tavisca.workshops;

import java.util.regex.Pattern;

public class QuestionParser {


    public String[] parse(String string) throws Exception {
        string = string.replace(" ?","");
        if(string.contains("how many Credits ")){
            return string.replace("how many Credits is ","").split(" ");
        }
        else if(string.contains("how much is ")){
            return string.replace("how much is ","").split(" ");
        }
        else{
            throw new Exception();
        }
    }
}
