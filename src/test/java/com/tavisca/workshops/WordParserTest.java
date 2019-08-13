package com.tavisca.workshops;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class WordParserTest {

    @Test
    void canParseWordsToRomanNumerals(){
        WordParser wordParser = new WordParser();
        assertArrayEquals(wordParser.parse("glob is I"),new String[]{"glob","I"});
    }
}
