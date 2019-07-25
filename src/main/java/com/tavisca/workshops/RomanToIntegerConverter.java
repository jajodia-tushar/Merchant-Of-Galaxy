package com.tavisca.workshops;

public class RomanToIntegerConverter {

    public int toInteger(String str){
        int result = 0;
        for (int i=0; i<str.length(); i++)
        {
            int s1 = value(str.charAt(i));
            if (i+1 <str.length())
            {
                int s2 = value(str.charAt(i+1));

                if (s1 >= s2)
                {
                    result = result + s1;
                }
                else
                {
                    result = result + s2 - s1;
                    i++;
                }
            }
            else
            {
                result = result + s1;
                i++;
            }
        }
        return result;
    }

    private int value(char symbol)
    {
        if (symbol == 'I')
            return 1;
        if (symbol == 'V')
            return 5;
        if (symbol == 'X')
            return 10;
        if (symbol == 'L')
            return 50;
        if (symbol == 'C')
            return 100;
        if (symbol == 'D')
            return 500;
        if (symbol == 'M')
            return 1000;
        return -1;
    }
}
