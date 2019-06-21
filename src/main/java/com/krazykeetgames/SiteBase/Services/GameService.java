package com.krazykeetgames.SiteBase.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krazykeetgames.SiteBase.models.Game;
import com.krazykeetgames.SiteBase.repositories.GameRepository;

@Service
public class GameService {
@Autowired
	private GameRepository gameRepository;

	public String getAllGames(List<Game> games) {
		String returnType = "";
		return returnType;
	}
}
