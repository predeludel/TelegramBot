package root.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import root.Bot;
import root.model.Item;

@Service
public class BotService {

    @Autowired
    private Bot bot;

    public void sendMessage(String chatId, Item item) throws TelegramApiException {
        bot.sendMessage(chatId, item.build());
    }

}
