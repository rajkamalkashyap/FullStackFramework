package com.fonepaisa.GenEPG.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="game_details")
public class game_details implements Serializable{

	private String title;
	private String url;
	private String platform;
	private String score;
	private String genre;
	private String editors_choice;
	private String release_year;
	@Id
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Id
	@Column(name = "url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name = "platform")
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	@Column(name = "score")
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	@Column(name = "genre")
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	@Column(name = "editors_choice")
	public String getEditors_choice() {
		return editors_choice;
	}
	public void setEditors_choice(String editors_choice) {
		this.editors_choice = editors_choice;
	}
	@Column(name = "release_year")
	public String getRelease_year() {
		return release_year;
	}
	public void setRelease_year(String release_year) {
		this.release_year = release_year;
	}
	@Override
	public String toString() {
		return "game_details [title=" + title + ", url=" + url + ", platform=" + platform + ", score=" + score
				+ ", genre=" + genre + ", editors_choice=" + editors_choice + ", release_year=" + release_year + "]";
	}


}
