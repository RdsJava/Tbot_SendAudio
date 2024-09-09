package org.example;

import java.io.File;

public class Duration {

    public String durationFileOnly48kGh(File file){
        long min = file.length() * 8L / 1152000 / 60;
        long sec = file.length() * 8L / 1152000 % 60;

        return "[" + String.format("%02d-%02d", min, sec) + "]";

    }
}