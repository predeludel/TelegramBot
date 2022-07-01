package root.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import root.Bot;

@Service
public class BotService {

    @Autowired
    private Bot bot;

    public void sendMessage(String chatId, String message) throws TelegramApiException {
        bot.sendMessage(chatId, message);
    }

}
