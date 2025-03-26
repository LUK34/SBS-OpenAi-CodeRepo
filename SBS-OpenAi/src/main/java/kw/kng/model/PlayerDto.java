package kw.kng.model;

import java.util.List;

import lombok.Data;

@Data
public class PlayerDto 
{
	private String playerName;
	private List<String> achievements;

}
