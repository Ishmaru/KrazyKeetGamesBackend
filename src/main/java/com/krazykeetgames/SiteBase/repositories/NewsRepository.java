package com.krazykeetgames.SiteBase.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.krazykeetgames.SiteBase.models.Game;
import com.krazykeetgames.SiteBase.models.News;

public interface NewsRepository extends CrudRepository<News, Long> {
	List<News> findAll();
	Optional<News> findById(Long id);
}