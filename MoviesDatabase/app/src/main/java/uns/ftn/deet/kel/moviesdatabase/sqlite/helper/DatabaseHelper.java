package uns.ftn.deet.kel.moviesdatabase.sqlite.helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

import uns.ftn.deet.kel.moviesdatabase.MainActivity;
import uns.ftn.deet.kel.moviesdatabase.sqlite.model.Artist;
import uns.ftn.deet.kel.moviesdatabase.sqlite.model.Genre;
import uns.ftn.deet.kel.moviesdatabase.sqlite.model.Playlist;
import uns.ftn.deet.kel.moviesdatabase.sqlite.model.Song;

public class DatabaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "MusicLibraryManager";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
        //dropTables();
    }

    // Table Names
    private static final String TABLE_USERS= "users";
    private static final String TABLE_SONGS = "songs";
    private static final String TABLE_ARTISTS= "artists";
    private static final String TABLE_GENRES = "genres";

    private static final String TABLE_PLAYLISTS = "playlists";

    private static final String TABLE_PLAYLIST_SONGS = "playlistSongs";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_ID_GENRE = "idGenre";
    private static final String KEY_ID_ARTIST = "idArtist";
    private static final String KEY_ID_USER = "idUser";
    private static final String KEY_ID_PLAYLIST = "idPlaylist";
    private static final String KEY_ID_SONG = "idSong";


    // Table Create Statements
    private static final String CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS "
            + TABLE_USERS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME
            + " TEXT," + KEY_PASSWORD + " TEXT" + ")";

    private static final String CREATE_TABLE_SONGS = "CREATE TABLE IF NOT EXISTS "
            + TABLE_SONGS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME
            + " TEXT," + KEY_ID_ARTIST + " INTEGER," + KEY_ID_GENRE + " INTEGER" + ")";

    private static final String CREATE_TABLE_ARTISTS = "CREATE TABLE IF NOT EXISTS " + TABLE_ARTISTS
            + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
            + KEY_ID_GENRE + " INTEGER" + ")";

    private static final String CREATE_TABLE_GENRES = "CREATE TABLE IF NOT EXISTS "
            + TABLE_GENRES + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NAME + " TEXT" + ")";

    private static final String CREATE_TABLE_PLAYLISTS = "CREATE TABLE IF NOT EXISTS "
            + TABLE_PLAYLISTS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME
            + " TEXT," + KEY_ID_USER + " INTEGER" + ")";
    private static final String CREATE_TABLE_PLAYLIST_SONGS = "CREATE TABLE IF NOT EXISTS "
            + TABLE_PLAYLIST_SONGS + "(" + KEY_ID_PLAYLIST + " INTEGER," + KEY_ID_SONG
            + " INTEGER" + ")";

    public void createTables() {
        if (db == null)
            db = getWritableDatabase();

        // creating required tables
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_SONGS);
        db.execSQL(CREATE_TABLE_ARTISTS);
        db.execSQL(CREATE_TABLE_GENRES);
        db.execSQL(CREATE_TABLE_PLAYLISTS);
        db.execSQL(CREATE_TABLE_PLAYLIST_SONGS);
    }

    public void dropTables(){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONGS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARTISTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GENRES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYLISTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYLIST_SONGS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_SONGS);
        db.execSQL(CREATE_TABLE_ARTISTS);
        db.execSQL(CREATE_TABLE_GENRES);
        db.execSQL(CREATE_TABLE_PLAYLISTS);
        db.execSQL(CREATE_TABLE_PLAYLIST_SONGS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        dropTables();
        // create new tables
        onCreate(db);
    }

    public boolean checkUserExists(String username, String password) {
        String selectQuery = "SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_NAME + " = ? AND " + KEY_PASSWORD + " = ?";
        Cursor c = db.rawQuery(selectQuery, new String[]{username, password});
        boolean exists = false;
        if (c != null) {
            exists = c.getCount() > 0;
            c.close();
        }
        return exists;
    }

    public boolean checkArtistExists(String name) {
        String selectQuery = "SELECT * FROM " + TABLE_ARTISTS + " WHERE " + KEY_NAME + " = ?";
        Cursor c = db.rawQuery(selectQuery, new String[]{name});
        boolean exists = false;
        if (c != null) {
            exists = c.getCount() > 0;
            c.close();
        }
        return exists;
    }

    public boolean checkGenreExists(String name) {
        String selectQuery = "SELECT * FROM " + TABLE_GENRES + " WHERE " + KEY_NAME + " = ?";
        Cursor c = db.rawQuery(selectQuery, new String[]{name});
        boolean exists = false;
        if (c != null) {
            exists = c.getCount() > 0;
            c.close();
        }
        return exists;
    }

    public boolean checkSongExists(String name) {
        String selectQuery = "SELECT * FROM " + TABLE_SONGS + " WHERE " + KEY_NAME + " = ?";
        Cursor c = db.rawQuery(selectQuery, new String[]{name});
        boolean exists = false;
        if (c != null) {
            exists = c.getCount() > 0;
            c.close();
        }
        return exists;
    }

    public long createUser(String username, String password) {
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, username);
        values.put(KEY_PASSWORD, password);
        long userId = db.insert(TABLE_USERS, null, values);
        return userId;
    }

    public long getIdUser(String name) {
        long retVal = -5;
        String selectQuery = "SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_NAME + " = ?";
        Cursor c = db.rawQuery(selectQuery, new String[]{name});
        if (c != null) {
            c.moveToFirst();
            retVal = c.getLong(c.getColumnIndex(KEY_ID));
            c.close();
        }
        return retVal;
    }

    public long getIdGenre(String name) {
        long retVal = -5;
        String selectQuery = "SELECT * FROM " + TABLE_GENRES + " WHERE " + KEY_NAME + " = ?";
        Cursor c = db.rawQuery(selectQuery, new String[]{name});
        if (c != null) {
            c.moveToFirst();
            retVal = c.getLong(c.getColumnIndex(KEY_ID));
            c.close();
        }
        return retVal;
    }

    public long getIdArtist(String name) {
        long retVal = -5;
        String selectQuery = "SELECT * FROM " + TABLE_ARTISTS + " WHERE " + KEY_NAME + " = ?";
        Cursor c = db.rawQuery(selectQuery, new String[]{name});
        if (c != null) {
            c.moveToFirst();
            retVal = c.getLong(c.getColumnIndex(KEY_ID));
            c.close();
        }
        return retVal;
    }

    public long getIdSong(String name) {
        long retVal = -5;
        String selectQuery = "SELECT * FROM " + TABLE_SONGS + " WHERE " + KEY_NAME + " = ?";
        Cursor c = db.rawQuery(selectQuery, new String[]{name});
        if (c != null) {
            c.moveToFirst();
            retVal = c.getLong(c.getColumnIndex(KEY_ID));
            c.close();
        }
        return retVal;
    }

    public long getIdPlaylist(String name) {
        long retVal = -5;
        String selectQuery = "SELECT * FROM " + TABLE_PLAYLISTS + " WHERE " + KEY_NAME + " = ?";
        Cursor c = db.rawQuery(selectQuery, new String[]{name});
        if (c != null) {
            c.moveToFirst();
            retVal = c.getLong(c.getColumnIndex(KEY_ID));
            c.close();
        }
        return retVal;
    }

    public long createGenre(String g) {
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, g);
        long genreId = db.insert(TABLE_GENRES, null, values);
        return genreId;
    }
    public long createArtist(String a, String g) {
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, a);
        values.put(KEY_ID_GENRE, getIdGenre(g));
        long artistId = db.insert(TABLE_ARTISTS, null, values);
        return artistId;
    }
    public long createSong(String s, String a, String g) {
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, s);
        if(checkArtistExists(a)) {
            Artist tmpA = getArtist(getIdArtist(a));
            values.put(KEY_ID_GENRE, tmpA.getGenre().getId());
        } else {
            createArtist(a, g);
            values.put(KEY_ID_GENRE, getIdGenre(g));
        }
        values.put(KEY_ID_ARTIST, getIdArtist(a));
        long songId = db.insert(TABLE_SONGS, null, values);
        return songId;
    }

    public long createPlaylist(String name, long id) {
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_ID_USER, id);
        long playlistId = db.insert(TABLE_PLAYLISTS, null, values);
        return playlistId;
    }

    public Genre getGenre (long genreId) {
        Genre g = null;
        String selectQuery = "SELECT  * FROM " + TABLE_GENRES + " WHERE " + KEY_ID + " = ?";

        Cursor c = db.rawQuery(selectQuery, new String[]{Long.toString(genreId)});
        if (c != null) {
            if(c.moveToFirst()) {
                g = new Genre();
                g.setId(c.getLong(c.getColumnIndex(KEY_ID)));
                g.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                c.close();
            }
        }
        return g;
    }

    public Artist getArtist (long artistId) {
        Artist a = null;
        String selectQuery = "SELECT  * FROM " + TABLE_ARTISTS + " WHERE " + KEY_ID + " = ?";
        
        Cursor c = db.rawQuery(selectQuery, new String[]{Long.toString(artistId)});
        if (c != null) {
            if(c.moveToFirst()) {
                a = new Artist();
                a.setId(c.getLong(c.getColumnIndex(KEY_ID)));
                a.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                a.setGenre(getGenre(c.getLong(c.getColumnIndex(KEY_ID_GENRE))));
                c.close();
            }
        }
        return a;
    }
    public Song getSong (long songId) {
        Song s = null;
        String selectQuery = "SELECT * FROM " + TABLE_SONGS + " WHERE " + KEY_ID + " = ?";
        
        Cursor c = db.rawQuery(selectQuery, new String[]{Long.toString(songId)});
        if (c != null){
            if(c.moveToFirst()) {
                s = new Song();
                s.setId(c.getLong(c.getColumnIndex(KEY_ID)));
                s.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                s.setArtist(getArtist(c.getLong(c.getColumnIndex(KEY_ID_ARTIST))));
                s.setGenre(getGenre(c.getLong(c.getColumnIndex(KEY_ID_GENRE))));
                c.close();
            }
        }
        return s;
    }

    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> songs = new ArrayList<Song>();
        String selectQuery = "SELECT * FROM " + TABLE_SONGS;
        
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    Song s = new Song();
                    s.setId(c.getLong((c.getColumnIndex(KEY_ID))));
                    s.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                    s.setGenre(getGenre(c.getLong(c.getColumnIndex(KEY_ID_GENRE))));
                    s.setArtist(getArtist(c.getLong(c.getColumnIndex(KEY_ID_ARTIST))));
                    songs.add(s);
                } while (c.moveToNext());
            }
            c.close();
        }
        return songs;
    }

    public ArrayList<Genre> getAllGenres() {
        ArrayList<Genre> genres = new ArrayList<Genre>();
        String selectQuery = "SELECT * FROM " + TABLE_GENRES;
        
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    Genre g = new Genre();
                    g.setId(c.getLong((c.getColumnIndex(KEY_ID))));
                    g.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                    genres.add(g);
                } while (c.moveToNext());
            }
            c.close();
        }
        return genres;
    }

    public ArrayList<Artist> getAllArtists() {
        ArrayList<Artist> artists = new ArrayList<Artist>();
        String selectQuery = "SELECT * FROM " + TABLE_ARTISTS;
        
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    Artist a = new Artist();
                    a.setId(c.getLong((c.getColumnIndex(KEY_ID))));
                    a.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                    a.setGenre(getGenre(c.getLong(c.getColumnIndex(KEY_ID_GENRE))));
                    artists.add(a);
                } while (c.moveToNext());
            }
            c.close();
        }
        return artists;
    }

    public ArrayList<Playlist> getAllPlaylist(long id) {
        ArrayList<Playlist> playlists = new ArrayList<Playlist>();
        String selectQuery = "SELECT * FROM " + TABLE_PLAYLISTS + " WHERE " + KEY_ID_USER + " = ?";
        Cursor c = db.rawQuery(selectQuery, new String[]{Long.toString(id)});
        if (c.moveToFirst()) {
            do {
                Playlist s = new Playlist();
                s.setId(c.getLong((c.getColumnIndex(KEY_ID))));
                s.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                playlists.add(s);
            } while (c.moveToNext());
        }
        c.close();
        return playlists;
    }

    public ArrayList<Song> getAllSongsOnPlaylist(long id) {
        ArrayList<Song> songs = new ArrayList<Song>();
        String selectQuery = "SELECT * FROM " + TABLE_PLAYLIST_SONGS + " WHERE " + KEY_ID_PLAYLIST + " = ?";
        Cursor c = db.rawQuery(selectQuery, new String[]{Long.toString(id)});
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    Song s = getSong(c.getLong((c.getColumnIndex(KEY_ID_SONG))));
                    if (s != null)
                        songs.add(s);
                } while (c.moveToNext());
            }
            c.close();
        }
        return songs;
    }

    public void deleteSong (String name) {

        long idArtist= getSong(getIdSong(name)).getArtist().getId();

        String selectQuery = "SELECT * FROM " + TABLE_PLAYLIST_SONGS + " WHERE " + KEY_ID_SONG + " = ?";
        
        Cursor c = db.rawQuery(selectQuery, new String[]{Long.toString(getIdSong(name))});
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    db.delete(TABLE_PLAYLIST_SONGS, KEY_ID_SONG + " = ?",
                            new String[]{Long.toString(c.getLong((c.getColumnIndex(KEY_ID_SONG))))});
                } while (c.moveToNext());
            }
            c.close();
        }

        db.delete(TABLE_SONGS, KEY_ID + " = ?",
                        new String[] { Long.toString(getIdSong(name)) });

        selectQuery = "SELECT * FROM " + TABLE_SONGS + " WHERE " + KEY_ID_ARTIST + " = ?";
        
        c = db.rawQuery(selectQuery, new String[]{Long.toString(idArtist)});
        if (c != null) {
            if( c.getCount() == 0 ) {
                long idGenre = getArtist(idArtist).getGenre().getId();
                db.delete(TABLE_ARTISTS, KEY_ID + " = ?",
                        new String[] { Long.toString(idArtist) });
                c.close();

                selectQuery = "SELECT * FROM " + TABLE_ARTISTS + " WHERE " + KEY_ID_GENRE + " = ?";
                c = db.rawQuery(selectQuery, new String[]{Long.toString(idGenre)});
                if (c != null) {
                    if( c.getCount() == 0 ) {
                        db.delete(TABLE_GENRES, KEY_ID + " = ?",
                                new String[] { Long.toString(idGenre) });
                    }
                    c.close();
                }
            }
        }
    }
    public void deleteArtist (String name) {
        ArrayList<Artist> artists = new ArrayList<Artist>();
        String selectQuery = "SELECT * FROM " + TABLE_SONGS + " WHERE " + KEY_ID_ARTIST + " = ?";
        
        Cursor c = db.rawQuery(selectQuery, new String[]{Long.toString(getIdArtist(name))});
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    db.delete(TABLE_SONGS, KEY_ID + " = ?",
                            new String[]{Long.toString(c.getLong((c.getColumnIndex(KEY_ID))))});
                } while (c.moveToNext());
            }
            c.close();
        }

        long idGenre = getArtist(getIdArtist(name)).getGenre().getId();
        db.delete(TABLE_ARTISTS, KEY_ID + " = ?",
                new String[] { Long.toString(getIdArtist(name)) });

        selectQuery = "SELECT * FROM " + TABLE_ARTISTS + " WHERE " + KEY_ID_GENRE + " = ?";
        c = db.rawQuery(selectQuery, new String[]{Long.toString(idGenre)});
        if (c != null) {
            if( c.getCount() == 0 ) {
                db.delete(TABLE_GENRES, KEY_ID + " = ?",
                        new String[] { Long.toString(idGenre) });
            }
            c.close();
        }


    }
    public void deleteGenre (String name) {
        ArrayList<Artist> artists = new ArrayList<Artist>();
        String selectQuery = "SELECT * FROM " + TABLE_SONGS + " WHERE " + KEY_ID_GENRE + " = ?";
        
        Cursor c = db.rawQuery(selectQuery, new String[]{Long.toString(getIdGenre(name))});
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    db.delete(TABLE_SONGS, KEY_ID + " = ?",
                            new String[]{Long.toString(c.getLong((c.getColumnIndex(KEY_ID))))});
                } while (c.moveToNext());
            }
            c.close();
        }

        selectQuery = "SELECT * FROM " + TABLE_ARTISTS + " WHERE " + KEY_ID_GENRE + " = ?";
        
        c = db.rawQuery(selectQuery, new String[]{Long.toString(getIdGenre(name))});
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    db.delete(TABLE_ARTISTS, KEY_ID + " = ?",
                            new String[]{Long.toString(c.getLong((c.getColumnIndex(KEY_ID))))});
                } while (c.moveToNext());
            }
            c.close();
        }

        db.delete(TABLE_GENRES, KEY_ID + " = ?",
                new String[] { Long.toString(getIdGenre(name)) });
    }

    public void deletePlaylist(String name, long userId) {
        long playlistId = getIdPlaylist(name);
        db.delete(TABLE_PLAYLIST_SONGS, KEY_ID_PLAYLIST + " = ?", new String[]{Long.toString(playlistId)});

        db.delete(TABLE_PLAYLISTS, KEY_ID + " = ? AND " + KEY_ID_USER + " = ?",
                new String[]{Long.toString(playlistId), Long.toString(userId)});
    }


    public void updatePlaylist(String pl, String newPl) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, newPl);
        db.update(TABLE_PLAYLISTS, values, KEY_ID + " = ?",
                new String[] { Long.toString(getIdPlaylist(pl)) });
    }

    public void updateSong(String song, String newSong) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, newSong);
        db.update(TABLE_SONGS, values, KEY_ID + " = ?",
                new String[] { Long.toString(getIdSong(song)) });
    }

    public void updateArtist(String artist, String newArtist) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, newArtist);
        db.update(TABLE_ARTISTS, values, KEY_ID + " = ?",
                new String[] { Long.toString(getIdArtist(artist)) });
    }

    public void updateGenre(String genre, String newGenre) {
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, newGenre);
        db.update(TABLE_GENRES, values, KEY_ID + " = ?",
                new String[] { Long.toString(getIdGenre(genre)) });
    }

    public ArrayList<Song> searchSong(String pattern) {
        ArrayList<Song> songs = new ArrayList<Song>();
        String selectQuery = "SELECT * FROM " + TABLE_SONGS + " WHERE " + KEY_NAME + " LIKE ?";
        
        Cursor c = db.rawQuery(selectQuery,  new String[]{pattern});
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    Song s = new Song();
                    s.setId(c.getLong((c.getColumnIndex(KEY_ID))));
                    s.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                    s.setGenre(getGenre(c.getLong(c.getColumnIndex(KEY_ID_GENRE))));
                    s.setArtist(getArtist(c.getLong(c.getColumnIndex(KEY_ID_ARTIST))));
                    songs.add(s);
                } while (c.moveToNext());
            }
            c.close();
        }
        return songs;
    }

    public ArrayList<Artist> searchArtist(String pattern) {
        ArrayList<Artist> artists = new ArrayList<Artist>();
        String selectQuery = "SELECT * FROM " + TABLE_ARTISTS + " WHERE " + KEY_NAME + " LIKE ?";
        
        Cursor c = db.rawQuery(selectQuery,  new String[]{pattern});
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    Artist a = new Artist();
                    a.setId(c.getLong((c.getColumnIndex(KEY_ID))));
                    a.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                    a.setGenre(getGenre(c.getLong(c.getColumnIndex(KEY_ID_GENRE))));
                    artists.add(a);
                } while (c.moveToNext());
            }
            c.close();
        }
        return artists;
    }

    public ArrayList<Genre> searchGenre(String pattern) {
        ArrayList<Genre> genres = new ArrayList<Genre>();
        String selectQuery = "SELECT * FROM " + TABLE_GENRES + " WHERE " + KEY_NAME + " LIKE ?";
        
        Cursor c = db.rawQuery(selectQuery,  new String[]{pattern});
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    Genre g = new Genre();
                    g.setId(c.getLong((c.getColumnIndex(KEY_ID))));
                    g.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                    genres.add(g);
                } while (c.moveToNext());
            }
            c.close();
        }
        return genres;
    }

    public ArrayList<Artist> searchArtistByGenre(String text) {
        ArrayList<Artist> artists = new ArrayList<Artist>();
        String selectQuery = "SELECT * FROM " + TABLE_ARTISTS + " WHERE " + KEY_ID_GENRE + " LIKE ?";
        
        Cursor c = db.rawQuery(selectQuery,  new String[]{Long.toString(getIdGenre(text))});
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    Artist a = new Artist();
                    a.setId(c.getLong((c.getColumnIndex(KEY_ID))));
                    a.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                    a.setGenre(getGenre(c.getLong(c.getColumnIndex(KEY_ID_GENRE))));
                    artists.add(a);
                } while (c.moveToNext());
            }
            c.close();
        }
        return artists;
    }

    public ArrayList<Song> searchSongByGenre(String text) {
        ArrayList<Song> songs = new ArrayList<Song>();
        String selectQuery = "SELECT * FROM " + TABLE_SONGS + " WHERE " + KEY_ID_GENRE + " LIKE ?";
        
        Cursor c = db.rawQuery(selectQuery,  new String[]{Long.toString(getIdGenre(text))});
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    Song s = new Song();
                    s.setId(c.getLong((c.getColumnIndex(KEY_ID))));
                    s.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                    s.setGenre(getGenre(c.getLong(c.getColumnIndex(KEY_ID_GENRE))));
                    s.setArtist(getArtist(c.getLong(c.getColumnIndex(KEY_ID_ARTIST))));
                    songs.add(s);
                } while (c.moveToNext());
            }
            c.close();
        }
        return songs;
    }

    public ArrayList<Song> searchSongByArtist(String text) {
        ArrayList<Song> songs = new ArrayList<Song>();
        String selectQuery = "SELECT * FROM " + TABLE_SONGS + " WHERE " + KEY_ID_ARTIST + " LIKE ?";
        
        Cursor c = db.rawQuery(selectQuery,  new String[]{Long.toString(getIdArtist(text))});
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    Song s = new Song();
                    s.setId(c.getLong((c.getColumnIndex(KEY_ID))));
                    s.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                    s.setGenre(getGenre(c.getLong(c.getColumnIndex(KEY_ID_GENRE))));
                    s.setArtist(getArtist(c.getLong(c.getColumnIndex(KEY_ID_ARTIST))));
                    songs.add(s);
                } while (c.moveToNext());
            }
            c.close();
        }
        return songs;
    }

    public void addSongOnPlaylist(String song, String playlist) {
        String selectQuery = "SELECT * FROM " + TABLE_PLAYLIST_SONGS + " WHERE " + KEY_ID_PLAYLIST + " = ? AND " + KEY_ID_SONG + " = ?";
        
        Cursor c = db.rawQuery(selectQuery,  new String[]{Long.toString(getIdPlaylist(playlist)), Long.toString(getIdSong(song))});
        if (c != null) {
            if (c.getCount() == 0) {
                db = getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(KEY_ID_PLAYLIST, getIdPlaylist(playlist));
                values.put(KEY_ID_SONG, getIdSong(song));
                long songId = db.insert(TABLE_PLAYLIST_SONGS, null, values);
            }
            c.close();
        }
    }

    public void removeSongOnPlaylist(String song, String playlist) {
        String selectQuery = "SELECT * FROM " + TABLE_PLAYLIST_SONGS + " WHERE " + KEY_ID_PLAYLIST + " = ? AND " + KEY_ID_SONG + " = ?";
        
        Cursor c = db.rawQuery(selectQuery,  new String[]{Long.toString(getIdPlaylist(playlist)), Long.toString(getIdSong(song))});
        if (c != null) {
            if (c.getCount() > 0) {
                db.delete(TABLE_PLAYLIST_SONGS, KEY_ID_PLAYLIST + " = ? AND " + KEY_ID_SONG + " = ?",
                        new String[]{Long.toString(getIdPlaylist(playlist)), Long.toString(getIdSong(song))});
            }
            c.close();
        }
    }

    // closing database
    public void closeDB() {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }


}
