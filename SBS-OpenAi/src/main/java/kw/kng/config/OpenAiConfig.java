package kw.kng.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiConfig 
{
	//-------------------------------------------------------------------------------
		@Value("${config.text}")
		private String configText;
	//-------------------------------------------------------------------------------
		
		
	@Bean(name = "chatClientBuilderPrime")
	public ChatClient chatClientBuilder(ChatClient.Builder chatClientBuilderPrime)
	{
		return chatClientBuilderPrime.defaultSystem(configText).build();
	}
}
