package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.crio.jukebox.exceptions.PlaylistEmptyException;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;

public class User extends BaseEntity {

    private String name;
    private List<Playlist> playlists;
    // private Song currentSongPlaying;

    public User(String name) {
        this.name = name;
        this.playlists = new ArrayList<>();
        // currentSongPlaying = null;
    }

    public User(int id, String name) {
        super.id = id;
        this.name = name;
        this.playlists = new ArrayList<>();
        // currentSongPlaying = null;
    }

    public String getName() {
        return name;
    }

    public List<Playlist> getAllPlaylists() {
        return playlists;
    }

    public Optional<Playlist> getPlaylistByID(int id) {
        return this.playlists.stream().filter(i -> i.id == id).findAny();
    }

    public void addPlaylist(Playlist newPlaylist) {
        this.playlists.add(newPlaylist);
    }

    public void deletePlaylist(Playlist playlist) throws PlaylistNotFoundException {
        boolean removed = this.playlists.remove(playlist);

        if(!removed) throw new PlaylistNotFoundException();
    }

    public void updatePlaylist(Playlist playlist) throws PlaylistEmptyException {

        for(int i = 0; i < this.playlists.size(); i++) {
            if(this.playlists.get(i).getId() == playlist.getId()) {
                playlists.set(i, playlist);
                return;
            }
        }

        throw new PlaylistNotFoundException();
    }

    // public Song getCurrentSongPlaying() {
        // return this.currentSongPlaying;
    // }


    // public Optional<Playlist> findPlaylist(int playlistID) {
    //     Optional<Playlist> playlist = this.playlists.stream().filter(s -> s.id == playlistID).findAny();
    //     return playlist;
    // }

    // /**
    //  * TODO: editPlaylist will get called whenever user wants to add/delete songs from the playlist.
    //  */
    // public void editPlaylist(Playlist newPlaylist) throws PlaylistNotFoundException {
    //     Playlist oldPlaylist = this.findPlaylist(newPlaylist.id).orElseThrow(() -> new PlaylistNotFoundException());
    //     this.deletePlaylist(oldPlaylist);
    //     this.addPlaylist(newPlaylist);
    // }

    // public Playlist playPlaylist(int playlistID) throws PlaylistNotFoundException, NoPlaylistException, PlaylistEmptyException {
    //     if(this.playlists.size() < 1) throw new NoPlaylistException();

    //     Playlist playlist = this.findPlaylist(playlistID).orElseThrow(() -> new PlaylistNotFoundException());
    //     Song playingSong = playlist.playPlaylist();
    //     // this.currentSongPlaying = playingSong;
    //     return playlist;
    // }

    // public Playlist pausePlaylist() throws NoPlaylistException, PlaylistEmptyException {

    //     if(this.playlists.size() < 1) throw new NoPlaylistException();

    //     for(Playlist playlist : this.playlists) {
    //         if(playlist.getPlaylistStatus().equals(Status.PLAYING)) {
    //             playlist.pausePlaylist();
    //             // this.currentSongPlaying = null;
    //             return playlist;
    //         }
    //     }

    //     return null;
    // }

    // public Optional<Playlist> getCurrentlyPlayingPlaylist() {
    //     return this.playlists.stream().filter(e -> e.getPlaylistStatus().equals(Status.PLAYING)).findAny();
    // }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((playlists == null) ? 0 : playlists.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (playlists == null) {
            if (other.playlists != null)
                return false;
        } else if (!playlists.equals(other.playlists))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return this.id + " " + this.name;
    }

    
}
