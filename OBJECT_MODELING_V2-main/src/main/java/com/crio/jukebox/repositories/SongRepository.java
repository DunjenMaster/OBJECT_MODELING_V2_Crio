package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
// import com.crio.jukebox.ISongRepository;
import com.crio.jukebox.entities.Song;

public class SongRepository implements ISongRepository{

    private Map<Integer, Song> songMap;
    private int autoIncrementId = 0;

    public SongRepository() {
        this.songMap = new HashMap<>();
    }

    public SongRepository(Map<Integer, Song> sMap) {
        this.songMap = sMap;
        this.autoIncrementId = songMap.size();
    }

    @Override
    public Song save(Song song) {

        int id = song.getId();

        if(id < 1) {
            autoIncrementId++;
            Song newSong = new Song(autoIncrementId, song.getName(), song.getAlbum(), song.getGenre(), song.getAlbumArtist(), song.getArtists());
            this.songMap.put(autoIncrementId, newSong);
            return newSong;
        }

        this.songMap.put(id, song);
        return song;
    }

    @Override
    public List<Song> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Song> findById(int id) {
        Optional<Song> song = Optional.of(this.songMap.get(id)) ;
        return song;
    }

    @Override
    public Optional<Song> findByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
