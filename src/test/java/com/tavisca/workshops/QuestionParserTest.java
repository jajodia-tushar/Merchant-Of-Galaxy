package com.tavisca.workshops;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.OffsetDateTime;

public class QuestionParserTest {

    @Test
    void canParseQuestionTypeOne(){
        QuestionParser questionParser = new QuestionParser();
        try {
            String[] actualValue = questionParser.parse("how many Credits is glob prok Silver ?");
            String[] expectedValue = new String[]{"glob","prok","Silver"};

            assertArrayEquals(expectedValue,actualValue);


        } catch (QuestionUnParsableException e) {
            e.printStackTrace();
        }
    }

    @Test
    void canParseQuestionTypeTwo(){
        try {
            QuestionParser questionParser =  new QuestionParser();
            String[] actualValue = questionParser.parse("how much is pish tegj glob glob ?");
            String[] expectedValue = new String[]{"pish","tegj","glob","glob"};

            assertArrayEquals(expectedValue,actualValue);

        } catch (QuestionUnParsableException e) {
            e.printStackTrace();
        }
    }

    @Test
    void canThrowExceptionIfTheQuestionIsNotValid(){
        QuestionParser questionParser =  new QuestionParser();
        assertThrows(QuestionUnParsableException.class,()->questionParser.parse("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"));



    }
}
