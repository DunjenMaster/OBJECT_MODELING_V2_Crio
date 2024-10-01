package com.crio.jukebox.exceptions;

public class PlaylistNotFoundException extends RuntimeException {

    public PlaylistNotFoundException() {
        super("Playlist Not Found");
    }
    
    public PlaylistNotFoundException(String msg) {
        super(msg);
    }

}
