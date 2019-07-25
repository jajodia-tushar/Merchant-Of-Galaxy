package com.tavisca.workshops;
import org.junit.jupiter.api.*;

import java.text.MessageFormat;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MoGTest {

    static MasterData masterData;

    @BeforeEach
    void initialization(){
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

        

    }







}
