package com.tavisca.workshops;
import org.junit.jupiter.api.*;

import java.text.MessageFormat;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MoGTest {

    static MasterData masterData;

    @BeforeAll
    @Test
    public static void initialization(){
        masterData = new MasterData();
    }

    @Test
    void canParseWordsToRomanNumerals(){
        WordParser wordParser = new WordParser();
        assertArrayEquals(wordParser.parse("glob is I"),new String[]{"glob","I"});
    }

    @Test
    void canStoreConversionData(){
        masterData.saveConversionData(new WordParser().parse("glob is I"));
        masterData.saveConversionData(new WordParser().parse("prok is V"));
        masterData.saveConversionData(new WordParser().parse("pish is X"));
        masterData.saveConversionData(new WordParser().parse("tegj is L"));
        try {
            assertEquals(masterData.getRomanFormatOf("glob"),"I");
            assertEquals(masterData.getRomanFormatOf("prok"),"V");
            assertEquals(masterData.getRomanFormatOf("pish"),"X");
            assertEquals(masterData.getRomanFormatOf("tegj"),"L");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void canParseCreditStatements(){
        CreditParser creditParser = new CreditParser();
        try {
            String[] expectedValue = new String[]{"glob glob","Silver","34"};
            String[] actualValue = creditParser.parse("glob glob Silver is 34 Credits");
            assertArrayEquals(expectedValue,actualValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void canConvertInterGalaticWordsToRomanNumerials(){
        canStoreConversionData();
        CreditParser creditParser = new CreditParser();
        try {
            String romanRepresentation = "";
            String interGalaticWords = creditParser.getInterGalaticWordsFromSplits(creditParser.replaceUnwantedWordsfromString("pish pish Iron is 3910 Credits"));
            for(String words : interGalaticWords.split(" ")){
                romanRepresentation += masterData.getRomanFormatOf(words);
            }

            String expected = "XX";
            assertEquals(expected,romanRepresentation);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void canStoreCreditData(){
        canStoreConversionData();
        CreditParser creditParser = new CreditParser();
        try {
            String reformedString = creditParser.replaceUnwantedWordsfromString("pish pish Iron is 3910 Credits");
            String interGalaticWords = creditParser.getInterGalaticWordsFromSplits(reformedString);

            String romanRepresentation = "";
            for(String words : interGalaticWords.split(" ")){
                romanRepresentation += masterData.getRomanFormatOf(words);
            }

            String price = creditParser.getPriceOfTheItem(reformedString);
            int integerOfRomanRepresentation = new RomanToIntegerConverter().toInteger(romanRepresentation);

            float unitPrice = Float.parseFloat(price)/integerOfRomanRepresentation;
            String metalName = creditParser.getMetalFromString(reformedString);
            masterData.saveCreditData(metalName,unitPrice);

            assertEquals(masterData.getCreditData(metalName),195.5f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void canParseQuestionStatement(){

        QuestionParser questionParser = new QuestionParser();
        try {
            String[] actualValue = questionParser.parse("how much is pish tegj glob glob ?");
            String[] expectedValue = new String[]{"pish","tegj","glob","glob"};
            assertArrayEquals(actualValue,expectedValue);

            actualValue = questionParser.parse("how many Credits is glob prok Silver ?");
            expectedValue = new String[]{"glob","prok","Silver"};
            assertArrayEquals(actualValue,expectedValue);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}