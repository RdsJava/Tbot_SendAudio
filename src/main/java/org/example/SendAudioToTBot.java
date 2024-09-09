package org.example;

import java.io.File;
import java.io.IOException;

public class SendAudioToTBot {

    public File sendAudioToTBot(String massage) throws IOException {

        TextToAudioCiklo textToAudioCiklo = new TextToAudioCiklo();

        textToAudioCiklo.textToAudioCiklo(massage);


        return null;
    }
}
