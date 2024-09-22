package com.ojasare.songapp.impl;

import com.ojasare.songapp.exception.NotFoundException;
import com.ojasare.songapp.model.Song;
import com.ojasare.songapp.repository.SongRepository;
import com.ojasare.songapp.service.impl.SongServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class SongServiceImplTest {

    @Mock
    private SongRepository songRepository;

    @InjectMocks
    private SongServiceImpl songService;

    private Song song;

    @BeforeEach
    public void setUp() {
        song = new Song();
        song.setId(1L);
        song.setTitle("Bad Guy");
        song.setArtist("Ed Sheeran");
        song.setAlbum("Back from the Edge");
    }

    @Test
    public void testSaveSong() {
        when(songRepository.save(song)).thenReturn(song);

        Song savedSong = songService.saveSong(song);

        assertNotNull(savedSong);
        assertEquals("Bad Guy", savedSong.getTitle());
        verify(songRepository, times(1)).save(song);
    }

    @Test
    public void testGetSongById() {
        when(songRepository.findById(1L)).thenReturn(Optional.of(song));

        Song foundSong = songService.getSongById(1L);

        assertNotNull(foundSong);
        assertEquals("Back from the Edge", foundSong.getAlbum());
        verify(songRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllSongs() {
        List<Song> songs = Collections.singletonList(song);
        when(songRepository.findAll()).thenReturn(songs);

        List<Song> allSongs = songService.getAllSongs();

        assertNotNull(allSongs);
        assertEquals(1, allSongs.size());
        assertEquals("Ed Sheeran", allSongs.getFirst().getArtist());
        verify(songRepository, times(1)).findAll();
    }
}