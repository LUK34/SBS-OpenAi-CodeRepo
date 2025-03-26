package kw.kng.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kw.kng.model.PlayerDto;
import kw.kng.service.PlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController
{
	private PlayerService ps;
	
	public PlayerController(PlayerService ps)
	{
		this.ps=ps;
	}


	@GetMapping("/details")
	public List<PlayerDto> getPlayerDetails(@RequestParam String name)
	{
		return ps.getPlayerAchievement(name);
	}
	
	
}
