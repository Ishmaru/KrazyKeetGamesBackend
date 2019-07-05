package com.krazykeetgames.SiteBase.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.krazykeetgames.SiteBase.models.Game;
//jdbc:h2:file:~/krazykeetgames
public interface GameRepository extends MongoRepository<Game, Long> {
	List<Game>findAll();
	Optional<Game> findById(Long id);
}
