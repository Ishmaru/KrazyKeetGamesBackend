package com.krazykeetgames.SiteBase.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krazykeetgames.SiteBase.models.Game;
import com.krazykeetgames.SiteBase.repositories.GameRepository;

@RestController
public class GameController {
	
	
	@Autowired
	private GameRepository gameRepository;
	
	@GetMapping(value="/game")
	List<Game> all() {
		return gameRepository.findAll();
	}
	
	@GetMapping(value="/game/{id}")
	public Game getGame(Game game, Model model, @PathVariable Long id){
		try {
			return gameRepository.findById(id).get();
		}catch(Exception e) {
			return null;
		}
	}
	
	@PostMapping(value="/game")
	public ResponseEntity<String> addNewGame(Game game, Model model){
		try {
			gameRepository.save(game);
			return new ResponseEntity<>(game.getName() + " Added", HttpStatus.OK);  
		}catch(Exception e){
			return new ResponseEntity<>("Game Failed to Post", HttpStatus.BAD_REQUEST); 
		}
	}
	@PutMapping(value="/game/{id}")
	public String updateGame(Game game, Model model, @PathVariable Long id) {
		String returnString = "";
		try {
			Game update = gameRepository.findById(id).get();
			String background = game.getBackground() != null ? game.getBackground() : update.getBackground();
			String thumb = game.getThumb() != null ? game.getThumb() : update.getThumb();
			String name = game.getName() != null ? game.getName() : update.getName();
			String description = game.getDescription() != null ? game.getDescription() : update.getDescription();
			String version = game.getVersion() != null ? game.getVersion() : update.getVersion();
			String details = game.getDetails() != null ? game.getDetails() : update.getDetails();
			String youtube = game.getYoutube() != null ? game.getYoutube() : update.getYoutube();
			String download = game.getDownload() != null ? game.getDownload() : update.getDownload();
			String github = game.getGitHub() != null ? game.getGitHub() : update.getGitHub();
			String requires = game.getRequires() != null ? game.getRequires() : update.getRequires();
			String news = game.getNews() != null ? game.getNews() : update.getNews();

			String[] media;
			if(game.getMedia() != null && game.getMedia().equals(new String[] {"","",""})) {
				System.out.println(game.getMedia()[0]);
				System.out.println(game.getMedia()[1]);
				System.out.println(game.getMedia()[2]);
				media = update.getMedia();
			}else if(game.getMedia().length == 3) {
				media = game.getMedia();
			}else if(game.getMedia().length == 2){
				media = new String[] {game.getMedia()[0],game.getMedia()[1],update.getMedia()[2]};
			}else if(game.getMedia().length == 1){
				media = new String[] {update.getMedia()[0],update.getMedia()[1],game.getMedia()[0]};
			}else {
				media = update.getMedia();
			}
			
			update.setThumb(thumb);
			update.setBackground(background);
			update.setName(name);
			update.setVersion(version);
			update.setDescription(description);
			update.setDetails(details);
			update.setYoutube(youtube);
			update.setDownload(download);
			update.setGitHub(github);
			update.setRequires(requires);
			update.setNews(news);
			update.setMedia(media);
			
			try {
				gameRepository.save(update);
				returnString = update.getName() + " Updated";
			}catch(Exception e) {
				returnString = "Failed to Update";
			}
		}catch(Exception e) {
			returnString = "Not present";
		}
		return returnString;
	}
	@DeleteMapping(value="/game/{id}")
	public String delete(Game game, Model model, @PathVariable Long id) {
		String returnString;
		try {
			gameRepository.deleteById(id);
			returnString = "Game deleted";
		}catch(Exception e) {
			returnString = "Cannot Delete";
		}
		return returnString;
	}
}
