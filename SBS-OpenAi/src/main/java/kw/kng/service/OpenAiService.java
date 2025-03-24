package kw.kng.service;

public interface OpenAiService 
{
	public String testChat();
	public String promptChat(String message);
	public String promptChatResponse(String message);

}
