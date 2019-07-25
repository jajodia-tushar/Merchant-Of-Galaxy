package com.tavisca.workshops;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreditParserTest {

    private CreditParser creditParser;

    @BeforeEach
    void initialize(){
        creditParser = new CreditParser();
    }

    @Test
    void canParseCreditStatements(){
        try {
            String[] expectedValue = new String[]{"glob prok","Gold","57800"};
            String[] actualValue = creditParser.parse("glob prok Gold is 57800 Credits");
            assertArrayEquals(expectedValue,actualValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void canMatchCreditPattern(){
        assertTrue(creditParser.matchPattern("pish pish Iron is 3910 Credits"));
    }

    @Test
    void canReplaceUnWantedWords(){
        String expectedValue = "pish pish Iron 3910";
        String actualValue = creditParser.replaceUnwantedWordsfromString("pish pish Iron is 3910 Credits");
        assertEquals(expectedValue,actualValue);
    }

    @Test
    void canReturnInterGalaticWordsFromRefinedString(){
        String refinedString = creditParser.replaceUnwantedWordsfromString("pish pish Iron is 3910 Credits");
        String interGalaticWords = creditParser.getInterGalaticWordsFromSplits(refinedString);

        String expectedValue = "pish pish";

        assertEquals(expectedValue,interGalaticWords);
    }

    @Test
    void canReturnThePriceFromRefinedString(){
        String refinedString = creditParser.replaceUnwantedWordsfromString("pish pish Iron is 3910 Credits");
        String price = creditParser.getPriceOfTheItem(refinedString);
        String expectedValue = "3910";
        assertEquals(expectedValue,price);
    }

}
