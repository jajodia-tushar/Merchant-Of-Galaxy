package com.tavisca.workshops;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreditParser {

    public String[] parse(String inputString) throws Exception{
        if(matchPattern(inputString)){

            String refinedString = replaceUnwantedWordsfromString(inputString);
            String interGalaticWords = getInterGalaticWordsFromSplits(refinedString);
            String metal = getMetalFromString(refinedString);
            String itemPrice = getPriceOfTheItem(refinedString);

            return new String[]{interGalaticWords,metal,itemPrice};
        }
        else{
            throw new Exception("No Credit Information Found In String");
        }
    }

    public boolean matchPattern(String inputString) {
        Pattern pattern = Pattern.compile(".*is [0-9]* Credits");
        Matcher matcher = pattern.matcher(inputString);
        return matcher.find();
    }

    public String replaceUnwantedWordsfromString(String originalString){
        String finalString = originalString.replace(" is","");
        return finalString.replace(" Credits","");
    }

    public String getInterGalaticWordsFromSplits(String inputString){

        String[] arrayOfWords = inputString.split(" ");

        String interGalaticWrods = "";
        for(int i = 0 ; i < arrayOfWords.length - 2; i++){
            interGalaticWrods = interGalaticWrods + arrayOfWords[i] + " ";
        }

        return interGalaticWrods.substring(0,interGalaticWrods.length()-1);
    }

    public String getPriceOfTheItem(String inputString){

        String[] splitString = inputString.split(" ");
        return splitString[splitString.length - 1];
    }

    public String getMetalFromString(String inputString){

        String[] splitString = inputString.split(" ");
        return splitString[splitString.length - 2];
    }
}
