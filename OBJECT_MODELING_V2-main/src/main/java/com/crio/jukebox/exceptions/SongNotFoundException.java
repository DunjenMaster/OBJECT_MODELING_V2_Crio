package com.crio.jukebox.exceptions;

public class SongNotFoundException extends RuntimeException {
    
    public SongNotFoundException() {
        super("Some Requested Songs Not Available. Please try again.");
    }

    public SongNotFoundException(String msg) {
        super(msg);
    }
}

