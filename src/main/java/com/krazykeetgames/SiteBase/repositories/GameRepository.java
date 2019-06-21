package com.krazykeetgames.SiteBase.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.krazykeetgames.SiteBase.models.Game;

public interface GameRepository extends CrudRepository<Game, Long> {
	List<Game>findAll();
	Optional<Game> findById(Long id);
}