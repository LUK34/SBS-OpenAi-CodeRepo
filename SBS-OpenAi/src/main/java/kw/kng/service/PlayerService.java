package kw.kng.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import kw.kng.model.AchievementDto;
import kw.kng.model.PlayerDto;

public interface PlayerService 
{
	public List<PlayerDto> getPlayerAchievement(String name);
	public List<AchievementDto> getAchievements(@RequestParam String name);

}
