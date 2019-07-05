package com.krazykeetgames.SiteBase.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	@GetMapping(value="/news")
	public List<News> getAllNews(){
		return newsService.getAllNews();
	}
	
	@PostMapping(value="/game/{gameId}/news")
	public String addNews(News news, @PathVariable Long gameId) {
		return newsService.addNews(news, gameId);
	}
	
	@PutMapping(value="/news/{id}")
	public String updateNews(News news, @PathVariable Long id) {
		return newsService.editNews(news, id);
	}
	
	@DeleteMapping(value="/news/{id}")
	public String deleteNews(@PathVariable Long id) {
		return newsService.deleteNews(id);
	}
	
}
