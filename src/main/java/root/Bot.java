package root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import root.model.Item;
import root.model.User;
import root.repository.UserRepository;

@Component
public class Bot extends TelegramLongPollingBot {
    @Autowired
    UserRepository userRepository;

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
            if (update.getMessage().getText().equals("/start")) {
                Item item = new Item("Где оформить карту Unionpay и сколько это стоит","8 дебетовых карт Unionpay, которые можно открыть сейчас\n" +
                        "Зачем нужна карта Unionpay\n" +
                        "Как оформить карту Unionpay\n" +
                        "Как снять наличные с карты Unionpay\n" +
                        "Как мы искали карты");
                sendMessage(chatId, "Вы подписались на рассылку!");
                sendMessage(chatId, item.build());
                if (!userRepository.existsByChatId(chatId)) {
                    User user = new User();
                    user.setChatId(chatId);
                    user.setStatus(true);
                    userRepository.save(user);
                } else if (!userRepository.findByChatId(chatId).getStatus()) {
                    User user = userRepository.findByChatId(chatId);
                    user.setStatus(true);
                    userRepository.save(user);
                }


            } else if (update.getMessage().getText().equals("/stop")) {
                sendMessage(chatId, "Вы отписались от рассылки!");
                if (userRepository.existsByChatId(chatId)) {
                    User user = userRepository.findByChatId(chatId);
                    user.setStatus(false);
                    userRepository.save(user);
                }

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