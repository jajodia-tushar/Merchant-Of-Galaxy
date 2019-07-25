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

    static String string = "glob is I\n" +
            "prok is V\n" +
            "pish is X\n" +
            "tegj is L\n" +
            "glob glob Silver is 34 Credits\n" +
            "glob prok Gold is 57800 Credits\n" +
            "pish pish Iron is 3910 Credits\n" +
            "how much is pish tegj glob glob ?\n" +
            "how many Credits is glob prok Silver ?\n" +
            "how many Credits is glob prok Gold ?\n" +
            "how many Credits is glob prok Iron ?\n" +
            "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?";

    public static void main(String[] args) {
        MoG mog = new MoG();
        mog.router(string);
    }

    private void router(String string) {

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
            String[] data = new WordParser().parse(inputString);
            masterData.saveConversionData(data);
        }
        else if(creditMatcher.find()){
            try {
                String[] data = new CreditParser().parse(inputString);
                int integerValueOfWords = getRomanNumerials(data[0]);

                Float unitPrice = Float.parseFloat(data[data.length-1])/integerValueOfWords;
                String metalName = data[data.length - 2];

                masterData.saveCreditData(metalName,unitPrice);

            } catch (NoSuchWordException e) {
                System.out.println(e.getMessage());
            }
        }
        else if(questionMatcher.find()){
            try {
                String[] data = new QuestionParser().parse(inputString);
                if(masterData.hasCreditData(data[data.length - 1])){

                    Float unitPrice = masterData.getCreditData(data[data.length - 1]);

                    int integralValue = getRomanNumerials(Arrays.copyOfRange(data,0,data.length-1));
                    float finalPrice = unitPrice * integralValue;

                    System.out.println(getStringRepresentationOfArray(data)+"is "+(int)finalPrice + " Credits");
                }
                else{
                    int integralValue = getRomanNumerials(data);
                    System.out.println(getStringRepresentationOfArray(data)+"is "+integralValue);
                }
            } catch (NoSuchWordException e) {
                System.out.println(e.getMessage());
            }
            catch (QuestionUnParsableException e){
                System.out.println(e.getMessage());
            }
            catch (NoSuchMetalException e){
                System.out.println(e.getMessage());
            }
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

    public int getRomanNumerials(String data) throws NoSuchWordException{

        String romanNumerial = "";
        for(String word : data.split(" ")){
            romanNumerial += masterData.getRomanFormatOf(word);
        }
        return this.romanToIntegerConverter.toInteger(romanNumerial);
    }

    public int getRomanNumerials(String[] data) throws NoSuchWordException{

        String romanNumerial = "";
        for(String word : data){
            romanNumerial += masterData.getRomanFormatOf(word);
        }
        return this.romanToIntegerConverter.toInteger(romanNumerial);
    }
}
