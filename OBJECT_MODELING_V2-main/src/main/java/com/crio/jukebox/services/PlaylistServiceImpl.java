package com.crio.jukebox.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.entities.dtos.PlaylistSongDto;
import com.crio.jukebox.entities.enums.EditPlaylist;
import com.crio.jukebox.entities.enums.SongChange;
import com.crio.jukebox.exceptions.PlaylistEmptyException;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.PlaylistNotPlayingException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;

public class PlaylistServiceImpl implements IPlaylistService {

    private final ISongRepository songRepository;
    private final IPlaylistRepository playlistRepository;
    private final IUserRepository userRepository;

    public PlaylistServiceImpl(ISongRepository sRepository, IPlaylistRepository pRepository, IUserRepository uRepository) {
        this.songRepository = sRepository;
        this.playlistRepository = pRepository;
        this.userRepository = uRepository;
    }

    @Override
    public Playlist create(int userID, String playlistName, List<Integer> songIds) throws SongNotFoundException {

        User user = userRepository.findById(userID).orElseThrow(() -> new UserNotFoundException());

        List<Song> songs = new ArrayList<>();

        for(Integer id : songIds) {
            Song song = songRepository.findById(id).orElseThrow(() -> new SongNotFoundException());
            songs.add(song);
        }

        Playlist playlist = new Playlist(playlistName, songs);
        Playlist newPlaylist = this.playlistRepository.save(playlist);

        user.addPlaylist(newPlaylist);
        userRepository.save(user);
        
        return newPlaylist;
    }

    @Override
    public String deletePlaylist(int userID, int playlistID) throws UserNotFoundException, PlaylistNotFoundException {
        User user = userRepository.findById(userID).orElseThrow(() -> new UserNotFoundException());
        Playlist playlistToDelete = playlistRepository.findById(playlistID).orElseThrow(() -> new PlaylistNotFoundException());

        user.deletePlaylist(playlistToDelete);
        userRepository.save(user);

        String deletePlaylistMsg = playlistRepository.deletePlaylist(playlistToDelete);
        
        return deletePlaylistMsg;
    }

    @Override
    public PlaylistSongDto modifyPlaylist(int userID, int playlistID, List<Integer> songIDs, EditPlaylist addOrDelete) 
                                        throws UserNotFoundException, PlaylistNotFoundException, SongNotFoundException, PlaylistEmptyException {

        User user = userRepository.findById(userID).orElseThrow(() -> new UserNotFoundException());
        Playlist playlist = playlistRepository.findById(playlistID).orElseThrow(() -> new PlaylistNotFoundException());

        if(addOrDelete.equals(EditPlaylist.ADD_SONG)) {

            for(int songID : songIDs) {
                Song song = songRepository.findById(songID).orElseThrow(() -> new SongNotFoundException());
                playlist.addTrack(song);
            }

        } else if(addOrDelete.equals(EditPlaylist.DELETE_SONG)) {

            for(int songID : songIDs) {
                if(playlist.getTracks().size() < 1) throw new PlaylistEmptyException();

                Song song = songRepository.findById(songID).orElseThrow(() -> new SongNotFoundException());
                playlist.deleteTrack(song);
            }

        }

        user.updatePlaylist(playlist);
        userRepository.save(user);

        List<Integer> allSongIds = playlist.getTracks().stream().map(s -> s.getId()).collect(Collectors.toList());

        PlaylistSongDto dto = new PlaylistSongDto(playlistID, playlist.getName(), allSongIds);

        return dto;
    }

    @Override
    public Song playPlaylist(int userID, int playlistID) throws PlaylistNotFoundException, PlaylistEmptyException {

        Song playingSong = playlistRepository.playPlaylist(playlistID);

        return playingSong;
    }

    @Override
    public Song playSong(int userID, SongChange songChange) throws PlaylistNotPlayingException, PlaylistEmptyException {
        Song playingSong = playlistRepository.playSong(songChange);
        return playingSong;
    }

    @Override
    public Song playSong(int userID, int songID) throws PlaylistNotPlayingException, PlaylistEmptyException, SongNotFoundException {

        Song playingSong = playlistRepository.playSong(songID);
        
        return playingSong;
    }
    
}
