package com.movie.Moviebackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.Moviebackend.model.Announcement;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
}
