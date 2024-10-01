package com.crio.jukebox.entities.dtos;

import java.util.List;

public class PlaylistSongDto {
    private int playlistID;
    private String playlistName;
    private String songIDs;

    
    public PlaylistSongDto(int playlistID, String playlistName, List<Integer> songIDs) {
        this.playlistID = playlistID;
        this.playlistName = playlistName;
        this.songIDs = storeSongIDs(songIDs);
    }

    private String storeSongIDs(List<Integer> songIDs) {
        String res = "";

        for (int songID : songIDs) {
            res += songID + " ";
        }

        return res.trim();
    }


    @Override
    public String toString() {
        String str = "Playlist ID - " + this.playlistID + "\n";
        str += "Playlist Name - " + this.playlistName + "\n";
        str += "Song IDs - " + this.songIDs;

        return str;
    }

    
}
