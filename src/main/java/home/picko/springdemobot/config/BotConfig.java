package home.picko.springdemobot.config;

import com.google.common.base.Utf8;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Configuration
@Data
@PropertySource("application.properties")
public class BotConfig {

    @Value("${bot.name}")
    String botName;

    String token = "";

    public String getToken() {
        try {
            token = Files.readString(Path.of("D:/Java/Projects/Spring-boot-tg-bot/src/main/resources/TOKEN.txt"),
                    StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            log.error("Error with TOKEN: " + e.getMessage());
        }
        return token;
    }
}
