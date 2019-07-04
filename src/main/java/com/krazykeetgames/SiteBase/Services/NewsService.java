package com.krazykeetgames.SiteBase.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krazykeetgames.SiteBase.models.Game;
import com.krazykeetgames.SiteBase.models.News;
import com.krazykeetgames.SiteBase.repositories.GameRepository;
import com.krazykeetgames.SiteBase.repositories.NewsRepository;

@Service
public class NewsService {

	@Autowired
	private NewsRepository newsRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
	public List<News> getAllNews() {
		return newsRepository.findAll();
	}
	
	public News getOneNews(Long id, Long gameId) {
		try {
			Game game = gameRepository.findById(gameId).get();
			return newsRepository.findById(id).get();
		}catch(Exception e){
			return null;
		}
	}
	
	public List<News> getAllNewsFromGame(Long gameId) {
		try {
			Game game = gameRepository.findById(gameId).get();
			return newsRepository.findAllByGame(game);
		} catch(Exception e) {
			return null;
		}
	}
	
	public String addNews(News news, Long gameId) {
		String returnString = "";
		try {
			Game game = gameRepository.findById(gameId).get();
			news.setGame(game);
		}catch(Exception e) {
			returnString = "Cannot find game";
			return returnString;
		}
		try {
			newsRepository.save(news);
			returnString = "Added" + news.getBody();
		}catch(Exception e) {
			returnString = "Failed To post";
		}
		return returnString;
	}
	
	private static String validateUpdate (String incoming, String current) {
		return incoming != null ? incoming : current;
	}
	
	public String updateNews(News news, Long id) {
		String returnString = "";
		try {
			News update = newsRepository.findById(id).get();
			String tempBody = validateUpdate(news.getBody(), update.getBody());
			update.setBody(tempBody);
			newsRepository.save(update);
			returnString = "News article " + update.getId() + " updated";
		}catch(Exception e) {
			returnString = "Failed to update";
		}
		return returnString;
	}
	
	public String updateNews(News news, Long id, Long gameId) {
		String returnString = "";
		News update;
		Game game;
		try {
			update = newsRepository.findById(id).get();
			game = gameRepository.findById(gameId).get();
		}catch(Exception e) {
			returnString = "Cannot find resource";
			return returnString;
		}
		try {
			String tempBody = validateUpdate(news.getBody(), update.getBody());
			update.setBody(tempBody);
			update.setGame(game);
			newsRepository.save(update);
			returnString = game.getName() + " news article " + update.getId() + " updated";
		}catch(Exception e) {
			returnString = "Failed to Update";
		}
		return returnString;
	}

	
}
