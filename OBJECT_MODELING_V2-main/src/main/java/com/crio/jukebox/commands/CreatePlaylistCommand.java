package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.services.IPlaylistService;

public class CreatePlaylistCommand implements ICommand {

    private final IPlaylistService playlistService;

    public CreatePlaylistCommand(IPlaylistService  pService) {
        this.playlistService = pService;
    }

    @Override
    public void execute(List<String> tokens) {
        int userID = Integer.valueOf(tokens.get(1));
        String playlistName = tokens.get(2);
        List<Integer> songIDs = new ArrayList<>();

        for(int i = 3; i < tokens.size(); i++) {
            songIDs.add(Integer.valueOf(tokens.get(i)));
        }

        Playlist playlist = playlistService.create(userID, playlistName, songIDs);
        System.out.println(playlist);
        
    }
    
}

