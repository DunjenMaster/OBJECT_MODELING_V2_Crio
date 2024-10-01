package com.crio.jukebox.entities;

import java.util.List;

public class Playlist extends BaseEntity {
    private String name;
    private List<Song> tracks;

    public Playlist(String name, List<Song> tracks) {
        this.name = name;
        this.tracks = tracks;
    }

    public Playlist(int id, String name, List<Song> tracks) {
        super.id = id;
        this.name = name;
        this.tracks = tracks;
    }

    public String getName() {
        return name;
    }

    public List<Song> getTracks() {
        return tracks;
    }

    public void addTrack(Song newTrack) {
        this.tracks.add(newTrack);
    }

    public void deleteTrack(Song song) {
        this.tracks.remove(song);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((tracks == null) ? 0 : tracks.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Playlist other = (Playlist) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        //     return false;
        if (tracks == null) {
            if (other.tracks != null)
                return false;
        } else if (!tracks.equals(other.tracks))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Playlist ID - " + this.id;
    }

    
}
