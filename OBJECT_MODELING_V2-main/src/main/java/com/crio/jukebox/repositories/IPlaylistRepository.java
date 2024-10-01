package com.crio.jukebox.repositories;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.enums.SongChange;

public interface IPlaylistRepository extends CRUDRepository<Playlist> {
    String deletePlaylist(Playlist playlist);
    Song playPlaylist(int playlistID);
    Song playSong(SongChange songChange);
    Song playSong(int songID);
}


