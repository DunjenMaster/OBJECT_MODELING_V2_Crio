package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.enums.SongChange;
import com.crio.jukebox.services.IPlaylistService;

public class PlaySongCommand implements ICommand {

    private IPlaylistService playlistService;

    public PlaySongCommand(IPlaylistService pService) {
        this.playlistService = pService;
    }

    @Override
    public void execute(List<String> tokens) {
        int userID = Integer.valueOf(tokens.get(1));
        String temp = tokens.get(2);

        Song songPlaying;

        if(temp.equals(SongChange.NEXT.toString()) || temp.equals(SongChange.BACK.toString())) {
            SongChange change = SongChange.valueOf(temp);
            songPlaying = playlistService.playSong(userID, change);
        } else {
            songPlaying = playlistService.playSong(userID, Integer.valueOf(temp));
        }

        if(songPlaying == null) {
            System.out.println("Given song id is not a part of the active playlist");
        } else {
            System.out.println(songPlaying);
        }
        
    }
    
}

