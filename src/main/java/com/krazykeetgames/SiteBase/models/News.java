package com.krazykeetgames.SiteBase.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
public class News {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String body;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "game_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Game game;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public long getId() {
		return id;
	}

	public News(String body, Game game) {
		super();
		this.body = body;
		this.game = game;
	}

	public News() {
		super();
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", body=" + body + ", game=" + game + "]";
	}
	
}
