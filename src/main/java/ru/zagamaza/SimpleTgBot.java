package ru.zagamaza;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import static java.util.Collections.singletonList;

/**
 * This example bot is an echo bot that just repeats the messages sent to him
 */
@Component
public class SimpleTgBot extends TelegramLongPollingBot {

    @Value("${tg.token}")
    private String token;

    @Value("${tg.username}")
    private String username = "ToNamazBot";

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            SendMessage sendMessage = new SendMessage();
            //id чата откуда сообщение
            sendMessage.setChatId(update.getMessage().getChatId());
            //текст ответа(сейчас пересылка отго что пришло)
            sendMessage.setText(update.getMessage().getText());

            //дальше необязательная часть
            //добавление кнопки
            InlineKeyboardMarkup replyMarkup = new InlineKeyboardMarkup();
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText("Кнопочка");
            //То что придет ответом на нажатие на кнопку
            button.setCallbackData("Knopochka");
            replyMarkup.setKeyboard(singletonList(singletonList(button)));
            sendMessage.setReplyMarkup(replyMarkup);

            //а это отправка, можно отправлять куда угодно и сколько угодно раз, главное указать чатId
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
