package com.crio.jukebox.entities;

public class Song extends BaseEntity {
    private String name;
    private String album;
    private String genre;
    private String albumArtist;
    private String artists;
    // private Status songStatus;

    public Song(String name, String album, String genre, String albumArtist, String artists) {
        this.name = name;
        this.album = album;
        this.albumArtist = albumArtist;
        this.genre = genre;
        this.artists = storeArtists(artists);
        // this.songStatus = Status.PAUSED;
    }

    public Song(int id, String name, String album, String genre, String albumArtist, String artists) {
        super.id = id;
        this.name = name;
        this.album = album;
        this.albumArtist = albumArtist;
        this.genre = genre;
        this.artists = storeArtists(artists);
        // this.songStatus = Status.PAUSED;
    }

    private String storeArtists(String artists) {
        String newArtists = artists.replace("#", ",");
        return newArtists;
    }

    public String getName() {
        return name;
    }
    
    public String getAlbum() {
        return album;
    }

    public String getGenre() {
        return genre;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public String getArtists() {
        return artists;
    }

    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((album == null) ? 0 : album.hashCode());
        result = prime * result + ((albumArtist == null) ? 0 : albumArtist.hashCode());
        result = prime * result + ((artists == null) ? 0 : artists.hashCode());
        result = prime * result + ((genre == null) ? 0 : genre.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        
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
        Song other = (Song) obj;
        if (album == null) {
            if (other.album != null)
                return false;
        } else if (!album.equals(other.album))
            return false;
        if (albumArtist == null) {
            if (other.albumArtist != null)
                return false;
        } else if (!albumArtist.equals(other.albumArtist))
            return false;
        if (artists == null) {
            if (other.artists != null)
                return false;
        } else if (!artists.equals(other.artists))
            return false;
        if (genre == null) {
            if (other.genre != null)
                return false;
        } else if (!genre.equals(other.genre))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        // if (songStatus != other.songStatus)
        //     return false;
        return true;
    }

    @Override
    public String toString() {

        String res = "Current Song Playing" + "\n";
            res += "Song - " + this.name + "\n";
            res += "Album - " + this.album + "\n";
            res += "Artists - " + this.artists;

        return res;
    }

    
}
