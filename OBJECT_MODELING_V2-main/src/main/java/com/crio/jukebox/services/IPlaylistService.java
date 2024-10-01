
package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.dtos.PlaylistSongDto;
import com.crio.jukebox.entities.enums.EditPlaylist;
import com.crio.jukebox.entities.enums.SongChange;

public interface IPlaylistService {
    
    public Playlist create(int userID, String playlistName, List<Integer> songIds);
    public String deletePlaylist(int userID, int playlistID);
    public PlaylistSongDto modifyPlaylist(int userID, int playlistID, List<Integer> songIDs, EditPlaylist addOrDelete);
    public Song playPlaylist(int userID, int playlistID);
    public Song playSong(int userID, SongChange songChange);
    public Song playSong(int userID, int songID);
}
