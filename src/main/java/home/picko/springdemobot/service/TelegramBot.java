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
    static final String M_BUTTON = "M_BUTTON";
    static final String E_BUTTON = "E_BUTTON";
    static final String S_BUTTON = "S_BUTTON";
    static final String SP_BUTTON = "SP_BUTTON";
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

            if(callbackData.equals(M_BUTTON)) {

                SendMessage message = new SendMessage();
                message.setChatId(String.valueOf(chatId));
                message.setText("Выберите аудиторию");

                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_1 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_2 = new ArrayList<>();

                var buttonM422_1 = new InlineKeyboardButton();

                buttonM422_1.setText("М422_1");
                buttonM422_1.setCallbackData("M422_1_BUTTON");

                var buttonM422_2 = new InlineKeyboardButton();

                buttonM422_2.setText("М422_2");
                buttonM422_2.setCallbackData("M422_2_BUTTON");

                var buttonM428 = new InlineKeyboardButton();

                buttonM428.setText("М428");
                buttonM428.setCallbackData("M428_BUTTON");

                var buttonM524 = new InlineKeyboardButton();

                buttonM524.setText("М524");
                buttonM524.setCallbackData("M524_BUTTON");

                var buttonM526 = new InlineKeyboardButton();

                buttonM526.setText("М526");
                buttonM526.setCallbackData("M526_BUTTON");

                var buttonM527 = new InlineKeyboardButton();

                buttonM527.setText("М527");
                buttonM527.setCallbackData("M527_BUTTON");

                var buttonM538 = new InlineKeyboardButton();

                buttonM538.setText("М538");
                buttonM538.setCallbackData("M538_BUTTON");

                rowInline_1.add(buttonM422_1);
                rowInline_1.add(buttonM422_2);
                rowInline_1.add(buttonM428);
                rowInline_1.add(buttonM524);
                rowInline_2.add(buttonM526);
                rowInline_2.add(buttonM527);
                rowInline_2.add(buttonM538);

                rowsInline.add(rowInline_1);
                rowsInline.add(rowInline_2);

                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);

                executeMessage(message);

            } else if (callbackData.equals("M422_1_BUTTON")) {

                if (cameraRepository.existsById("М422_1")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("М422_1"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("M422_2_BUTTON")) {

                if (cameraRepository.existsById("М422_2")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("М422_2"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("M428_BUTTON")) {

                if (cameraRepository.existsById("М428")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("М428"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("M524_BUTTON")) {

                if (cameraRepository.existsById("М524")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("М524"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("M526_BUTTON")) {

                if (cameraRepository.existsById("М526")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("М526"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("M527_BUTTON")) {

                if (cameraRepository.existsById("М527")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("М527"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("M538_BUTTON")) {

                if (cameraRepository.existsById("М538")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("М538"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals(E_BUTTON)) {

                SendMessage message = new SendMessage();
                message.setChatId(String.valueOf(chatId));
                message.setText("Выберите аудиторию");

                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_1 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_2 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_3 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_4 = new ArrayList<>();

                var buttonE404 = new InlineKeyboardButton();

                buttonE404.setText("Э404");
                buttonE404.setCallbackData("Э404_BUTTON");

                var buttonE406 = new InlineKeyboardButton();

                buttonE406.setText("Э406");
                buttonE406.setCallbackData("Э406_BUTTON");

                var buttonE503 = new InlineKeyboardButton();

                buttonE503.setText("Э503");
                buttonE503.setCallbackData("Э503_BUTTON");

                var buttonE507 = new InlineKeyboardButton();

                buttonE507.setText("Э507");
                buttonE507.setCallbackData("Э507_BUTTON");

                var buttonE509 = new InlineKeyboardButton();

                buttonE509.setText("Э509");
                buttonE509.setCallbackData("Э509_BUTTON");

                var buttonE511 = new InlineKeyboardButton();

                buttonE511.setText("Э511");
                buttonE511.setCallbackData("Э511_BUTTON");

                var buttonE513 = new InlineKeyboardButton();

                buttonE513.setText("Э513");
                buttonE513.setCallbackData("Э513_BUTTON");

                var buttonE514 = new InlineKeyboardButton();

                buttonE514.setText("Э514");
                buttonE514.setCallbackData("Э514_BUTTON");

                var buttonE517 = new InlineKeyboardButton();

                buttonE517.setText("Э517");
                buttonE517.setCallbackData("Э517_BUTTON");

                var buttonE518 = new InlineKeyboardButton();

                buttonE518.setText("Э518");
                buttonE518.setCallbackData("Э518_BUTTON");

                var buttonE519 = new InlineKeyboardButton();

                buttonE519.setText("Э519");
                buttonE519.setCallbackData("Э519_BUTTON");

                var buttonE520 = new InlineKeyboardButton();

                buttonE520.setText("Э520");
                buttonE520.setCallbackData("Э520_BUTTON");

                var buttonE522 = new InlineKeyboardButton();

                buttonE522.setText("Э522");
                buttonE522.setCallbackData("Э522_BUTTON");

                rowInline_1.add(buttonE404);
                rowInline_1.add(buttonE406);
                rowInline_1.add(buttonE503);
                rowInline_1.add(buttonE507);
                rowInline_2.add(buttonE509);
                rowInline_2.add(buttonE511);
                rowInline_2.add(buttonE513);
                rowInline_2.add(buttonE514);
                rowInline_3.add(buttonE517);
                rowInline_3.add(buttonE518);
                rowInline_3.add(buttonE519);
                rowInline_3.add(buttonE520);
                rowInline_4.add(buttonE522);

                rowsInline.add(rowInline_1);
                rowsInline.add(rowInline_2);
                rowsInline.add(rowInline_3);
                rowsInline.add(rowInline_4);

                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);

                executeMessage(message);

            } else if (callbackData.equals("Э404_BUTTON")) {

                if (cameraRepository.existsById("Э404")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("Э404"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("Э406_BUTTON")) {

                if (cameraRepository.existsById("Э406")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("Э406"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("Э503_BUTTON")) {

                if (cameraRepository.existsById("Э503")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("Э503"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("Э507_BUTTON")) {

                if (cameraRepository.existsById("Э507")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("Э507"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            }  else if (callbackData.equals("Э509_BUTTON")) {

                if (cameraRepository.existsById("Э509")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("Э509"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("Э511_BUTTON")) {

                if (cameraRepository.existsById("Э511")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("Э511"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("Э513_BUTTON")) {

                if (cameraRepository.existsById("Э513")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("Э513"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("Э514_BUTTON")) {

                if (cameraRepository.existsById("Э514")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("Э514"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("Э517_BUTTON")) {

                if (cameraRepository.existsById("Э517")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("Э517"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("Э518_BUTTON")) {

                if (cameraRepository.existsById("Э518")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("Э518"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("Э519_BUTTON")) {

                if (cameraRepository.existsById("Э519")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("Э519"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("Э520_BUTTON")) {

                if (cameraRepository.existsById("Э520")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("Э520"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("Э522_BUTTON")) {

                if (cameraRepository.existsById("Э522")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("Э522"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals(S_BUTTON)) {

                SendMessage message = new SendMessage();
                message.setChatId(String.valueOf(chatId));
                message.setText("Выберите аудиторию");

                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_1 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_2 = new ArrayList<>();

                var buttonS305 = new InlineKeyboardButton();

                buttonS305.setText("С305");
                buttonS305.setCallbackData("С305_BUTTON");

                var buttonS306 = new InlineKeyboardButton();

                buttonS306.setText("С306");
                buttonS306.setCallbackData("С306_BUTTON");

                var buttonS309 = new InlineKeyboardButton();

                buttonS309.setText("С309");
                buttonS309.setCallbackData("С309_BUTTON");

                var buttonS312 = new InlineKeyboardButton();

                buttonS312.setText("С312");
                buttonS312.setCallbackData("С312_BUTTON");

                var buttonS416 = new InlineKeyboardButton();

                buttonS416.setText("С416");
                buttonS416.setCallbackData("С416_BUTTON");

                var buttonS418 = new InlineKeyboardButton();

                buttonS418.setText("С418");
                buttonS418.setCallbackData("С418_BUTTON");

                var buttonS430 = new InlineKeyboardButton();

                buttonS430.setText("С430");
                buttonS430.setCallbackData("С430_BUTTON");

                var buttonS431 = new InlineKeyboardButton();

                buttonS431.setText("С431");
                buttonS431.setCallbackData("С431_BUTTON");

                rowInline_1.add(buttonS305);
                rowInline_1.add(buttonS306);
                rowInline_1.add(buttonS309);
                rowInline_1.add(buttonS312);
                rowInline_2.add(buttonS416);
                rowInline_2.add(buttonS418);
                rowInline_2.add(buttonS430);
                rowInline_2.add(buttonS431);

                rowsInline.add(rowInline_1);
                rowsInline.add(rowInline_2);

                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);

                executeMessage(message);

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
        List<InlineKeyboardButton> rowInline_1 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline_2 = new ArrayList<>();

        var buttonM = new InlineKeyboardButton();

        buttonM.setText("М");
        buttonM.setCallbackData(M_BUTTON);

        var buttonE = new InlineKeyboardButton();

        buttonE.setText("Э");
        buttonE.setCallbackData(E_BUTTON);

        var buttonS = new InlineKeyboardButton();

        buttonS.setText("С");
        buttonS.setCallbackData(S_BUTTON);

        var buttonSP = new InlineKeyboardButton();

        buttonSP.setText("СП");
        buttonSP.setCallbackData(SP_BUTTON);

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

        rowInline_1.add(buttonM);
        rowInline_1.add(buttonE);
        rowInline_1.add(buttonS);
        rowInline_1.add(buttonSP);
        rowInline_2.add(buttonI);
        rowInline_2.add(buttonMT);
        rowInline_2.add(buttonH);
        rowInline_2.add(buttonFT);

        rowsInline.add(rowInline_1);
        rowsInline.add(rowInline_2);

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