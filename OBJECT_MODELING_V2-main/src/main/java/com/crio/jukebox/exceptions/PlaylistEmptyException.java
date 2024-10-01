package com.crio.jukebox.exceptions;

public class PlaylistEmptyException extends RuntimeException {

    public PlaylistEmptyException() {
        super("Playlist is empty.");
    }

    public PlaylistEmptyException(String msg) {
        super(msg);
    }
    
}

