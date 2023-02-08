
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class simpleBot extends TelegramLongPollingBot {

    String content = message.content();
    String picture = message.picture();
    String Hello = "Привет. Я труп. Как твои дела?";
    String Error = "Давай ты будешь пользоваться только командами?";
    String id = "AgADAgAD6qcxGwnPsUgOp7-MvnQ8GecvSw0ABGvTl7ObQNPNX7UEAAEC";
    //  String citata = message.citata();

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        System.out.print(update.getMessage().getFrom().getUserName());
        System.out.print(": ");
        System.out.print(update.getMessage().getText());
        System.out.println("");

        switch (message.getText()) {
            case "/start":

                break;
            case "Гимн":
                sendMsg(message,content);

                break;
            case "Котик":
                sendMsg(message,picture);
                break;
            case "Фото":

               sendPhoto(chatId,"cat","Photo");

                break;
            case "Цитата":
                ArrayList<String> list = new ArrayList<>();
                try (Scanner scan = new Scanner(new File("citaty.txt"))) {
                    while (scan.hasNextLine()) {
                        list.add(scan.nextLine());
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                double n = Math.random()*15;
                //System.out.println(n);
                String[] array = list.toArray(new String[(int) n]);

                String citata = array[(int) n];
                sendMsg(message,citata);

                break;
            default:
                sendMsg(message,Error);
                    /* ArrayList<String> list1 = new ArrayList<>();
                    try (Scanner scan = new Scanner(new File("Error.txt"))) {
                        while (scan.hasNextLine()) {
                            list1.add(scan.nextLine());
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    double n1 = Math.random()*15;
                    //System.out.println(n);
                    String[] array1 = list1.toArray(new String[(int) n1]);

                    String Error1 = array1[(int) n1];
                    sendMsg(message,Error1);

                    break;

                     */
                break;
        }

    }
    public void sendPhoto(String chatId, String id, String caption) throws TelegramApiException {
        SendPhoto msg = new SendPhoto();
        String filePath1 = "cat.jpg";
        InputFile cat = new InputFile(filePath1);
        msg.setChatId(chatId);
        msg.setPhoto(cat);
        msg.setCaption("Photo");

        execute(msg);
    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        // Создаем клавиатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new
                ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add("Гимн");
        keyboardFirstRow.add("Котик");

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add("Цитата");
        //keyboardSecondRow.add("Команда 4");

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        // и устанавливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);

        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        // TODO
        // System.out.println("Никнейм бота получен");

        return "@MerciSimpleBot";
    }

    @Override
    public String getBotToken() {
        // TODO
        //System.out.println("Токен получен");
        return "6094669299:AAEdHmQ4CzQfXe2fgLUkXyQvztN0wsEtPAM";
    }

}
