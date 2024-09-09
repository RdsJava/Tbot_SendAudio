package org.example;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class FindFile {

    public String isFindFile(String message) {
        int count = 0;
        int countRithm = 1;
        String returnMessage = null;
        StringBuilder stringBuilder = new StringBuilder();
        File root = new File("E:\\01_Готовое\\Ритмы Розузы Леды ЫЙИ\\ЫЙИ_ЗРВ+(копии)");
        Collection files = new ArrayList<>();

        try {
            boolean recursive = true;
            files = FileUtils.listFiles(root, null, recursive);
            var iterator = files.iterator();
            if (iterator.hasNext()) {
                do {
                    File file = (File) iterator.next();
                    if (file.getName().equals(message)) {
                        System.out.println(file.getAbsolutePath());
                    }
                } while (iterator.hasNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert files != null;

        Object[] objects = files.toArray();
        String string = Arrays.toString(objects);
        String[] stringsArrayOfRhythms = string.split("\\\\");

        for (String stringWithName : stringsArrayOfRhythms) {
            String smallLetters = stringWithName.toLowerCase();
            if (smallLetters.contains(message.toLowerCase())) {
                returnMessage = stringWithName.replaceAll("_", " ");
                returnMessage = returnMessage.replaceAll(", E:", "");
                // <p>Используйте &lt <, &gt >, &amp &, &quot ", &apos '.</p>
                //< ➔ &lt; > ➔ &gt; & ➔ &amp; " ➔ &quot; ' ➔ &apos; или &#39;
                stringBuilder.append("<pre>").append(countRithm++).append(". ").append(returnMessage).append("</pre>"); //разметка https://help.cloud.just-ai.com/jaicp/channels/telegram/telegram_markup/?ysclid=lvf0y64cwc621031190
                count = 1;
            }
        }
       //stringBuilder.append("     [взять можно отсюда]").append("https://disk.yandex.ru/d/poXe4Pnh8XMl-A");
        returnMessage = String.valueOf(stringBuilder);

        if (count == 0) {
            returnMessage = "Не найдено Что делать-то?" ;
        }

        System.out.println("Ответ " + returnMessage);
        return returnMessage;
    }
}
