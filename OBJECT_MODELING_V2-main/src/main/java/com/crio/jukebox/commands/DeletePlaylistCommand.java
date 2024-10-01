package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.IPlaylistService;

public class DeletePlaylistCommand implements ICommand {

    private final IPlaylistService playlistService;

    public DeletePlaylistCommand(IPlaylistService pService) {
        this.playlistService = pService;
    }

    @Override
    public void execute(List<String> tokens) {
        
        int userID = Integer.valueOf(tokens.get(1));
        int playlistID = Integer.valueOf(tokens.get(2));

        String deletedPlaylist = playlistService.deletePlaylist(userID, playlistID);
        System.out.println(deletedPlaylist);
    }
    
}

