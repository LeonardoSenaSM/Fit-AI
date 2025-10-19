package dev.leonardosena.fitai.FitAI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${chatgpt.api.url:https://api.openai.com/v1}")
    private String chatGptUrl;

    @Bean
    public WebClient webClient( WebClient.Builder builder){
        return builder.baseUrl(chatGptUrl).build();
    }
}
