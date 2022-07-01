package root;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {

    @Value("${name}")
    private String username;

    @Value("${token}")
    private String token;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String chatId = String.valueOf(update.getMessage().getChatId());
        try {
            if (update.getMessage().getText().equals("/start")){
                sendMessage(chatId,"Вы подписались на рассылку!");

            }
            else if(update.getMessage().getText().equals("/stop")) {
                sendMessage(chatId,"Вы отписались от рассылки!");
            }

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        // этот метод срабатывает каждый раз, когда человек пишет нашему боту
        // /start - добавить челвоека в рассылку, и отправить ему сообщение об этому
        // /stop - удалить человека из базы рассылки


    }

    public void sendMessage(String chatId, String text) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode("HTML");
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        execute(sendMessage);
    }

}