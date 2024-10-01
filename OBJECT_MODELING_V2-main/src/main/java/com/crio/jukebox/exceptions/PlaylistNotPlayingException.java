package com.crio.jukebox.exceptions;

public class PlaylistNotPlayingException extends RuntimeException {

    public PlaylistNotPlayingException() {
        super("Playlist is not playing. Play the playlist first to change next song");
    }

    public PlaylistNotPlayingException(String message) {
        super(message);
    }
    
}
