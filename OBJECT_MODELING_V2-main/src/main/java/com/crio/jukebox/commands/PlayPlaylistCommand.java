package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.services.IPlaylistService;

public class PlayPlaylistCommand implements ICommand {

    private IPlaylistService playlistService;

    public PlayPlaylistCommand(IPlaylistService pService) {
        this.playlistService = pService;
    }

    @Override
    public void execute(List<String> tokens) {
        int userID = Integer.valueOf(tokens.get(1));
        int playlistID = Integer.valueOf(tokens.get(2));
        
        Song currentSongPlaying = this.playlistService.playPlaylist(userID, playlistID);
        System.out.println(currentSongPlaying);
    }
    
}

