package kw.kng.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenAiServiceImpl implements OpenAiService 
{
	
	//-------------------------------------------------------------------------------
	private ChatClient chatClient;
	
	//Constructor Injection
	public OpenAiServiceImpl(ChatClient.Builder chatClient)
	{
		this.chatClient=chatClient.build();
	}
	//-------------------------------------------------------------------------------
	
	@Value("${default.text}")
	private String defaultText;
	//-------------------------------------------------------------------------------
	
	@Override
	public String testChat()
	{
		return chatClient.prompt()
				.user(defaultText)
				.call()
				.chatResponse()
				.getResult()
				.getOutput()
				.getText();
	}
	
	@Override
	public String promptChat(String message)
	{
		return chatClient.prompt(message)
				.call()
				.content();
	}

	@Override
	public String promptChatResponse(String message)
	{
		return chatClient.prompt(message)
				.call()
				.chatResponse()
				.getResult()
				.getOutput()
				.getText();
	}

}
