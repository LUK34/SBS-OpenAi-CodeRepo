package kw.kng.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kw.kng.service.ImageService;

@RestController
@RequestMapping("/image")
public class ImageController 
{
	private ImageService imgs;
	
	public ImageController(ImageService imgs)
	{
		this.imgs=imgs;
	}
	
	@GetMapping("/img-to-text")
	public String describeImage()
	{
		return imgs.describeImage();
	}

	@GetMapping("/{text}")
	public String generateImage(@PathVariable String text)
	{
		return imgs.imageGenerate(text);
	}
	
}
