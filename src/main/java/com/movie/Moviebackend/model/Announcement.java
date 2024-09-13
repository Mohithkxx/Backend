package com.movie.Moviebackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Announcement {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String message;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public Announcement(Long id, String title, String message) {
	super();
	this.id = id;
	this.title = title;
	this.message = message;
}
  public Announcement()
  {
	  
  }
}
