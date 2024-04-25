package com.cams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        System.out.println(finalGenerateRandomChars(1150));
    }

    public static String generateSubRandomChars(int number) {
        String result = "";
        String reapet = "";
        int base = 26;
        if (number > base) {
            int count = number / base;
            if (number % base == 0) {
                count--;
            }
            for (int i = 0; i < count; i++) {
                reapet = reapet + "A";
            }
        }
        int count = 0;
        number = number - 1;
        while (count <= number) {
            result = Character.toString((char) ('A' + (number % base)));
            count++;
        }
        return reapet + result;
    }

    public static String finalGenerateRandomChars(int number) {
        String str = generateSubRandomChars(number);
        String s = "";
        String sub = "";
        for (int i = 1; i < str.length() - 1; i++) {
            if (str.charAt(i) == 'A' && str.charAt(i - 1) == 'A') {
                s += str.charAt(i);
            } else {
                s = "";
            }
        }
        if (s.length() > 2) {
            sub = generateSubRandomChars(s.length()-1);
          s= str.substring(1, s.length());
        }
        return str.charAt(0) +str.substring(1, str.length()).replace(s, sub);
       }
}
