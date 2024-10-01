package com.crio.jukebox.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.ISongRepository;

public class LoadSongsCommand implements ICommand {

    private final ISongRepository songRepository;

    public LoadSongsCommand(ISongRepository sRepository) {
        this.songRepository = sRepository;
    }

    @Override
    public void execute(List<String> tokens) {
        
        String songFileName = tokens.get(1);
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(songFileName));
            String line = reader.readLine();
            List<Song> savedSongs = new ArrayList<>();

            while(line != null) {
                List<String> songs = Arrays.asList(line.split(","));

                if(songs.get(0).equals("<<<<<<< HEAD") || songs.get(0).equals("=======") || songs.get(0).equals(">>>>>>> ecd7888c0ee57b8f868c6a89c3e3d4591980036a")) {
                    line = reader.readLine();
                    continue;
                }

                Song song;
                
                if(isInteger(songs.get(0))) {
                    song = new Song(Integer.valueOf(songs.get(0)), songs.get(1), songs.get(3), songs.get(2), songs.get(4), songs.get(5));
                } else {
                    song = new Song(songs.get(0), songs.get(2), songs.get(1), songs.get(3), songs.get(4));
                }

                savedSongs.add(songRepository.save(song));
                line = reader.readLine();
            }
            reader.close();

            if(!savedSongs.isEmpty())
             System.out.println("Songs Loaded successfully");
             
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }
    
}
