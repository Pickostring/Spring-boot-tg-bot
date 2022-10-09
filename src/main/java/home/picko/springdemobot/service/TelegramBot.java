package home.picko.springdemobot.service;

import home.picko.springdemobot.config.BotConfig;
import home.picko.springdemobot.model.CameraRepository;
import home.picko.springdemobot.model.User;
import home.picko.springdemobot.model.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    static final String HELP_TEXT = "Help text";
    static final String E_BUTTON = "E_BUTTON";
    static final String S_BUTTON = "S_BUTTON";
    static final String I_BUTTON = "I_BUTTON";
    static final String MT_BUTTON = "MT_BUTTON";
    static final String H_BUTTON = "H_BUTTON";
    static final String FT_BUTTON = "FT_BUTTON";
    @Autowired
    private CameraRepository cameraRepository;
    @Autowired
    private UserRepository userRepository;

    final BotConfig config;

    public TelegramBot(BotConfig config) {
        this.config = config;
        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/start", "get welcome text"));
        listOfCommands.add(new BotCommand("/get", "get camera IP adress"));
        listOfCommands.add(new BotCommand("/info", "how to use this bot"));
        listOfCommands.add(new BotCommand("/settings", "set your preferences"));
        try {
            this.execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
        }
        catch (TelegramApiException e){
            log.error("Error setting bot's command list: " + e.getMessage());
        }
    }

    @Override
    public String getBotUsername() { return config.getBotName(); }

    @Override
    public String getBotToken() { return config.getToken(); }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start":

                    registerUser(update.getMessage());
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;

                case "/help":

                    sendMessage(chatId, HELP_TEXT);
                    break;

                case "/get":

                    getAdress(chatId);
                    break;

                default: sendMessage(chatId, "Sorry, command was not recognised");
            }
        } else if (update.hasCallbackQuery()) {

            String callbackData = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            if(callbackData.equals("M_BUTTON")) {

                SendMessage message = new SendMessage();
                message.setChatId(String.valueOf(chatId));
                message.setText("Выберите аудиторию");

                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();

                var buttonM428_1 = new InlineKeyboardButton();

                buttonM428_1.setText("М428-1");
                buttonM428_1.setCallbackData("M428_1_BUTTON");

                var buttonM428_2 = new InlineKeyboardButton();

                buttonM428_2.setText("М428-2");
                buttonM428_2.setCallbackData("M428_2_BUTTON");

                rowInline.add(buttonM428_1);
                rowInline.add(buttonM428_2);

                rowsInline.add(rowInline);

                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);

                executeMessage(message);

            } else if (callbackData.equals("M428_1_BUTTON")) {

                if (cameraRepository.existsById("М428-1")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("М428-1"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals(E_BUTTON)) {

                String text = "Вы выбрали Э";
                executeEditMessageText(text, chatId, messageId);

            } else if (callbackData.equals(S_BUTTON)) {

                String text = "Вы выбрали С";
                executeEditMessageText(text, chatId, messageId);

            } else if (callbackData.equals(I_BUTTON)) {

                String text = "Вы выбрали И";
                executeEditMessageText(text, chatId, messageId);

            } else if (callbackData.equals(MT_BUTTON)) {

                String text = "Вы выбрали МТ";
                executeEditMessageText(text, chatId, messageId);

            } else if (callbackData.equals(H_BUTTON)) {

                String text = "Вы выбрали Х";
                executeEditMessageText(text, chatId, messageId);

            } else if (callbackData.equals(FT_BUTTON)) {

                String text = "Вы выбрали ФТ";
                executeEditMessageText(text, chatId, messageId);

            }

        }

    }

    private void registerUser(Message msg) {

        if(userRepository.findById(msg.getChatId()).isEmpty()) {

            var chatId = msg.getChatId();
            var chat = msg.getChat();

            User user = new User();

            user.setChatId(chatId);
            user.setFirstName(chat.getFirstName());
            user.setLastName(chat.getLastName());
            user.setUserName(chat.getUserName());
            user.setRegistredAt(new Timestamp(System.currentTimeMillis()));

            userRepository.save(user);
            log.info("User saved: " + user);

        }
    }

    private void getAdress(long chatId) {

        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выберите корпус");

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        var buttonM = new InlineKeyboardButton();

        buttonM.setText("М");
        buttonM.setCallbackData("M_BUTTON");

        var buttonE = new InlineKeyboardButton();

        buttonE.setText("Э");
        buttonE.setCallbackData(E_BUTTON);

        var buttonS = new InlineKeyboardButton();

        buttonS.setText("С");
        buttonS.setCallbackData(S_BUTTON);

        var buttonI = new InlineKeyboardButton();

        buttonI.setText("И");
        buttonI.setCallbackData(I_BUTTON);

        var buttonMT = new InlineKeyboardButton();

        buttonMT.setText("МТ");
        buttonMT.setCallbackData(MT_BUTTON);

        var buttonH = new InlineKeyboardButton();

        buttonH.setText("Х");
        buttonH.setCallbackData(H_BUTTON);

        var buttonFT = new InlineKeyboardButton();

        buttonFT.setText("ФТ");
        buttonFT.setCallbackData(FT_BUTTON);

        rowInline.add(buttonM);
        rowInline.add(buttonE);
        rowInline.add(buttonS);
        rowInline.add(buttonI);
        rowInline.add(buttonMT);
        rowInline.add(buttonH);
        rowInline.add(buttonFT);

        rowsInline.add(rowInline);

        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);

        executeMessage(message);

    }

    private void startCommandReceived(long chatId, String name) {

        String answer = "Hi, " + name + ", nice to meet you!";
        log.info("Replied to user " + name);

        sendMessage(chatId, answer);

    }

    private void sendMessage(long chatId, String textToSend) {

        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        executeMessage(message);

    }

    private void executeEditMessageText(String text, long chatId, long messageId) {

        EditMessageText message = new EditMessageText();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        message.setMessageId((int) messageId);

        try {
            execute(message);
        }
        catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }

    }

    private void executeMessage(SendMessage message){

        try {
            execute(message);
        }
        catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }

    }
}