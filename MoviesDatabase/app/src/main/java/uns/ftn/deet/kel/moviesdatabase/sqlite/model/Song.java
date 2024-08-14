package uns.ftn.deet.kel.moviesdatabase.sqlite.model;

public class Song {
    long id;
    String name;
    Genre genre;
    Artist artist;

    public Song() {}

    public Song(String name, Genre genre, Artist artist) {
        this.name = name;
        this.genre = genre;
        this.artist = artist;
    }

    public Song(long id, String name, Genre genre, Artist artist) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.artist = artist;
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
