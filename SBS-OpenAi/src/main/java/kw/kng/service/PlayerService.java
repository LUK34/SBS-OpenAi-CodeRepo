package kw.kng.service;

import java.util.List;

import kw.kng.model.PlayerDto;

public interface PlayerService 
{
	public List<PlayerDto> getPlayerAchievement(String name);

}
