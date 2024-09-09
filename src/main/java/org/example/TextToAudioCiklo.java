package org.example;

import org.example.maps.MapCiklo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextToAudioCiklo {
    public void textToAudioCiklo(String text) throws IOException {

        MapCiklo map = new MapCiklo();
        String checkEndLanguage = "оль";
        String checkLanguagePath = "Ciklo_AV/";
        String endFileName = " Ц ";

        PathToAudio pathToAudio = new PathToAudio();
        Concatenate concatenate = new Concatenate();
        Duration duration = new Duration();
        RenameFile renameFileF = new RenameFile();
        IfDigitalInWords ifDigital = new IfDigitalInWords();
        String filePathName = pathToAudio.getPathToAudio() + "готовое/";
        String line;
        List<String> lines = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        Reader fr = new FileReader(pathToAudio.getPathToAudio() + "doc.txt");
        BufferedReader br = new BufferedReader(fr);

        String firstStringNoWhiteSpaceStartEnd = text.replaceAll("^\\s+", "").replaceAll("\\s+$", "");
        String fileName = firstStringNoWhiteSpaceStartEnd.toUpperCase();

        // Удаление пустых строк и пробелов в конце и в начале строк++
        while ((line = br.readLine()) != null) {
            line = line.trim(); // remove leading and trailing whitespace
            line = line.replaceAll("^\\s+", "").replaceAll("\\s+$", "");
            line = line.replaceAll("\\s+", " ").trim().concat("\n");
            line = line.replaceAll("(?m)^[ \t]*\r?\n", ""); //удалением пустых строк
            line = line.replaceAll("\\s", "_"); //Замена пробелов на '_'
            line = line.replaceAll("[^А-ё 0-9_]", ""); //Удаление символов кроме букв и цифр
            line = line.replaceAll("__", "_");// Замена двойных на одинарные


            if (!line.isEmpty()) {

                for (int i = 0; i < line.length(); i++) {
                    if (String.valueOf(line.charAt(i)).matches("-?\\d+(\\.\\d+)?")) { // Проверка на цифру(от 0 до 9) с возвратом цифруСловом
                        stringBuilder.append(ifDigital.checkDigitalChar(line.charAt(i)));
                    } else {
                        stringBuilder.append(line.charAt(i));
                    }
                }
            }
        }
        System.out.println("line " + lines);
        System.out.println();

        fr.close(); // Закрытие FileReader

        System.out.println("stringBuilder " + stringBuilder);
        String string = String.valueOf(stringBuilder);

        if (string.endsWith("_")) {
            string = string.substring(0, string.length() - 1);
        }

        System.out.println("string " + string);
        String[] language = new String[string.length()];
        String ss;
        for (
                int i = 0; i < string.length(); i++) {
            ss = map.replace(string.charAt(i));
            language[i] = ss;
        }

        try {
            concatenate.concatenateFiles(language, filePathName + fileName + endFileName);
        } catch (
                Exception e) {
            e.printStackTrace();
        }

        File fileLanguage = new File(filePathName + fileName + endFileName);

        renameFileF.renameFile(filePathName + fileName + endFileName, duration.durationFileOnly48kGh(fileLanguage), ".wav");

        String listString = String.join(",", language);
        listString = listString.replace(".wav," + pathToAudio.getPathToAudio() + checkLanguagePath, "");
        listString = listString.replace(  pathToAudio.getPathToAudio() + checkLanguagePath, "");
        listString = listString.replace(checkEndLanguage, "");

        System.out.println(endFileName + " с удалением путей++ " + listString);
    }
}


