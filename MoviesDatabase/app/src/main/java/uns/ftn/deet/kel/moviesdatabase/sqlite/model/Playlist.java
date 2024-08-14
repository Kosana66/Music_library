package uns.ftn.deet.kel.moviesdatabase.sqlite.model;

import java.util.List;

import uns.ftn.deet.kel.moviesdatabase.sqlite.model.Song;

public class Playlist {
    long id;
    String name;
    List<Song> songs;

    public Playlist() {}

    public Playlist(String name) {
        this.name = name;
    }

    public Playlist(long id, String name, List<Song> songs) {
        this.id = id;
        this.name = name;
        this.songs = songs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
