package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


public class MyBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "";
    }

    @Override
    public String getBotToken() {
        return "";
    }

    @Override
    public void onUpdateReceived(Update update) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        FindFile findFile = new FindFile();
        System.out.println(update.getMessage().getText());
        Message message = update.getMessage();

        // <p>Используйте &lt; вместо <, &gt; вместо >, &amp; вместо &, &quot; вместо " и &apos; вместо '.</p>
        //< ➔ &lt; //> ➔ &gt; //& ➔ &amp; //" ➔ &quot; //' ➔ &apos; или &#39;

        if (message.getText().equals("Пояснение")) {
            try {
                sendMsg(message, " <u>Первые две цифры номер тома. Следующие цифры номер страницы.</u>");
            } catch (TelegramApiException var7) {
                throw new RuntimeException(var7);
            }
        } else if (message.getText().equals("/start")) {
            try {
                sendMsg(message, "Наберите пожалуйста  название Ритма.");
            } catch (TelegramApiException var6) {
                throw new RuntimeException(var6);
            }
        } else {
            try {
                sendMsg(message, findFile.isFindFile(update.getMessage().getText()));
            } catch (TelegramApiException var5) {
                throw new RuntimeException(var5);
            }
        }
    }

    public void sendMsg(Message message, String text) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        sendMessage.setParseMode(ParseMode.HTML);
        execute(sendMessage);

        /*
         * Для этого в Telegram API у класса SendMessage есть метод setParseMode(),
         * который позволяет установить тип разметки.
         * Например ParseMode.HTML позволит форматировать текст с помощью html-тегов.
         * То есть просто у вашего объекта SendMessage вызовите вышеуказанный метод и дальше в тексте сообщения применяйте нужные теги,
         * например вот так: <b>жирный шрифт</b> <i>курсив</i>.
         * Также вы можете использовать ParseMode.MARKDOWN,
         * в этом случае подобное форматирование текста будет выглядеть так: **жирный шрифт** *курсив*
         */


        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        /* Создаем список строк клавиатуры */
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add("Пояснение");
       // keyboardFirstRow.add("Найти ритм");

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        //keyboardSecondRow.add("Команда 3");
       // keyboardSecondRow.add("Команда 4");

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);

        // и устанавливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);


    }
}
