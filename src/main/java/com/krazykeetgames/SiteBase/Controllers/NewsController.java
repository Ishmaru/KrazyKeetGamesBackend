package com.krazykeetgames.SiteBase.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krazykeetgames.SiteBase.Services.NewsService;
import com.krazykeetgames.SiteBase.models.News;

@RestController
@RequestMapping("/api")
public class NewsController {
	
	@Autowired
	private NewsService newsService;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value="/news")
	public List<News> getAllNews() {
		return newsService.getAllNews();
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value="/game/{gameId}/news/{id}")
	public News getANews(News news, @PathVariable Long id, @PathVariable Long gameId) {
		return newsService.getOneNews(id, gameId);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value="/game/{gameId}/news")
	public List<News> getAllNewsFromGame(@PathVariable Long gameId) {
		return newsService.getAllNewsFromGame(gameId);
	}
	
	@PostMapping(value="/game/{gameId}/news")
	public String newNews(News news, @PathVariable Long gameId) {
		return newsService.addNews(news, gameId);
	}
	
	@PutMapping(value="/game/{gameId}/news/{id}")
	public String updateNews(News news, @PathVariable Long gameId, @PathVariable Long id) {
		return newsService.updateNews(news, id, gameId);
	}
	
	@PutMapping(value="/news/{id}")
	public String updateNews(News news, @PathVariable Long id) {
		return newsService.updateNews(news, id);
	}
}
