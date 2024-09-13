package com.movie.Moviebackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.movie.Moviebackend.model.Announcement;
import com.movie.Moviebackend.repository.AnnouncementRepository;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/announcements")
public class AnnouncementController {

  @Autowired
  private AnnouncementRepository announcementRepository;

  @PostMapping
  public Announcement createAnnouncement(@RequestBody Announcement announcement) {
    return announcementRepository.save(announcement);
  }

  @GetMapping
  public List<Announcement> getAnnouncements() {
    return announcementRepository.findAll();
  }
}
