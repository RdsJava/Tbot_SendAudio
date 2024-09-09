package org.example;

public class IfDigitalInWords {

    public String checkDigitalChar(char digital) {

        String digitalToWord = null;

        if (digital == '0') {
            digitalToWord = "ноль";
        } else if (digital  == '1') {
            digitalToWord = "один";
        } else if (digital  == '2') {
            digitalToWord = "два";
        } else if (digital  == '3') {
            digitalToWord = "три";
        } else if (digital  == '4') {
            digitalToWord = "четыре";
        } else if (digital  == '5') {
            digitalToWord = "пять";
        } else if (digital  == '6') {
            digitalToWord = "шесть";
        } else if (digital  == '7') {
            digitalToWord = "семь";
        } else if (digital  == '8') {
            digitalToWord = "восемь";
        } else if (digital  == '9') {
            digitalToWord = "девять";
        }
        return digitalToWord;
    }
}
