package com.krazykeetgames.SiteBase.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.krazykeetgames.SiteBase.models.News;

public interface NewsRepository extends MongoRepository<News, String> {
	List<News> findAll();
	Optional<News> findById(String id);
}
