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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                message.setText("???????????????? ??????????????????");

                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_1 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_2 = new ArrayList<>();

                var buttonM422_1 = new InlineKeyboardButton();

                buttonM422_1.setText("??422_1");
                buttonM422_1.setCallbackData("M422_1_BUTTON");

                var buttonM422_2 = new InlineKeyboardButton();

                buttonM422_2.setText("??422_2");
                buttonM422_2.setCallbackData("M422_2_BUTTON");

                var buttonM428 = new InlineKeyboardButton();

                buttonM428.setText("??428");
                buttonM428.setCallbackData("M428_BUTTON");

                var buttonM524 = new InlineKeyboardButton();

                buttonM524.setText("??524");
                buttonM524.setCallbackData("M524_BUTTON");

                var buttonM526 = new InlineKeyboardButton();

                buttonM526.setText("??526");
                buttonM526.setCallbackData("M526_BUTTON");

                var buttonM527 = new InlineKeyboardButton();

                buttonM527.setText("??527");
                buttonM527.setCallbackData("M527_BUTTON");

                var buttonM538 = new InlineKeyboardButton();

                buttonM538.setText("??538");
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

                if (cameraRepository.existsById("??422_1")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??422_1");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("M422_2_BUTTON")) {

                if (cameraRepository.existsById("??422_2")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??422_2");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("M428_BUTTON")) {

                if (cameraRepository.existsById("??428")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??428");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("M524_BUTTON")) {

                if (cameraRepository.existsById("??524")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??524");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("M526_BUTTON")) {

                if (cameraRepository.existsById("??526")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??526");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("M527_BUTTON")) {

                if (cameraRepository.existsById("??527")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??527");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("M538_BUTTON")) {

                if (cameraRepository.existsById("??538")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??538");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals(E_BUTTON)) {

                SendMessage message = new SendMessage();
                message.setChatId(String.valueOf(chatId));
                message.setText("???????????????? ??????????????????");

                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_1 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_2 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_3 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_4 = new ArrayList<>();

                var buttonE404 = new InlineKeyboardButton();

                buttonE404.setText("??404");
                buttonE404.setCallbackData("??404_BUTTON");

                var buttonE406 = new InlineKeyboardButton();

                buttonE406.setText("??406");
                buttonE406.setCallbackData("??406_BUTTON");

                var buttonE503 = new InlineKeyboardButton();

                buttonE503.setText("??503");
                buttonE503.setCallbackData("??503_BUTTON");

                var buttonE507 = new InlineKeyboardButton();

                buttonE507.setText("??507");
                buttonE507.setCallbackData("??507_BUTTON");

                var buttonE509 = new InlineKeyboardButton();

                buttonE509.setText("??509");
                buttonE509.setCallbackData("??509_BUTTON");

                var buttonE511 = new InlineKeyboardButton();

                buttonE511.setText("??511");
                buttonE511.setCallbackData("??511_BUTTON");

                var buttonE513 = new InlineKeyboardButton();

                buttonE513.setText("??513");
                buttonE513.setCallbackData("??513_BUTTON");

                var buttonE514 = new InlineKeyboardButton();

                buttonE514.setText("??514");
                buttonE514.setCallbackData("??514_BUTTON");

                var buttonE517 = new InlineKeyboardButton();

                buttonE517.setText("??517");
                buttonE517.setCallbackData("??517_BUTTON");

                var buttonE518 = new InlineKeyboardButton();

                buttonE518.setText("??518");
                buttonE518.setCallbackData("??518_BUTTON");

                var buttonE519 = new InlineKeyboardButton();

                buttonE519.setText("??519");
                buttonE519.setCallbackData("??519_BUTTON");

                var buttonE520 = new InlineKeyboardButton();

                buttonE520.setText("??520");
                buttonE520.setCallbackData("??520_BUTTON");

                var buttonE522 = new InlineKeyboardButton();

                buttonE522.setText("??522");
                buttonE522.setCallbackData("??522_BUTTON");

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

            } else if (callbackData.equals("??404_BUTTON")) {

                if (cameraRepository.existsById("??404")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??404");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??406_BUTTON")) {

                if (cameraRepository.existsById("??406")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??406");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??503_BUTTON")) {

                if (cameraRepository.existsById("??503")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??503");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??507_BUTTON")) {

                if (cameraRepository.existsById("??507")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??507");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            }  else if (callbackData.equals("??509_BUTTON")) {

                if (cameraRepository.existsById("??509")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??509");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??511_BUTTON")) {

                if (cameraRepository.existsById("??511")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??511");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??513_BUTTON")) {

                if (cameraRepository.existsById("??513")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??513");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??514_BUTTON")) {

                if (cameraRepository.existsById("??514")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??514");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??517_BUTTON")) {

                if (cameraRepository.existsById("??517")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??517");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??518_BUTTON")) {

                if (cameraRepository.existsById("??518")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??518");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??519_BUTTON")) {

                if (cameraRepository.existsById("??519")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??519");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??520_BUTTON")) {

                if (cameraRepository.existsById("??520")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??520");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??522_BUTTON")) {

                if (cameraRepository.existsById("??522")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??522");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals(S_BUTTON)) {

                SendMessage message = new SendMessage();
                message.setChatId(String.valueOf(chatId));
                message.setText("???????????????? ??????????????????");

                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_1 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_2 = new ArrayList<>();

                var buttonS305 = new InlineKeyboardButton();

                buttonS305.setText("??305");
                buttonS305.setCallbackData("??305_BUTTON");

                var buttonS306 = new InlineKeyboardButton();

                buttonS306.setText("??306");
                buttonS306.setCallbackData("??306_BUTTON");

                var buttonS309 = new InlineKeyboardButton();

                buttonS309.setText("??309");
                buttonS309.setCallbackData("??309_BUTTON");

                var buttonS312 = new InlineKeyboardButton();

                buttonS312.setText("??312");
                buttonS312.setCallbackData("??312_BUTTON");

                var buttonS416 = new InlineKeyboardButton();

                buttonS416.setText("??416");
                buttonS416.setCallbackData("??416_BUTTON");

                var buttonS418 = new InlineKeyboardButton();

                buttonS418.setText("??418");
                buttonS418.setCallbackData("??418_BUTTON");

                var buttonS430 = new InlineKeyboardButton();

                buttonS430.setText("??430");
                buttonS430.setCallbackData("??430_BUTTON");

                var buttonS431 = new InlineKeyboardButton();

                buttonS431.setText("??431");
                buttonS431.setCallbackData("??431_BUTTON");

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

            } else if (callbackData.equals("??305_BUTTON")) {

                if (cameraRepository.existsById("??305")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??305");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??306_BUTTON")) {

                if (cameraRepository.existsById("??306")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??306");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??309_BUTTON")) {

                if (cameraRepository.existsById("??309")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??309");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??312_BUTTON")) {

                if (cameraRepository.existsById("??312")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??312");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??416_BUTTON")) {

                if (cameraRepository.existsById("??416")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??416");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??418_BUTTON")) {

                if (cameraRepository.existsById("??418")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??418");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??430_BUTTON")) {

                if (cameraRepository.existsById("??430")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??430");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??431_BUTTON")) {

                if (cameraRepository.existsById("??431")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??431");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals(SP_BUTTON)) {

                SendMessage message = new SendMessage();
                message.setChatId(String.valueOf(chatId));
                message.setText("???????????????? ??????????????????");

                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_1 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_2 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_3 = new ArrayList<>();

                var buttonSP401 = new InlineKeyboardButton();

                buttonSP401.setText("????401");
                buttonSP401.setCallbackData("????401_BUTTON");

                var buttonSP402 = new InlineKeyboardButton();

                buttonSP402.setText("????402");
                buttonSP402.setCallbackData("????402_BUTTON");

                var buttonSP403 = new InlineKeyboardButton();

                buttonSP403.setText("????403");
                buttonSP403.setCallbackData("????403_BUTTON");

                var buttonSP404 = new InlineKeyboardButton();

                buttonSP404.setText("????404");
                buttonSP404.setCallbackData("????404_BUTTON");

                var buttonSP406 = new InlineKeyboardButton();

                buttonSP406.setText("????406");
                buttonSP406.setCallbackData("????406_BUTTON");

                var buttonSP501_1 = new InlineKeyboardButton();

                buttonSP501_1.setText("????501_1");
                buttonSP501_1.setCallbackData("????501_1_BUTTON");

                var buttonSP501_2 = new InlineKeyboardButton();

                buttonSP501_2.setText("????501_2");
                buttonSP501_2.setCallbackData("????501_2_BUTTON");

                var buttonSP502_1 = new InlineKeyboardButton();

                buttonSP502_1.setText("????502_1");
                buttonSP502_1.setCallbackData("????502_1_BUTTON");

                var buttonSP502_2 = new InlineKeyboardButton();

                buttonSP502_2.setText("????502_2");
                buttonSP502_2.setCallbackData("????502_2_BUTTON");

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

            } else if (callbackData.equals("????401_BUTTON")) {

                if (cameraRepository.existsById("????401")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????401");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????402_BUTTON")) {

                if (cameraRepository.existsById("????402")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????402");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????403_BUTTON")) {

                if (cameraRepository.existsById("????403")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????403");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????404_BUTTON")) {

                if (cameraRepository.existsById("????404")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????404");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????406_BUTTON")) {

                if (cameraRepository.existsById("????406")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????406");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????501_1_BUTTON")) {

                if (cameraRepository.existsById("????501_1")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????501_1");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????501_2_BUTTON")) {

                if (cameraRepository.existsById("????501_2")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????501_2");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????502_1_BUTTON")) {

                if (cameraRepository.existsById("????502_1")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????502_1");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????502_2_BUTTON")) {

                if (cameraRepository.existsById("????502_2")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????502_2");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals(I_BUTTON)) {

                SendMessage message = new SendMessage();
                message.setChatId(String.valueOf(chatId));
                message.setText("???????????????? ??????????????????");

                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_1 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_2 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_3 = new ArrayList<>();

                var buttonI156_1 = new InlineKeyboardButton();

                buttonI156_1.setText("??156_1");
                buttonI156_1.setCallbackData("??156_1_BUTTON");

                var buttonI156_2 = new InlineKeyboardButton();

                buttonI156_2.setText("??156_2");
                buttonI156_2.setCallbackData("??156_2_BUTTON");

                var buttonI308 = new InlineKeyboardButton();

                buttonI308.setText("??308");
                buttonI308.setCallbackData("??308_BUTTON");

                var buttonI310 = new InlineKeyboardButton();

                buttonI310.setText("??310");
                buttonI310.setCallbackData("??310_BUTTON");

                var buttonI329 = new InlineKeyboardButton();

                buttonI329.setText("??329");
                buttonI329.setCallbackData("??329_BUTTON");

                var buttonI333 = new InlineKeyboardButton();

                buttonI333.setText("??333");
                buttonI333.setCallbackData("??333_BUTTON");

                var buttonI335 = new InlineKeyboardButton();

                buttonI335.setText("??335");
                buttonI335.setCallbackData("??335_BUTTON");

                var buttonI431 = new InlineKeyboardButton();

                buttonI431.setText("??431");
                buttonI431.setCallbackData("??431_BUTTON");

                var buttonI526 = new InlineKeyboardButton();

                buttonI526.setText("??526");
                buttonI526.setCallbackData("??526_BUTTON");

                var buttonI532 = new InlineKeyboardButton();

                buttonI532.setText("??532");
                buttonI532.setCallbackData("??532_BUTTON");

                var buttonI534 = new InlineKeyboardButton();

                buttonI534.setText("??534");
                buttonI534.setCallbackData("??534_BUTTON");

                var buttonI535 = new InlineKeyboardButton();

                buttonI535.setText("??535");
                buttonI535.setCallbackData("??535_BUTTON");

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

            } else if (callbackData.equals("??156_1_BUTTON")) {

                if (cameraRepository.existsById("??156_1")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??156_1");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            }
            else if (callbackData.equals("??156_2_BUTTON")) {

                if (cameraRepository.existsById("156_2")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("156_2");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??308_BUTTON")) {

                if (cameraRepository.existsById("??308")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??308");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??310_BUTTON")) {

                if (cameraRepository.existsById("??310")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??310");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??329_BUTTON")) {

                if (cameraRepository.existsById("??329")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??329");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??333_BUTTON")) {

                if (cameraRepository.existsById("??333")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??333");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??335_BUTTON")) {

                if (cameraRepository.existsById("??335")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??335");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??431_BUTTON")) {

                if (cameraRepository.existsById("??431")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??431");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??526_BUTTON")) {

                if (cameraRepository.existsById("??526")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??526");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??532_BUTTON")) {

                if (cameraRepository.existsById("??532")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??532");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??534_BUTTON")) {

                if (cameraRepository.existsById("??534")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??534");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??535_BUTTON")) {

                if (cameraRepository.existsById("??535")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??535");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals(MT_BUTTON)) {

                SendMessage message = new SendMessage();
                message.setChatId(String.valueOf(chatId));
                message.setText("???????????????? ??????????????????");

                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_1 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_2 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_3 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_4 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_5 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_6 = new ArrayList<>();

                var buttonMT139 = new InlineKeyboardButton();

                buttonMT139.setText("????139");
                buttonMT139.setCallbackData("????139_BUTTON");

                var buttonMT143 = new InlineKeyboardButton();

                buttonMT143.setText("????143");
                buttonMT143.setCallbackData("????143_BUTTON");

                var buttonMT149 = new InlineKeyboardButton();

                buttonMT149.setText("????149");
                buttonMT149.setCallbackData("????149_BUTTON");

                var buttonMT151 = new InlineKeyboardButton();

                buttonMT151.setText("????151");
                buttonMT151.setCallbackData("????151_BUTTON");

                var buttonMT204 = new InlineKeyboardButton();

                buttonMT204.setText("????204");
                buttonMT204.setCallbackData("????204_BUTTON");

                var buttonMT213 = new InlineKeyboardButton();

                buttonMT213.setText("????213");
                buttonMT213.setCallbackData("????213_BUTTON");

                var buttonMT311 = new InlineKeyboardButton();

                buttonMT311.setText("????311");
                buttonMT311.setCallbackData("????311_BUTTON");

                var buttonMT312 = new InlineKeyboardButton();

                buttonMT312.setText("????312");
                buttonMT312.setCallbackData("????312_BUTTON");

                var buttonMT314_1 = new InlineKeyboardButton();

                buttonMT314_1.setText("????314_1");
                buttonMT314_1.setCallbackData("????314_1_BUTTON");

                var buttonMT314_2 = new InlineKeyboardButton();

                buttonMT314_2.setText("????314_2");
                buttonMT314_2.setCallbackData("????314_2_BUTTON");

                var buttonMT320 = new InlineKeyboardButton();

                buttonMT320.setText("????320");
                buttonMT320.setCallbackData("????320_BUTTON");

                var buttonMT404 = new InlineKeyboardButton();

                buttonMT404.setText("????404");
                buttonMT404.setCallbackData("????404_BUTTON");

                var buttonMT405 = new InlineKeyboardButton();

                buttonMT405.setText("????405");
                buttonMT405.setCallbackData("????405_BUTTON");

                var buttonMT406 = new InlineKeyboardButton();

                buttonMT406.setText("????406");
                buttonMT406.setCallbackData("????406_BUTTON");

                var buttonMT407 = new InlineKeyboardButton();

                buttonMT407.setText("????407");
                buttonMT407.setCallbackData("????407_BUTTON");

                var buttonMT408 = new InlineKeyboardButton();

                buttonMT408.setText("????408");
                buttonMT408.setCallbackData("????408_BUTTON");

                var buttonMT409 = new InlineKeyboardButton();

                buttonMT409.setText("????409");
                buttonMT409.setCallbackData("????409_BUTTON");

                var buttonMT412 = new InlineKeyboardButton();

                buttonMT412.setText("????412");
                buttonMT412.setCallbackData("????412_BUTTON");

                var buttonMT413 = new InlineKeyboardButton();

                buttonMT413.setText("????413");
                buttonMT413.setCallbackData("????413_BUTTON");

                var buttonMT414 = new InlineKeyboardButton();

                buttonMT414.setText("????414");
                buttonMT414.setCallbackData("????414_BUTTON");

                var buttonMT415 = new InlineKeyboardButton();

                buttonMT415.setText("????415");
                buttonMT415.setCallbackData("????415_BUTTON");

                var buttonMT421 = new InlineKeyboardButton();

                buttonMT421.setText("????421");
                buttonMT421.setCallbackData("????421_BUTTON");

                var buttonMT424 = new InlineKeyboardButton();

                buttonMT424.setText("????424");
                buttonMT424.setCallbackData("????424_BUTTON");

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

            } else if (callbackData.equals("????139_BUTTON")) {

                if (cameraRepository.existsById("????139")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????139");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????143_BUTTON")) {

                if (cameraRepository.existsById("????143")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????143");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????149_BUTTON")) {

                if (cameraRepository.existsById("????149")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????149");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????151_BUTTON")) {

                if (cameraRepository.existsById("????151")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????151");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????204_BUTTON")) {

                if (cameraRepository.existsById("????204")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????204");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????213_BUTTON")) {

                if (cameraRepository.existsById("????213")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????213");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????311_BUTTON")) {

                if (cameraRepository.existsById("????311")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????311");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????312_BUTTON")) {

                if (cameraRepository.existsById("????312")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????312");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????314_1_BUTTON")) {

                if (cameraRepository.existsById("????314_1")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????314_1");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????314_2_BUTTON")) {

                if (cameraRepository.existsById("????314_2")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????314_2");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????320_BUTTON")) {

                if (cameraRepository.existsById("????320")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????320");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????404_BUTTON")) {

                if (cameraRepository.existsById("????404")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????404");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????405_BUTTON")) {

                if (cameraRepository.existsById("????405")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????405");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????406_BUTTON")) {

                if (cameraRepository.existsById("????406")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????406");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????407_BUTTON")) {

                if (cameraRepository.existsById("????407")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????407");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????408_BUTTON")) {

                if (cameraRepository.existsById("????408")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????408");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????409_BUTTON")) {

                if (cameraRepository.existsById("????409")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????409");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????412_BUTTON")) {

                if (cameraRepository.existsById("????412")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????412");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????413_BUTTON")) {

                if (cameraRepository.existsById("????413")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????413");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????414_BUTTON")) {

                if (cameraRepository.existsById("????414")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????414");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????415_BUTTON")) {

                if (cameraRepository.existsById("????415")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????415");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????421_BUTTON")) {

                if (cameraRepository.existsById("????421")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????421");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????424_BUTTON")) {

                if (cameraRepository.existsById("????424")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????424");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals(H_BUTTON)) {

                SendMessage message = new SendMessage();
                message.setChatId(String.valueOf(chatId));
                message.setText("???????????????? ??????????????????");

                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_1 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_2 = new ArrayList<>();

                var buttonH242 = new InlineKeyboardButton();

                buttonH242.setText("??242");
                buttonH242.setCallbackData("??242_BUTTON");

                var buttonH306 = new InlineKeyboardButton();

                buttonH306.setText("??306");
                buttonH306.setCallbackData("??306_BUTTON");

                var buttonH402 = new InlineKeyboardButton();

                buttonH402.setText("??402");
                buttonH402.setCallbackData("??402_BUTTON");

                var buttonH405 = new InlineKeyboardButton();

                buttonH405.setText("??405");
                buttonH405.setCallbackData("??405_BUTTON");

                var buttonH407 = new InlineKeyboardButton();

                buttonH407.setText("??407");
                buttonH407.setCallbackData("??407_BUTTON");

                var buttonH500 = new InlineKeyboardButton();

                buttonH500.setText("??500");
                buttonH500.setCallbackData("??500_BUTTON");

                rowInline_1.add(buttonH242);
                rowInline_1.add(buttonH306);
                rowInline_1.add(buttonH402);
                rowInline_1.add(buttonH405);
                rowInline_2.add(buttonH407);
                rowInline_2.add(buttonH500);

                rowsInline.add(rowInline_1);
                rowsInline.add(rowInline_2);

                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);

                executeMessage(message);

            } else if (callbackData.equals("??242_BUTTON")) {

                if (cameraRepository.existsById("??242")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??242");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            }
            else if (callbackData.equals("??306_BUTTON")) {

                if (cameraRepository.existsById("??306")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??306");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??402_BUTTON")) {

                if (cameraRepository.existsById("??402")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??402");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??405_BUTTON")) {

                if (cameraRepository.existsById("??405")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??405");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??407_BUTTON")) {

                if (cameraRepository.existsById("??407")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??407");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("??500_BUTTON")) {

                if (cameraRepository.existsById("??500")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("??500");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals(FT_BUTTON)) {

                SendMessage message = new SendMessage();
                message.setChatId(String.valueOf(chatId));
                message.setText("???????????????? ??????????????????");

                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_1 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_2 = new ArrayList<>();
                List<InlineKeyboardButton> rowInline_3 = new ArrayList<>();

                var buttonFT201_1 = new InlineKeyboardButton();

                buttonFT201_1.setText("????201_1");
                buttonFT201_1.setCallbackData("????201_1_BUTTON");

                var buttonFT201_2 = new InlineKeyboardButton();

                buttonFT201_2.setText("????201_2");
                buttonFT201_2.setCallbackData("????201_2_BUTTON");

                var buttonFT401 = new InlineKeyboardButton();

                buttonFT401.setText("????401");
                buttonFT401.setCallbackData("????401_BUTTON");

                var buttonFT403 = new InlineKeyboardButton();

                buttonFT403.setText("????403");
                buttonFT403.setCallbackData("????403_BUTTON");

                var buttonFT404 = new InlineKeyboardButton();

                buttonFT404.setText("????404");
                buttonFT404.setCallbackData("????404_BUTTON");

                var buttonFT406 = new InlineKeyboardButton();

                buttonFT406.setText("????406");
                buttonFT406.setCallbackData("????406_BUTTON");

                var buttonFT414 = new InlineKeyboardButton();

                buttonFT414.setText("????414");
                buttonFT414.setCallbackData("????414_BUTTON");

                var buttonFT416 = new InlineKeyboardButton();

                buttonFT416.setText("????416");
                buttonFT416.setCallbackData("????416_BUTTON");

                var buttonFT417 = new InlineKeyboardButton();

                buttonFT417.setText("????417");
                buttonFT417.setCallbackData("????417_BUTTON");

                var buttonFT419 = new InlineKeyboardButton();

                buttonFT419.setText("????419");
                buttonFT419.setCallbackData("????419_BUTTON");

                var buttonFT425 = new InlineKeyboardButton();

                buttonFT425.setText("????425");
                buttonFT425.setCallbackData("????425_BUTTON");

                rowInline_1.add(buttonFT201_1);
                rowInline_1.add(buttonFT201_2);
                rowInline_1.add(buttonFT401);
                rowInline_1.add(buttonFT403);
                rowInline_2.add(buttonFT404);
                rowInline_2.add(buttonFT406);
                rowInline_2.add(buttonFT414);
                rowInline_2.add(buttonFT416);
                rowInline_3.add(buttonFT417);
                rowInline_3.add(buttonFT419);
                rowInline_3.add(buttonFT425);

                rowsInline.add(rowInline_1);
                rowsInline.add(rowInline_2);
                rowsInline.add(rowInline_3);

                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);

                executeMessage(message);

            } else if (callbackData.equals("????201_1_BUTTON")) {

                if (cameraRepository.existsById("????201_1")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????201_1");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            }
            else if (callbackData.equals("????201_2_BUTTON")) {

                if (cameraRepository.existsById("????201_2")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????201_2");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????401_BUTTON")) {

                if (cameraRepository.existsById("????401")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????401");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????403_BUTTON")) {

                if (cameraRepository.existsById("????403")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????403");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????404_BUTTON")) {

                if (cameraRepository.existsById("????404")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????404");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????406_BUTTON")) {

                if (cameraRepository.existsById("????406")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????406");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????414_BUTTON")) {

                if (cameraRepository.existsById("????414")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????414");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????416_BUTTON")) {

                if (cameraRepository.existsById("????416")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????416");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????417_BUTTON")) {

                if (cameraRepository.existsById("????417")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????417");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????419_BUTTON")) {

                if (cameraRepository.existsById("????419")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????419");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

            } else if (callbackData.equals("????425_BUTTON")) {

                if (cameraRepository.existsById("????425")) {

                    SendMessage message = new SendMessage();
                    String text = clearAnswerText("????425");
                    message.setChatId(String.valueOf(chatId));
                    message.setText(text);

                    executeMessage(message);

                }

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
        message.setText("???????????????? ????????????");

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline_1 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline_2 = new ArrayList<>();

        var buttonM = new InlineKeyboardButton();

        buttonM.setText("??");
        buttonM.setCallbackData(M_BUTTON);

        var buttonE = new InlineKeyboardButton();

        buttonE.setText("??");
        buttonE.setCallbackData(E_BUTTON);

        var buttonS = new InlineKeyboardButton();

        buttonS.setText("??");
        buttonS.setCallbackData(S_BUTTON);

        var buttonSP = new InlineKeyboardButton();

        buttonSP.setText("????");
        buttonSP.setCallbackData(SP_BUTTON);

        var buttonI = new InlineKeyboardButton();

        buttonI.setText("??");
        buttonI.setCallbackData(I_BUTTON);

        var buttonMT = new InlineKeyboardButton();

        buttonMT.setText("????");
        buttonMT.setCallbackData(MT_BUTTON);

        var buttonH = new InlineKeyboardButton();

        buttonH.setText("??");
        buttonH.setCallbackData(H_BUTTON);

        var buttonFT = new InlineKeyboardButton();

        buttonFT.setText("????");
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

    private String clearAnswerText(String text) {

        Pattern pattern = Pattern.compile("[A-z]");

        String str = String.valueOf(cameraRepository.findById(text));

        Matcher matcher = pattern.matcher(str);

        return matcher.replaceAll("");

    }
}