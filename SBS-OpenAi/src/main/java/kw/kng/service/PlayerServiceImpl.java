package kw.kng.service;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import kw.kng.model.PlayerDto;

@Service
public class PlayerServiceImpl implements PlayerService
{
	//-------------------------------------------------------------------------------
		private ChatClient chatClient;
		
		//Constructor Injection
		public PlayerServiceImpl(ChatClient.Builder chatClient)
		{
			this.chatClient=chatClient.build();
		}
	//-------------------------------------------------------------------------------
			
		@Value("classpath:/prompts/sports-person.st")
		private Resource sprsPersonPrompt;
		
	//-------------------------------------------------------------------------------	

		@Override
		public List<PlayerDto> getPlayerAchievement(String name)
		{
			BeanOutputConverter<List<PlayerDto>> converter=new BeanOutputConverter<>(new ParameterizedTypeReference<List<PlayerDto>>() {
				
			});
			
			PromptTemplate template= new PromptTemplate(sprsPersonPrompt);
			
			Prompt prompt = template.create(Map.of("sports",name,"format",converter.getFormat()));
			
			Generation result= chatClient
				.prompt(prompt)
				.call()
				.chatResponse()
				.getResult();
			
			return converter.convert(result.getOutput().getText());
		}
		
		
}
