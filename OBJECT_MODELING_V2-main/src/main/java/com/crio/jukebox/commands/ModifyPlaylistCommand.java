package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.dtos.PlaylistSongDto;
import com.crio.jukebox.entities.enums.EditPlaylist;
import com.crio.jukebox.services.IPlaylistService;

public class ModifyPlaylistCommand implements ICommand {

    private final IPlaylistService playlistService;

    public ModifyPlaylistCommand(IPlaylistService pService) {
        this.playlistService = pService;
    }

    @Override
    public void execute(List<String> tokens) {

        EditPlaylist playlistOperation = EditPlaylist.ADD_SONG;

        if(tokens.get(1).equals("DELETE-SONG")) playlistOperation = EditPlaylist.DELETE_SONG;

        int userID = Integer.valueOf(tokens.get(2));
        int playlistID = Integer.valueOf(tokens.get(3));
        List<Integer> songIDs = new ArrayList<>();

        for(int i = 4; i < tokens.size(); i++) {
            songIDs.add(Integer.valueOf(tokens.get(i)));
        }

        PlaylistSongDto modifiedPlaylist = playlistService.modifyPlaylist(userID, playlistID, songIDs, playlistOperation);
        System.out.println(modifiedPlaylist);
    }


}
