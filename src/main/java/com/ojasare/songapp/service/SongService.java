package com.ojasare.songapp.service;


import com.ojasare.songapp.model.Song;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface SongService {
    Song saveSong(Song song);

    @Cacheable(value = "songs", key = "#id")
    Song getSongById(Long id);

    List<Song> getAllSongs();
}
