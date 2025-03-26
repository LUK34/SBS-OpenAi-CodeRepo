package kw.kng.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.MimeTypeUtils;

@Service
public class ImageServiceImpl implements ImageService 
{
	//-------------------------------------------------------------------------------
		private final ChatModel chatModel;
		private final ImageModel imageModel;
		
		//Constructor Injection
		public ImageServiceImpl(ChatModel chatModel, ImageModel imageModel)
		{
			this.chatModel=chatModel;
			this.imageModel=imageModel;
		}
	//-------------------------------------------------------------------------------
		
		@Value("classpath:/prompts/describe-image.st")
		private Resource describeImage;
		
		@Value("${img.n}")
		private Integer imgN;

		@Value("${img.width}")
		private Integer imgWidth;
		
		@Value("${img.height}")
		private Integer imgHeight;
		
		@Value("${img.quality}")
		private String imgQuality;
		
		
	//-------------------------------------------------------------------------------
		
	@Override
	public String describeImage()
	{
		String messageTemplate;
		  
		try 
		{
			try (Reader reader = new InputStreamReader(describeImage.getInputStream(), StandardCharsets.UTF_8))
	        {
	            messageTemplate = FileCopyUtils.copyToString(reader);
	        }
			
			return ChatClient.create(chatModel)
			.prompt()
			.user(useSpec -> useSpec.text(messageTemplate).media(MimeTypeUtils.IMAGE_JPEG,new ClassPathResource("images/gohan.jpg")))
			.call()
			.content();
			
		}catch (IOException e) 
		{
			throw new RuntimeException("Failed to load prompt template", e);
		}
		
	}

	@Override
	public String imageGenerate(String text)
	{
		
		ImageResponse imageResponse= imageModel.call(new ImagePrompt(text,
					OpenAiImageOptions
					.builder()
					.withN(imgN)
                    .withWidth(imgWidth)
                    .withHeight(imgHeight)
                    .withQuality(imgQuality)
                    .build()));
		 return imageResponse.getResult().getOutput().getUrl();
		
	}
		
		
		
}


/*

.withN(imgN)	
-> Number of images to generate (e.g., 1, 2)

.withWidth(imgWidth)	
-> Width of the generated image (e.g., 512, 1024)

.withHeight(imgHeight)	
-> Height of the generated image

.withQuality(imgQuality)
-> Quality setting (hd or standard) depending on the model capabilities

 */





