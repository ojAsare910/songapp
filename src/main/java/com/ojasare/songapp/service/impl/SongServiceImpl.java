package com.ojasare.songapp.service.impl;


import com.ojasare.songapp.exception.NotFoundException;
import com.ojasare.songapp.model.Song;
import com.ojasare.songapp.repository.SongRepository;
import com.ojasare.songapp.service.SongService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {
    private static final Logger logger = LoggerFactory.getLogger(SongServiceImpl.class);
    private final SongRepository songRepository;

    @Override
    public Song saveSong(Song song) {
        logger.info("Creating new song: {}", song.getTitle());
        return songRepository.save(song);
    }


    @Cacheable(value = "songs", key = "#id")
    @Override
    public Song getSongById(Long id) {
        logger.info("Fetching song with id: {}", id);
        return songRepository.findById(id).orElseThrow(() -> new NotFoundException("Song not found with id: " + id));
    }

    @Override
    public List<Song> getAllSongs() {
        logger.info("Fetching all songs");
        return songRepository.findAll();
    }
}
