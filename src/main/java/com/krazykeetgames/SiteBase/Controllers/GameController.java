package com.krazykeetgames.SiteBase.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krazykeetgames.SiteBase.Services.GameService;
import com.krazykeetgames.SiteBase.models.Game;
import com.krazykeetgames.SiteBase.repositories.GameRepository;

@RestController
@RequestMapping("/api")
public class GameController {
	
	@Autowired
	private GameService gameService;
	
    @CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value="/game")
	List<Game> all() {
		return gameService.getAllGames();
	}
    
    @CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value="/game/{id}")
	public Game getGame(Game game, @PathVariable String id){
		return gameService.getOneGame(id);
	}
	
	@PostMapping(value="/game")
	public String addNewGame(Game game, Model model){
		return gameService.newGame(game);
	}
	@PutMapping(value="/game/{id}")
	public String updateGame(Game game, @PathVariable String id) {	
		return gameService.updateGame(id, game);
	}
	@DeleteMapping(value="/game/{id}")
	public String delete(Game game, @PathVariable String id) {
		return gameService.deleteGame(id);
	}
}
