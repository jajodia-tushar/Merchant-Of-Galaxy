package com.tavisca.workshops;
import org.junit.jupiter.api.*;

import java.text.MessageFormat;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MoGTest {

    static MoG mog;

    @BeforeAll
    @Test
    public static void initialization(){
        mog = new MoG();
    }

    @Test
    void canSaveInterGalaticWords(){
        mog.lineRouter("glog is I");
        try {
            String expectedValue = "I";
            String actualValue = mog.getRomanNumerials("glob");
            assertEquals(expectedValue,actualValue);
        } catch (NoSuchWordException e) {
            e.printStackTrace();
        }
    }

    @Test
    void canSaveCreditDetails(){
        try {
            mog.lineRouter("glob is I");
            mog.lineRouter("glob glob Silver is 34 Credits");
            Float expectedValue = 17.0f;
            Float actualValue = mog.getTheValueOfMetalInCredits("Silver");
            assertEquals(expectedValue,actualValue);
        } catch (NoSuchMetalException e) {
            e.printStackTrace();
        }

    }

    @Test
    void canThrowExceptionForMetalsThatHasNoData(){
        mog.lineRouter("glob is I");
        mog.lineRouter("glob glob Silver is 34 Credits");
        assertThrows(NoSuchMetalException.class,() ->mog.getTheValueOfMetalInCredits("Iron"));

    }

    @Test
    void canGetIntegerValueOfInterGalaticWords(){
        mog.lineRouter("glob is I");
        mog.lineRouter("prok is V");
        mog.lineRouter("pish is X");
        mog.lineRouter("tegj is L");

        try {
           String romanNumerial =  mog.getRomanNumerials("glob");
           int expectedIntegerValue = 1;
           int actualIntegerValue = mog.getTheIntegerValueFromRoman(romanNumerial);

           assertEquals(expectedIntegerValue,actualIntegerValue);

            romanNumerial =  mog.getRomanNumerials("prok");
             expectedIntegerValue = 5;
             actualIntegerValue = mog.getTheIntegerValueFromRoman(romanNumerial);

            assertEquals(expectedIntegerValue,actualIntegerValue);

             romanNumerial =  mog.getRomanNumerials("pish");
             expectedIntegerValue = 10;
             actualIntegerValue = mog.getTheIntegerValueFromRoman(romanNumerial);

            assertEquals(expectedIntegerValue,actualIntegerValue);

             romanNumerial =  mog.getRomanNumerials("tegj");
             expectedIntegerValue = 50;
             actualIntegerValue = mog.getTheIntegerValueFromRoman(romanNumerial);

            assertEquals(expectedIntegerValue,actualIntegerValue);

        } catch (NoSuchWordException e) {
            e.printStackTrace();
        }

    }

    @Test
    void canConverInterGalaticSentencesToInteger(){
        mog.lineRouter("glob is I");
        mog.lineRouter("prok is V");
        mog.lineRouter("pish is X");
        mog.lineRouter("tegj is L");

        String expectedValue = "pish tegj glob glob is 42";
        String actualValue = mog.parseAndAnswerQuestion("how much is pish tegj glob glob ?");

        assertEquals(expectedValue,actualValue);
    }

    @Test
    void canAnswerTypeOneQuestion(){
        mog.lineRouter("glob is I");
        mog.lineRouter("prok is V");
        mog.lineRouter("pish is X");
        mog.lineRouter("tegj is L");
        mog.lineRouter("glob glob Silver is 34 Credits");

        String expectedValue = "glob prok Silver is 68 Credits";
        String actualValue = mog.parseAndAnswerQuestion("how many Credits is glob prok Silver ?");

        assertEquals(expectedValue,actualValue);
    }

    @Test
    void canAnwerTypeTwoQuestion(){
        mog.lineRouter("glob is I");
        mog.lineRouter("prok is V");
        mog.lineRouter("pish is X");
        mog.lineRouter("tegj is L");
        mog.lineRouter("glob prok Gold is 57800 Credits");

        String expectedValue = "glob prok Gold is 57800 Credits";
        String actualValue = mog.parseAndAnswerQuestion("how many Credits is glob prok Gold ?");

        assertEquals(expectedValue,actualValue);
    }

    @Test
    void canAnswerNonSenseQuestions(){
        mog.lineRouter("glob is I");
        mog.lineRouter("prok is V");
        mog.lineRouter("pish is X");
        mog.lineRouter("tegj is L");
        mog.lineRouter("glob prok Gold is 57800 Credits");
        mog.lineRouter("glob prok Gold is 57800 Credits");
        mog.lineRouter("glob prok Gold is 57800 Credits");

        String expectedValue = "I have no idea what you are talking about";
        String actualValue = mog.parseAndAnswerQuestion("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");

        assertEquals(expectedValue,actualValue);


    }







//    @Test
//    void canConvertInterGalaticWordsToRomanNumerials(){
//        canStoreConversionData();
//        CreditParser creditParser = new CreditParser();
//        try {
//            String romanRepresentation = "";
//            String interGalaticWords = creditParser.getInterGalaticWordsFromSplits(creditParser.replaceUnwantedWordsfromString("pish pish Iron is 3910 Credits"));
//            for(String words : interGalaticWords.split(" ")){
//                romanRepresentation += masterData.getRomanFormatOf(words);
//            }
//
//            String expected = "XX";
//            assertEquals(expected,romanRepresentation);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    void canStoreConversionData(){
//        masterData.saveConversionData(new WordParser().parse("glob is I"));
//        masterData.saveConversionData(new WordParser().parse("prok is V"));
//        masterData.saveConversionData(new WordParser().parse("pish is X"));
//        masterData.saveConversionData(new WordParser().parse("tegj is L"));
//        try {
//            assertEquals(masterData.getRomanFormatOf("glob"),"I");
//            assertEquals(masterData.getRomanFormatOf("prok"),"V");
//            assertEquals(masterData.getRomanFormatOf("pish"),"X");
//            assertEquals(masterData.getRomanFormatOf("tegj"),"L");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    void canStoreCreditData(){
//        canStoreConversionData();
//        CreditParser creditParser = new CreditParser();
//        try {
//            String reformedString = creditParser.replaceUnwantedWordsfromString("pish pish Iron is 3910 Credits");
//            String interGalaticWords = creditParser.getInterGalaticWordsFromSplits(reformedString);
//
//            String romanRepresentation = "";
//            for(String words : interGalaticWords.split(" ")){
//                romanRepresentation += masterData.getRomanFormatOf(words);
//            }
//
//            String price = creditParser.getPriceOfTheItem(reformedString);
//            int integerOfRomanRepresentation = new RomanToIntegerConverter().toInteger(romanRepresentation);
//
//            float unitPrice = Float.parseFloat(price)/integerOfRomanRepresentation;
//            String metalName = creditParser.getMetalFromString(reformedString);
//            masterData.saveCreditData(metalName,unitPrice);
//
//            assertEquals(masterData.getCreditData(metalName),195.5f);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }



}