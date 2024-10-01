package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.enums.SongChange;
import com.crio.jukebox.exceptions.PlaylistEmptyException;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.PlaylistNotPlayingException;
import com.crio.jukebox.exceptions.SongNotFoundException;

public class PlaylistRepository implements IPlaylistRepository {

    private final Map<Integer, Playlist> playlistMap;
    private int autoIncrementId = 0;
    private int currentPlayingPlaylistID = -1;
    private int currentPlayingSongID = -1;

    public PlaylistRepository() {
        this.playlistMap = new HashMap<>();
    }

    public PlaylistRepository(Map<Integer, Playlist> pMap) {
        this.playlistMap = pMap;
        this.autoIncrementId = playlistMap.size();
    }

    @Override
    public Playlist save(Playlist playlist) {
        int id = playlist.getId();

        if(id < 1) {
            this.autoIncrementId++;
            Playlist newPlaylist = new Playlist(autoIncrementId, playlist.getName(), playlist.getTracks());
            this.playlistMap.put(autoIncrementId, newPlaylist);
            return newPlaylist;
        }

        this.playlistMap.put(autoIncrementId, playlist);
        return playlist;
    }

    @Override
    public List<Playlist> findAll() {
        return this.playlistMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Playlist> findById(int id) {
        return Optional.of(this.playlistMap.get(id));
    }

    @Override
    public Optional<Playlist> findByName(String name) {
        Optional<Playlist> playlist = this.playlistMap.values().stream().filter(s -> s.getName().equals(name)).findAny();
        return playlist;
    }

    @Override
    public String deletePlaylist(Playlist playlist) {
        
        if(playlist.getId() == this.currentPlayingPlaylistID) {
            this.currentPlayingPlaylistID = -1;
            this.currentPlayingSongID = -1;
        }

        boolean playlistRemoved = this.playlistMap.values().removeIf(s -> s.getId() == playlist.getId());

        if(playlistRemoved) {
            return "Delete Successful";
        }

        return "Not able to remove the playlist: " + playlist.getName();
    }

    @Override
    public Song playPlaylist(int playlistID) throws PlaylistNotFoundException, PlaylistEmptyException {

        if(!this.playlistMap.containsKey(playlistID)) throw new PlaylistNotFoundException();
        Playlist playlist = this.findById(playlistID).orElseThrow(() -> new PlaylistNotFoundException());
        if(playlist.getTracks().size() < 1) throw new PlaylistEmptyException();

        this.currentPlayingPlaylistID = playlistID;

        Song playingSong = playlist.getTracks().get(0);
        this.currentPlayingSongID = playingSong.getId();
        
        return playingSong;
    }

    @Override
    public Song playSong(SongChange songChange) throws PlaylistNotPlayingException, PlaylistEmptyException{
        if(this.currentPlayingPlaylistID == -1 || this.currentPlayingSongID == -1) throw new PlaylistNotPlayingException();

        List<Song> songs = this.playlistMap.get(this.currentPlayingPlaylistID).getTracks();

        if(songs.isEmpty()) throw new PlaylistEmptyException();

        for(int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            if(song.getId() == this.currentPlayingSongID) {

                Song currentlyPlayingSong;

                if(songChange.equals(SongChange.NEXT)) {
                    if(i == songs.size()-1) {
                        currentlyPlayingSong = songs.get(0);
                    } else {
                        currentlyPlayingSong = songs.get(++i);
                    }
                } else {
                    if(i == 0) {
                        currentlyPlayingSong = songs.get(songs.size()-1);
                    } else {
                        currentlyPlayingSong = songs.get(--i);
                    }
                }

                this.currentPlayingSongID = currentlyPlayingSong.getId();

                return currentlyPlayingSong;
            }
        }

        return null;
    }

    @Override
    public Song playSong(int songID) throws PlaylistNotPlayingException, PlaylistEmptyException, SongNotFoundException {

        if(this.currentPlayingPlaylistID == -1 || this.currentPlayingSongID == -1) throw new PlaylistNotPlayingException();
       
        List<Song> songs = this.playlistMap.get(this.currentPlayingPlaylistID).getTracks();

        if(songs.isEmpty()) throw new PlaylistEmptyException();
        Song playingSong = null ;

        for(Song song : songs) {
            if(song.getId() == songID) {
                this.currentPlayingSongID = song.getId();
                playingSong = song;
                break;
            }
        }

        return playingSong;
    }
    
}
