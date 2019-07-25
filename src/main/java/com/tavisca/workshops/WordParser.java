package com.tavisca.workshops;
public class WordParser {
    public String[] parse(String string) {
        String[] splits = string.split(" ");
        return new String[]{splits[0],splits[2]};
    }
}
