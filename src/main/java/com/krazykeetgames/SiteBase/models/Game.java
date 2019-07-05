package com.krazykeetgames.SiteBase.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
public class Game {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "thumb is required")
	private String thumb;
	@NotEmpty(message = "background is required")
	private String background;
	@NotEmpty(message = "name is required")
	private String name;
	private String version;
	@NotEmpty(message = "descripton is required")
	@Length(max = 500, message = "Less than 500 chars")
	private String description;
	private String details;
	private String[] media = new String[] {"","",""};
	private String youtube;
	private String download;
	private String gitHub;
	private String requires;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<News> news = new ArrayList<>();
	
	
	public Game() {
		super();
	}
	
	public Game(String thumb, String background, String name, String version, String description, String details, String[] media,
			String download, String gitHub, String requires, String news, String youtube) {
		super();
		
		this.thumb = thumb;
		this.background = background;
		this.name = name;
		this.version = version;
		this.description = description;
		this.details = details;
		this.media = media;
		this.youtube = youtube;
		this.download = download;
		this.gitHub = gitHub;
		this.requires = requires;
	}



	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String[] getMedia() {
		return media;
	}
	public void setMedia(String[] media) {
		this.media = media;
	}
	public void setMedia(String media) {
		for(byte i = 0; i < this.media.length; i++) {
			if(this.media[i].length() > 0) {
				this.media[i] = media;
				break;
			}
		}
	}
	public void setMedia(String media0, String media1, String media2) {
		if(media0.length() >1) {
			this.media[0]= media0;
		}
		if(media1.length() >1) {
			this.media[0]= media1;
		}
		if(media2.length() >1) {
			this.media[0]= media2;
		}
	}
	public void clearMedia() {
		this.media = new String[] {"","",""};
	}
	public void clearMedia(byte index) {
		this.media[index] = "";
	}
	public String getDownload() {
		return download;
	}
	public void setDownload(String download) {
		this.download = download;
	}
	public String getGitHub() {
		return gitHub;
	}
	public void setGitHub(String gitHub) {
		this.gitHub = gitHub;
	}
	public String getRequires() {
		return requires;
	}
	public void setRequires(String requires) {
		this.requires = requires;
	}

	
	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

	public Long getId() {
		return id;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getYoutube() {
		return youtube;
	}
	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", thumb=" + thumb + ", background=" + background + ", name=" + name + ", version="
				+ version + ", description=" + description + ", details=" + details + ", media="
				+ Arrays.toString(media) + ", youtube=" + youtube + ", download=" + download + ", gitHub=" + gitHub
				+ ", requires=" + requires + ", news=" + news + "]";
	}
	


}
