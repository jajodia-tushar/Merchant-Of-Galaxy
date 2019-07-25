package com.tavisca.workshops;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Router {

    public String[] route(String inputString) throws Exception{

        Pattern wordPattern = Pattern.compile(".* is [IVXLCDM]");
        Matcher wordMatcher = wordPattern.matcher(inputString);

        Pattern creditPatter = Pattern.compile(".*is [0-9]* Credits");
        Matcher creditMatcher = creditPatter.matcher(inputString);

        Pattern questionPattern = Pattern.compile("how .* is .* ?");
        Matcher questionMatcher = questionPattern.matcher(inputString);


        if(wordMatcher.find())
        {
            return new WordParser().parse(inputString);
        }
        else if(creditMatcher.find()){
            try {
                return new CreditParser().parse(inputString);
            } catch (Exception e) {
                throw e;
            }
        }
        else if(questionMatcher.find()){
            return new QuestionParser().parse(inputString);
        }
        else{
             throw new Exception("I have no idea what you are talking about");
        }
    }

}
