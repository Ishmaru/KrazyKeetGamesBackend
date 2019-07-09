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
	
	public List<News> getAllNews(){
		return newsRepository.findAll();
	}
	
	public String addNews(News news, String gameId) {
		String returnString = "";	
		Game game;
		try {
			game = gameRepository.findById(gameId).get();
			System.out.println(game);
			List<News> currNews = game.getNews();
			currNews.add(news);
			game.setNews(currNews);
		}catch(Exception e) {
			returnString = "Cannot find game:\n"  + e.getMessage();
			return returnString;
		}
		try {

			newsRepository.save(news);
			gameRepository.save(game);
			returnString = "Added" + news.getBody();
		}catch(Exception e) {
			returnString = "Failed To post:\n" + e.getMessage();
		}
		return returnString;
	}
	
	private static String validateUpdate (String incoming, String current) {
		return incoming != null ? incoming : current;
	}
	
	public String editNews(News news, String id, String gameId) {
		String returnString = "";
		try {
			News update = newsRepository.findById(id).get();
			System.out.println(update);
			Game updateGame = gameRepository.findById(gameId).get();
			System.out.println(updateGame);
			String tempBody = validateUpdate(news.getBody(), update.getBody());
			String tempTitle = validateUpdate(news.getTitle(), update.getTitle());
			String tempImage = validateUpdate(news.getImage(), update.getImage());
			
			
			update.setPostDate();
			update.setBody(tempBody);
			update.setTitle(tempTitle);
			update.setImage(tempImage);
			
			
			newsRepository.save(update);
			
			//Update GameArray
			int index = updateGame.getNews().indexOf(update);
			updateGame.getNews().set(index, update);
			System.out.println(updateGame);
			gameRepository.save(updateGame);
			
			returnString = "News article " + update.getId() + " updated";
		}catch(Exception e) {
			returnString = "Failed to update:\n" + e.getMessage();
		}
		return returnString;
	}
	
	public String deleteNews(String id, String gameId) {
		String returnString = "";
		try {
//			Game updateGame = gameRepository.findById(gameId).get();
		}catch(Exception e) {
			
		}
		try {
			newsRepository.deleteById(id);
			returnString = "News id:" + id + " deleted";
		}catch(Exception e) {
			returnString = "Failed to delete:\n" + e.getMessage();
		}
		return returnString;
	}
	
	
}
