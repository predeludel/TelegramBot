package root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import root.model.Item;
import root.model.User;
import root.repository.UserRepository;
import root.service.BotService;


@RestController
@RequestMapping("/api")
public class MainController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BotService botService;

    @PostMapping("/send")
    public Item send(@RequestBody Item item) throws TelegramApiException {
        Iterable<User> users= userRepository.findAllByStatus(true);
        for (User user: users) {
            botService.sendMessage(user.getChatId(), item);
        }
        return item;
    }
}
