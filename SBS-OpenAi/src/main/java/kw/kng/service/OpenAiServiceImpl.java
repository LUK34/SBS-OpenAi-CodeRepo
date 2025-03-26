package kw.kng.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

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
	
	@Value("classpath:/prompts/celeb-details.st")
	private Resource celebPrompt;
	
	@Value("classpath:/prompts/sport-details.st")
	private Resource sportPrompt;
	
	@Value("classpath:/prompts/system-message.st")
	private Resource systemMessage;
	
	
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

	@Override
	public String celebDetails(String celeb)
	{
	
		PromptTemplate template= new PromptTemplate(celebPrompt);
		
		Prompt prompt=template.create(Map.of("celeb",celeb));
		
		return chatClient.prompt(prompt)
				.call()
				.chatResponse()
				.getResult()
				.getOutput()
				.getText();
	}

	@Override
	public String sportsDetails(String sportName) 
	{			
			try 
			{
				// Read the contents of sport-details.st file
		        String messageTemplate;
		        
		        try (Reader reader = new InputStreamReader(sportPrompt.getInputStream(), StandardCharsets.UTF_8))
		        {
		            messageTemplate = FileCopyUtils.copyToString(reader);
		        }
	
		        // Format the template with sportName
		        String formattedMessage = messageTemplate.replace("{sportName}", sportName);
	
		        // Construct the user and system messages
		        UserMessage userMessage = new UserMessage(formattedMessage);
			
		        SystemMessage systemMessage1 = new SystemMessage(systemMessage);
			
		        Prompt prompt=new Prompt(List.of(userMessage,systemMessage1));
			
				return chatClient.prompt(prompt)
						.call()
						.chatResponse()
						.getResult()
						.getOutput()
						.getText();
			}catch (IOException e) 
			{
				throw new RuntimeException("Failed to load prompt template", e);
			}
	}
	
}
