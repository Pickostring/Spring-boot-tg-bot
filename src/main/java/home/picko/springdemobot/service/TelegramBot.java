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

            } else if (callbackData.equals("С305_BUTTON")) {

                if (cameraRepository.existsById("С305")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("С305"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("С306_BUTTON")) {

                if (cameraRepository.existsById("С306")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("С306"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("С309_BUTTON")) {

                if (cameraRepository.existsById("С309")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("С309"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("С312_BUTTON")) {

                if (cameraRepository.existsById("С312")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("С312"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("С416_BUTTON")) {

                if (cameraRepository.existsById("С416")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("С416"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("С418_BUTTON")) {

                if (cameraRepository.existsById("С418")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("С418"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("С430_BUTTON")) {

                if (cameraRepository.existsById("С430")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("С430"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("С431_BUTTON")) {

                if (cameraRepository.existsById("С431")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("С431"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals(SP_BUTTON)) {

                SendMessage message = new SendMessage();
                message.setChatId(String.valueOf(chatId));
                message.setText("Выберите аудиторию");

                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_1 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_2 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_3 = new ArrayList<>();

                var buttonSP401 = new InlineKeyboardButton();

                buttonSP401.setText("СП401");
                buttonSP401.setCallbackData("СП401_BUTTON");

                var buttonSP402 = new InlineKeyboardButton();

                buttonSP402.setText("СП402");
                buttonSP402.setCallbackData("СП402_BUTTON");

                var buttonSP403 = new InlineKeyboardButton();

                buttonSP403.setText("СП403");
                buttonSP403.setCallbackData("СП403_BUTTON");

                var buttonSP404 = new InlineKeyboardButton();

                buttonSP404.setText("СП404");
                buttonSP404.setCallbackData("СП404_BUTTON");

                var buttonSP406 = new InlineKeyboardButton();

                buttonSP406.setText("СП406");
                buttonSP406.setCallbackData("СП406_BUTTON");

                var buttonSP501_1 = new InlineKeyboardButton();

                buttonSP501_1.setText("СП501_1");
                buttonSP501_1.setCallbackData("СП501_1_BUTTON");

                var buttonSP501_2 = new InlineKeyboardButton();

                buttonSP501_2.setText("СП501_2");
                buttonSP501_2.setCallbackData("СП501_2_BUTTON");

                var buttonSP502_1 = new InlineKeyboardButton();

                buttonSP502_1.setText("СП502_1");
                buttonSP502_1.setCallbackData("СП502_1_BUTTON");

                var buttonSP502_2 = new InlineKeyboardButton();

                buttonSP502_2.setText("СП502_2");
                buttonSP502_2.setCallbackData("СП502_2_BUTTON");

                rowInline_1.add(buttonSP401);
                rowInline_1.add(buttonSP402);
                rowInline_1.add(buttonSP403);
                rowInline_1.add(buttonSP404);
                rowInline_2.add(buttonSP406);
                rowInline_2.add(buttonSP501_1);
                rowInline_2.add(buttonSP501_2);
                rowInline_2.add(buttonSP502_1);
                rowInline_3.add(buttonSP502_2);

                rowsInline.add(rowInline_1);
                rowsInline.add(rowInline_2);
                rowsInline.add(rowInline_3);

                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);

                executeMessage(message);

            } else if (callbackData.equals("СП401_BUTTON")) {

                if (cameraRepository.existsById("СП401")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("СП401"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("СП402_BUTTON")) {

                if (cameraRepository.existsById("СП402")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("СП402"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("СП403_BUTTON")) {

                if (cameraRepository.existsById("СП403")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("СП403"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("СП404_BUTTON")) {

                if (cameraRepository.existsById("СП404")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("СП404"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("СП406_BUTTON")) {

                if (cameraRepository.existsById("СП406")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("СП406"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("СП501_1_BUTTON")) {

                if (cameraRepository.existsById("СП501_1")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("СП501_1"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("СП501_2_BUTTON")) {

                if (cameraRepository.existsById("СП501_2")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("СП501_2"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("СП502_1_BUTTON")) {

                if (cameraRepository.existsById("СП502_1")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("СП502_1"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("СП502_2_BUTTON")) {

                if (cameraRepository.existsById("СП502_2")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("СП502_2"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals(I_BUTTON)) {

                SendMessage message = new SendMessage();
                message.setChatId(String.valueOf(chatId));
                message.setText("Выберите аудиторию");

                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_1 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_2 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_3 = new ArrayList<>();

                var buttonI156_1 = new InlineKeyboardButton();

                buttonI156_1.setText("И156_1");
                buttonI156_1.setCallbackData("И156_1_BUTTON");

                var buttonI156_2 = new InlineKeyboardButton();

                buttonI156_2.setText("И156_2");
                buttonI156_2.setCallbackData("И156_2_BUTTON");

                var buttonI308 = new InlineKeyboardButton();

                buttonI308.setText("И308");
                buttonI308.setCallbackData("И308_BUTTON");

                var buttonI310 = new InlineKeyboardButton();

                buttonI310.setText("И310");
                buttonI310.setCallbackData("И310_BUTTON");

                var buttonI329 = new InlineKeyboardButton();

                buttonI329.setText("И329");
                buttonI329.setCallbackData("И329_BUTTON");

                var buttonI333 = new InlineKeyboardButton();

                buttonI333.setText("И333");
                buttonI333.setCallbackData("И333_BUTTON");

                var buttonI335 = new InlineKeyboardButton();

                buttonI335.setText("И335");
                buttonI335.setCallbackData("И335_BUTTON");

                var buttonI431 = new InlineKeyboardButton();

                buttonI431.setText("И431");
                buttonI431.setCallbackData("И431_BUTTON");

                var buttonI526 = new InlineKeyboardButton();

                buttonI526.setText("И526");
                buttonI526.setCallbackData("И526_BUTTON");

                var buttonI532 = new InlineKeyboardButton();

                buttonI532.setText("И532");
                buttonI532.setCallbackData("И532_BUTTON");

                var buttonI534 = new InlineKeyboardButton();

                buttonI534.setText("И534");
                buttonI534.setCallbackData("И534_BUTTON");

                var buttonI535 = new InlineKeyboardButton();

                buttonI535.setText("И535");
                buttonI535.setCallbackData("И535_BUTTON");

                rowInline_1.add(buttonI156_1);
                rowInline_1.add(buttonI156_2);
                rowInline_1.add(buttonI308);
                rowInline_1.add(buttonI310);
                rowInline_2.add(buttonI329);
                rowInline_2.add(buttonI333);
                rowInline_2.add(buttonI335);
                rowInline_2.add(buttonI431);
                rowInline_3.add(buttonI526);
                rowInline_3.add(buttonI532);
                rowInline_3.add(buttonI534);
                rowInline_3.add(buttonI535);

                rowsInline.add(rowInline_1);
                rowsInline.add(rowInline_2);
                rowsInline.add(rowInline_3);

                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);

                executeMessage(message);

            } else if (callbackData.equals("И156_1_BUTTON")) {

                if (cameraRepository.existsById("И156_1")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("И156_1"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            }
            else if (callbackData.equals("И156_2_BUTTON")) {

                if (cameraRepository.existsById("156_2")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("156_2"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("И308_BUTTON")) {

                if (cameraRepository.existsById("И308")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("И308"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("И310_BUTTON")) {

                if (cameraRepository.existsById("И310")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("И310"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("И329_BUTTON")) {

                if (cameraRepository.existsById("И329")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("И329"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("И333_BUTTON")) {

                if (cameraRepository.existsById("И333")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("И333"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("И335_BUTTON")) {

                if (cameraRepository.existsById("И335")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("И335"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("И431_BUTTON")) {

                if (cameraRepository.existsById("И431")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("И431"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("И526_BUTTON")) {

                if (cameraRepository.existsById("И526")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("И526"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("И532_BUTTON")) {

                if (cameraRepository.existsById("И532")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("И532"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("И534_BUTTON")) {

                if (cameraRepository.existsById("И534")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("И534"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("И535_BUTTON")) {

                if (cameraRepository.existsById("И535")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("И535"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals(MT_BUTTON)) {

                SendMessage message = new SendMessage();
                message.setChatId(String.valueOf(chatId));
                message.setText("Выберите аудиторию");

                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_1 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_2 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_3 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_4 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_5 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_6 = new ArrayList<>();

                var buttonMT139 = new InlineKeyboardButton();

                buttonMT139.setText("МТ139");
                buttonMT139.setCallbackData("МТ139_BUTTON");

                var buttonMT143 = new InlineKeyboardButton();

                buttonMT143.setText("МТ143");
                buttonMT143.setCallbackData("МТ143_BUTTON");

                var buttonMT149 = new InlineKeyboardButton();

                buttonMT149.setText("МТ149");
                buttonMT149.setCallbackData("МТ149_BUTTON");

                var buttonMT151 = new InlineKeyboardButton();

                buttonMT151.setText("МТ151");
                buttonMT151.setCallbackData("МТ151_BUTTON");

                var buttonMT204 = new InlineKeyboardButton();

                buttonMT204.setText("МТ204");
                buttonMT204.setCallbackData("МТ204_BUTTON");

                var buttonMT213 = new InlineKeyboardButton();

                buttonMT213.setText("МТ213");
                buttonMT213.setCallbackData("МТ213_BUTTON");

                var buttonMT311 = new InlineKeyboardButton();

                buttonMT311.setText("МТ311");
                buttonMT311.setCallbackData("МТ311_BUTTON");

                var buttonMT312 = new InlineKeyboardButton();

                buttonMT312.setText("МТ312");
                buttonMT312.setCallbackData("МТ312_BUTTON");

                var buttonMT314_1 = new InlineKeyboardButton();

                buttonMT314_1.setText("МТ314_1");
                buttonMT314_1.setCallbackData("МТ314_1_BUTTON");

                var buttonMT314_2 = new InlineKeyboardButton();

                buttonMT314_2.setText("МТ314_2");
                buttonMT314_2.setCallbackData("МТ314_2_BUTTON");

                var buttonMT320 = new InlineKeyboardButton();

                buttonMT320.setText("МТ320");
                buttonMT320.setCallbackData("МТ320_BUTTON");

                var buttonMT404 = new InlineKeyboardButton();

                buttonMT404.setText("МТ404");
                buttonMT404.setCallbackData("МТ404_BUTTON");

                var buttonMT405 = new InlineKeyboardButton();

                buttonMT405.setText("МТ405");
                buttonMT405.setCallbackData("МТ405_BUTTON");

                var buttonMT406 = new InlineKeyboardButton();

                buttonMT406.setText("МТ406");
                buttonMT406.setCallbackData("МТ406_BUTTON");

                var buttonMT407 = new InlineKeyboardButton();

                buttonMT407.setText("МТ407");
                buttonMT407.setCallbackData("МТ407_BUTTON");

                var buttonMT408 = new InlineKeyboardButton();

                buttonMT408.setText("МТ408");
                buttonMT408.setCallbackData("МТ408_BUTTON");

                var buttonMT409 = new InlineKeyboardButton();

                buttonMT409.setText("МТ409");
                buttonMT409.setCallbackData("МТ409_BUTTON");

                var buttonMT412 = new InlineKeyboardButton();

                buttonMT412.setText("МТ412");
                buttonMT412.setCallbackData("МТ412_BUTTON");

                var buttonMT413 = new InlineKeyboardButton();

                buttonMT413.setText("МТ413");
                buttonMT413.setCallbackData("МТ413_BUTTON");

                var buttonMT414 = new InlineKeyboardButton();

                buttonMT414.setText("МТ414");
                buttonMT414.setCallbackData("МТ414_BUTTON");

                var buttonMT415 = new InlineKeyboardButton();

                buttonMT415.setText("МТ415");
                buttonMT415.setCallbackData("МТ415_BUTTON");

                var buttonMT421 = new InlineKeyboardButton();

                buttonMT421.setText("МТ421");
                buttonMT421.setCallbackData("МТ421_BUTTON");

                var buttonMT424 = new InlineKeyboardButton();

                buttonMT424.setText("МТ424");
                buttonMT424.setCallbackData("МТ424_BUTTON");

                rowInline_1.add(buttonMT139);
                rowInline_1.add(buttonMT143);
                rowInline_1.add(buttonMT149);
                rowInline_1.add(buttonMT151);
                rowInline_2.add(buttonMT204);
                rowInline_2.add(buttonMT213);
                rowInline_2.add(buttonMT311);
                rowInline_2.add(buttonMT312);
                rowInline_3.add(buttonMT314_1);
                rowInline_3.add(buttonMT314_2);
                rowInline_3.add(buttonMT320);
                rowInline_3.add(buttonMT404);
                rowInline_4.add(buttonMT405);
                rowInline_4.add(buttonMT406);
                rowInline_4.add(buttonMT407);
                rowInline_4.add(buttonMT408);
                rowInline_5.add(buttonMT409);
                rowInline_5.add(buttonMT412);
                rowInline_5.add(buttonMT413);
                rowInline_5.add(buttonMT414);
                rowInline_6.add(buttonMT415);
                rowInline_6.add(buttonMT421);
                rowInline_6.add(buttonMT424);

                rowsInline.add(rowInline_1);
                rowsInline.add(rowInline_2);
                rowsInline.add(rowInline_3);
                rowsInline.add(rowInline_4);
                rowsInline.add(rowInline_5);
                rowsInline.add(rowInline_6);

                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);

                executeMessage(message);

            } else if (callbackData.equals("МТ139_BUTTON")) {

                if (cameraRepository.existsById("МТ139")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("МТ139"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("МТ143_BUTTON")) {

                if (cameraRepository.existsById("МТ143")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("МТ143"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("МТ149_BUTTON")) {

                if (cameraRepository.existsById("МТ149")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("МТ149"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("МТ151_BUTTON")) {

                if (cameraRepository.existsById("МТ151")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("МТ151"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("МТ204_BUTTON")) {

                if (cameraRepository.existsById("МТ204")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("МТ204"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("МТ213_BUTTON")) {

                if (cameraRepository.existsById("МТ213")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("МТ213"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("МТ311_BUTTON")) {

                if (cameraRepository.existsById("МТ311")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("МТ311"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("МТ312_BUTTON")) {

                if (cameraRepository.existsById("МТ312")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("МТ312"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("МТ314_1_BUTTON")) {

                if (cameraRepository.existsById("МТ314_1")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("МТ314_1"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("МТ314_2_BUTTON")) {

                if (cameraRepository.existsById("МТ314_2")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("МТ314_2"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("МТ320_BUTTON")) {

                if (cameraRepository.existsById("МТ320")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("МТ320"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("МТ404_BUTTON")) {

                if (cameraRepository.existsById("МТ404")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("МТ404"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("МТ405_BUTTON")) {

                if (cameraRepository.existsById("МТ405")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("МТ405"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("МТ406_BUTTON")) {

                if (cameraRepository.existsById("МТ406")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("МТ406"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("МТ407_BUTTON")) {

                if (cameraRepository.existsById("МТ407")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("МТ407"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("МТ408_BUTTON")) {

                if (cameraRepository.existsById("МТ408")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("МТ408"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("МТ409_BUTTON")) {

                if (cameraRepository.existsById("МТ409")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("МТ409"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("МТ412_BUTTON")) {

                if (cameraRepository.existsById("МТ412")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("МТ412"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("МТ413_BUTTON")) {

                if (cameraRepository.existsById("МТ413")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("МТ413"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("МТ414_BUTTON")) {

                if (cameraRepository.existsById("МТ414")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("МТ414"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("МТ415_BUTTON")) {

                if (cameraRepository.existsById("МТ415")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("МТ415"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("МТ421_BUTTON")) {

                if (cameraRepository.existsById("МТ421")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("МТ421"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("МТ424_BUTTON")) {

                if (cameraRepository.existsById("МТ424")) {

                    SendMessage message = new SendMessage();
                    String text = String.valueOf(cameraRepository.findById("МТ424"));
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

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