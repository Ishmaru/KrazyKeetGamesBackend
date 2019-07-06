package com.krazykeetgames.SiteBase.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.krazykeetgames.SiteBase.models.Game;
import com.krazykeetgames.SiteBase.repositories.GameRepository;

@Service
public class GameService {
	@Autowired
	private GameRepository gameRepository;

	public List<Game> getAllGames() {
		return gameRepository.findAll();
	}
	
	public Game getOneGame(String id) {
		try {
			return gameRepository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
	}
	
	public String newGame(Game game) {
		String returnString = "";
		try {
			gameRepository.save(game);
			returnString = game.getName() + " Added"; 
		}catch(Exception e){
			returnString = "Game failed to post: \n" + e.getMessage();
		}
		return returnString;
	}
	
	public String deleteGame(String id) {
		String returnString;
		try {
			gameRepository.deleteById(id);
			returnString = "Game deleted";
		}catch(Exception e) {
			returnString = "Cannot delete: \n " + e.getMessage();
		}
		return returnString;
	}
	
	private static String validateUpdate (String incoming, String current) {
		return incoming != null ? incoming : current;
	}
	
	public String updateGame(String id, Game game) {
		String returnString;
		try {
			Game update = gameRepository.findById(id).get();
			
			String background = validateUpdate(game.getBackground(), update.getBackground());
			String thumb = validateUpdate(game.getThumb(),update.getThumb());
			String name = validateUpdate(game.getName(),update.getName());
			String description = validateUpdate(game.getDescription(),update.getDescription());
			String version = validateUpdate(game.getVersion(),update.getVersion());
			String details = validateUpdate(game.getDetails(),update.getDetails());
			String youtube = validateUpdate(game.getYoutube(),update.getYoutube());
			String download = validateUpdate(game.getDownload(),update.getDownload());
			String github = validateUpdate(game.getGitHub(),update.getGitHub());
			String requires = validateUpdate(game.getRequires(),update.getRequires());
//			String news = validateUpdate(game.getNews(),update.getNews());

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
//			update.setNews(news);
			update.setMedia(media);
			
			try {
				gameRepository.save(update);
				returnString = update.getName() + " Updated";
			}catch(Exception e) {
				returnString = "Failed to update:\n" + e.getMessage();
			}
		}catch(Exception e) {
			returnString = "Not present: \n"  + e.getMessage();
		}
		return returnString;
	}
}
