package kw.kng.controller;

import java.util.Map;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kw.kng.service.OpenAiService;

@RestController
@RequestMapping("/openAi")
public class OpenAiController 
{

	//--------------------------------------------------------------------------------
	private OpenAiService os;
	
	public OpenAiController(OpenAiService os)
	{
		this.os=os;
	}
	//--------------------------------------------------------------------------------
	
	@GetMapping("/default_chat")
	public String chat()
	{
		return os.testChat();
	}
	
	@GetMapping("/prompt_chat")
	public String promptChat(@RequestParam String message)
	{
		return os.promptChat(message);
	}
	
	@GetMapping("/prompt_chat_response")
	public String promptChatResponse(@RequestParam String message)
	{
		return os.promptChatResponse(message);
	}
	
	
	@GetMapping("/celeb")
	public String getCelebDetails(@RequestParam String name)
	{
		return os.celebDetails(name);
	}
	
	@GetMapping("/sports")
	public String getSportsDetail(@RequestParam String name)
	{
		return os.sportsDetails(name);
	}
	
	
}
