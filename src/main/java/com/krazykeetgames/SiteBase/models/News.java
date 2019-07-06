package com.krazykeetgames.SiteBase.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;


@Entity
public class News {

	@Id
	private String id;
	@NotEmpty
	private String title;
	@NotEmpty
	private String body;
	private String image;
	private LocalDate postDate = LocalDate.now();
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}


	public String getId() {
		return id;
	}

	public void setPostDate() {
		this.postDate = LocalDate.now();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public LocalDate getPostDate() {
		return postDate;
	}

	public News(String body, String title, String image) {
		super();
		this.body = body;
		this.title = title;
		this.image = image;
//		this.postDate = LocalDate.now();
	}

	public News() {
		super();
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", body=" + body +"]";
	}
	
}
