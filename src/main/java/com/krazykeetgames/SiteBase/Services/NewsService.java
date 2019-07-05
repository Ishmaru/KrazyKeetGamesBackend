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
	
	public String addNews(News news, Long gameId) {
		String returnString = "";	
		Game game;
		try {
			game = gameRepository.findById(gameId).get();
			System.out.println(game);
			List<News> currNews = game.getNews();
			currNews.add(news);
			game.setNews(currNews);
		}catch(Exception e) {
			returnString = "Cannot find game";
			return returnString;
		}
		try {

			newsRepository.save(news);
			gameRepository.save(game);
			returnString = "Added" + news.getBody();
		}catch(Exception e) {
			returnString = "Failed To post";
		}
		return returnString;
	}
	
	private static String validateUpdate (String incoming, String current) {
		return incoming != null ? incoming : current;
	}
	
	public String editNews(News news, Long id) {
		String returnString = "";
		try {
			News update = newsRepository.findById(id).get();
			System.out.println(update);
			String tempBody = validateUpdate(news.getBody(), update.getBody());
			String tempTitle = validateUpdate(news.getTitle(), update.getTitle());
			String tempImage = validateUpdate(news.getImage(), update.getImage());
			
			
			update.setPostDate();
			update.setBody(tempBody);
			update.setTitle(tempTitle);
			update.setImage(tempImage);
			
			
			newsRepository.save(update);
			returnString = "News article " + update.getId() + " updated";
		}catch(Exception e) {
			returnString = "Failed to update";
		}
		return returnString;
	}
	
	public String deleteNews(Long id) {
		String returnString = "";
		try {
			newsRepository.deleteById(id);
			returnString = "News id:" + id + " deleted";
		}catch(Exception e) {
			returnString = "Failed to delete";
		}
		return returnString;
	}
	
	
}
