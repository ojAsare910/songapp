package com.ojasare.songapp.repository;

import com.ojasare.songapp.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
