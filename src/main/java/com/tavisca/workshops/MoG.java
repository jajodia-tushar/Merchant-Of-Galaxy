package com.tavisca.workshops;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoG {

    MasterData masterData;
    Router router;
    WordParser wordParser;
    CreditParser creditParser;
    QuestionParser questionParser;
    RomanToIntegerConverter romanToIntegerConverter;

    public MoG(){
        this.masterData = new MasterData();
        this.router = new Router();
        this.wordParser = new WordParser();
        this.creditParser = new CreditParser();
        this.questionParser = new QuestionParser();
        this.romanToIntegerConverter = new RomanToIntegerConverter();
    }

    public void router(String string) {
        for(String line : string.split("\n")){
            lineRouter(line);
        }
    }

    public void lineRouter(String inputString){

        Pattern wordPattern = Pattern.compile(".* is [IVXLCDM]");
        Matcher wordMatcher = wordPattern.matcher(inputString);

        Pattern creditPatter = Pattern.compile(".*is [0-9]* Credits");
        Matcher creditMatcher = creditPatter.matcher(inputString);

        Pattern questionPattern = Pattern.compile("how .* is .* ?");
        Matcher questionMatcher = questionPattern.matcher(inputString);

        if(wordMatcher.find()) {
            parseAndSaveWords(inputString);
        }
        else if(creditMatcher.find()){
            parseAndSaveCredit(inputString);
        }
        else if(questionMatcher.find()){
            parseAndAnswerQuestion(inputString);
        }
        else{
            System.out.println("I have no idea what you are talking about");
        }
    }

    public String getStringRepresentationOfArray(String[] data){
        String string = "";

        for(String word : data){
            string += word + " ";
        }
        return string;
    }

    public String getRomanNumerials(String data) throws NoSuchWordException{

        String romanNumerial = "";
        for(String word : data.split(" ")){
            romanNumerial += masterData.getRomanFormatOf(word);
        }
        return romanNumerial;
//        return this.romanToIntegerConverter.toInteger(romanNumerial);
    }

    public String getRomanNumerials(String[] data) throws NoSuchWordException{

        String romanNumerial = "";
        for(String word : data){
            romanNumerial += masterData.getRomanFormatOf(word);
        }
        return romanNumerial;
        //return this.romanToIntegerConverter.toInteger(romanNumerial);
    }

    public void parseAndSaveWords(String inputString){
        String[] data = this.wordParser.parse(inputString);
        masterData.saveConversionData(data);
    }

    public void parseAndSaveCredit(String inputString) {
        try {
            String[] data = this.creditParser.parse(inputString);
            int integerValueOfWords = getTheIntegerValueFromRoman(getRomanNumerials(data[0]));

            Float unitPrice = Float.parseFloat(data[data.length-1])/integerValueOfWords;
            String metalName = data[data.length - 2];

            masterData.saveCreditData(metalName,unitPrice);

        } catch (NoSuchWordException e) {
            System.out.println(e.getMessage());
        }
    }

    public String parseAndAnswerQuestion(String inputString) {
        try {
            String[] data = new QuestionParser().parse(inputString);
            if(masterData.hasCreditData(data[data.length - 1])){

                Float unitPrice = getTheValueOfMetalInCredits(data[data.length - 1]);

                int integralValue = getTheIntegerValueFromRoman(getRomanNumerials(Arrays.copyOfRange(data,0,data.length-1)));
                float finalPrice = unitPrice * integralValue;

                return (getStringRepresentationOfArray(data)+"is "+(int)finalPrice + " Credits");
            }
            else{
                int integralValue = getTheIntegerValueFromRoman(getRomanNumerials(data));
                return (getStringRepresentationOfArray(data)+"is "+integralValue);
            }
        } catch (NoSuchWordException e) {
            return (e.getMessage());
        }
        catch (QuestionUnParsableException e){
            return (e.getMessage());
        }
        catch (NoSuchMetalException e){
            return (e.getMessage());
        }
    }

    public int getTheIntegerValueFromRoman(String romanNumerial){
        return this.romanToIntegerConverter.toInteger(romanNumerial);
    }

    public Float getTheValueOfMetalInCredits(String metal) throws NoSuchMetalException{
        return this.masterData.getCreditData(metal);
    }
}
