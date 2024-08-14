package uns.ftn.deet.kel.moviesdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import uns.ftn.deet.kel.moviesdatabase.sqlite.helper.DatabaseHelper;
import uns.ftn.deet.kel.moviesdatabase.sqlite.model.Playlist;
import uns.ftn.deet.kel.moviesdatabase.sqlite.model.Song;
import uns.ftn.deet.kel.moviesdatabase.sqlite.model.Artist;
import uns.ftn.deet.kel.moviesdatabase.sqlite.model.Genre;

public class MainActivity extends AppCompatActivity {
    // Database Helper
    DatabaseHelper dbHelper;

    private long id;

    private EditText etName;
    private EditText  etPassword;
    private EditText  etFieldSong;
    private EditText  etFieldArtist;
    private EditText  etFieldGenre;
    private EditText  etFieldPlaylist;
    private Button btnLogin;
    private Button btnAddSong;
    private Button btnAddArtist;
    private Button btnAddGenre;

    private Button btnRemoveSong;
    private Button btnRemoveArtist;
    private Button btnRemoveGenre;

    private Button btnEditSong;
    private Button btnEditArtist;
    private Button btnEditGenre;

    private Button btnChangeSongToArtist;
    private Button btnChangeSongToGenre;
    private Button btnChangeArtistToSong;
    private Button btnChangeArtistToGenre;
    private Button btnChangeGenreToArtist;
    private Button btnChangeGenreToSong;
    private Button btnChangeArtistToPlaylist;
    private Button btnChangeSongToPlaylist;
    private Button btnChangeGenreToPlaylist;
    private Button btnChangePlaylistToSong;
    private Button btnSearchSong;
    private Button btnSearchArtist;
    private Button btnSearchGenre;
    private Button btnSearchByArtist;
    private Button btnSearchByGenre;
    private Button btnSearchByGenreS;
    private Button btnAddPlaylist;
    private Button btnRemovePlaylist;
    private Button btnReplacePlaylist;
    private Button btnAddToPlaylist;
    private Button btnRemoveToPlaylist;


    private ConstraintLayout lyLogin;
    private ConstraintLayout lySongs;
    private ConstraintLayout lyArtist;
    private ConstraintLayout lyGenre;
    private ConstraintLayout lyPlaylist;

    private Spinner spnSongs;
    private Spinner spnGenres;
    private Spinner spnArtists;
    private Spinner spnArtS;
    private Spinner spnGnrA;
    private Spinner spnGnrS;
    private Spinner spnPlaylists;
    private Spinner spnAllSongs;
    private Spinner spnSongsOnPlaylist;



    @Override
    protected void onStop() {
        super.onStop();
        dbHelper.closeDB();
    }

    @Override
    protected void onNightModeChanged(int mode) {
        super.onNightModeChanged(mode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lyLogin = findViewById(R.id.lyLogin);
        lySongs = findViewById(R.id.lySongs);
        lyArtist = findViewById(R.id.lyArtist);
        lyGenre = findViewById(R.id.lyGenre);
        lyPlaylist = findViewById(R.id.lyPlaylist);
        lyLogin.setVisibility(View.VISIBLE);
        lySongs.setVisibility(View.GONE);
        lyArtist.setVisibility(View.GONE);
        lyGenre.setVisibility(View.GONE);
        lyPlaylist.setVisibility(View.GONE);

        dbHelper = new DatabaseHelper(getApplicationContext());

        etName = (EditText) findViewById(R.id.etName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        etFieldSong = (EditText) findViewById(R.id.etFieldSong);
        etFieldArtist = (EditText) findViewById(R.id.etFieldArtist);
        etFieldGenre = (EditText) findViewById(R.id.etFieldGenre);
        etFieldPlaylist = (EditText) findViewById(R.id.etFieldPlaylist);

        btnAddSong = (Button) findViewById(R.id.btnAddSong);
        btnAddArtist = (Button) findViewById(R.id.btnAddArtist);
        btnAddGenre = (Button) findViewById(R.id.btnAddGenre);

        btnRemoveSong = (Button) findViewById(R.id.btnRemoveSong);
        btnRemoveArtist = (Button) findViewById(R.id.btnRemoveArtist);
        btnRemoveGenre = (Button) findViewById(R.id.btnRemoveGenre);

        btnEditSong = (Button) findViewById(R.id.btnEditSong);
        btnEditArtist = (Button) findViewById(R.id.btnEditArtist);
        btnEditGenre = (Button) findViewById(R.id.btnEditGenre);

        btnSearchSong = (Button) findViewById(R.id.btnSearchSong);
        btnSearchArtist = (Button) findViewById(R.id.btnSearchArtist);
        btnSearchGenre = (Button) findViewById(R.id.btnSearchGenre);

        btnChangeSongToArtist = (Button) findViewById(R.id.btnChangeSongToArtist);
        btnChangeSongToGenre = (Button) findViewById(R.id.btnChangeSongToGenre);
        btnChangeArtistToSong = (Button) findViewById(R.id.btnChangeArtistToSong);
        btnChangeArtistToGenre = (Button) findViewById(R.id.btnChangeArtistToGenre);
        btnChangeGenreToSong = (Button) findViewById(R.id.btnChangeGenreToSong);
        btnChangeGenreToArtist = (Button) findViewById(R.id.btnChangeGenreToArtist);

        btnChangeSongToPlaylist = (Button) findViewById(R.id.btnChangeSongToPlaylist);
        btnChangeArtistToPlaylist = (Button) findViewById(R.id.btnChangeArtistToPlaylist);
        btnChangeGenreToPlaylist= (Button) findViewById(R.id.btnChangeGenreToPlaylist);
        btnChangePlaylistToSong = (Button) findViewById(R.id.btnChangePlaylistToSong);

        btnSearchByArtist = (Button) findViewById(R.id.btnSearchByArtist);
        btnSearchByGenre = (Button) findViewById(R.id.btnSearchByGenre);
        btnSearchByGenreS = (Button) findViewById(R.id.btnSearchByGenreS);

        btnAddPlaylist = (Button) findViewById(R.id.btnAddPlaylist);
        btnRemovePlaylist = (Button) findViewById(R.id.btnRemovePlaylist);
        btnReplacePlaylist = (Button) findViewById(R.id.btnReplacePlaylist);
        btnAddToPlaylist = (Button) findViewById(R.id.btnAddToPlaylist);
        btnRemoveToPlaylist= (Button) findViewById(R.id.btnRemoveToPlaylist);

        spnSongs = (Spinner) findViewById(R.id.spnSongs);
        spnArtists = (Spinner) findViewById(R.id.spnArtists);
        spnGenres = (Spinner) findViewById(R.id.spnGenres);

        spnArtS = (Spinner) findViewById(R.id.spnArtS);
        spnGnrA = (Spinner) findViewById(R.id.spnGnrA);
        spnGnrS = (Spinner) findViewById(R.id.spnGnrS);

        spnPlaylists = (Spinner) findViewById(R.id.spnPlaylists);
        spnAllSongs = (Spinner) findViewById(R.id.spnAllSongs);
        spnSongsOnPlaylist = (Spinner) findViewById(R.id.spnSongsOnPlaylist);

        createTablesAndInitData();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (dbHelper.checkUserExists(name, password)) {
                    lySongs.setVisibility(View.VISIBLE);
                    lyLogin.setVisibility(View.GONE);
                    id = dbHelper.getIdUser(name);
                    Toast.makeText(MainActivity.this, "User exists", Toast.LENGTH_SHORT).show();

                } else {
                    lySongs.setVisibility(View.VISIBLE);
                    lyLogin.setVisibility(View.GONE);
                    id = dbHelper.createUser(name, password);
                    etName.setText("");
                    etPassword.setText("");
                    Toast.makeText(MainActivity.this, "User successfully added", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAddSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] songInfo = etFieldSong.getText().toString().trim().split(",");
                if (songInfo.length != 3) {
                    Toast.makeText(MainActivity.this, "Please enter Song, Artist, and Genre separated by commas", Toast.LENGTH_SHORT).show();
                    return;
                }
                String tmpSongName = songInfo[0].trim();
                String tmpArtistName = songInfo[1].trim();
                String tmpGenreName = songInfo[2].trim();
                if (dbHelper.checkSongExists(tmpSongName)) {
                    Toast.makeText(MainActivity.this, "Song exists", Toast.LENGTH_SHORT).show();
                } else {
                    if (dbHelper.checkArtistExists(tmpArtistName)) {
                        Toast.makeText(MainActivity.this, "Artist exists", Toast.LENGTH_SHORT).show();
                        if(!dbHelper.checkGenreExists(tmpGenreName))
                            Toast.makeText(MainActivity.this, "Wrong genre, but the artist genre was used", Toast.LENGTH_SHORT).show();
                        dbHelper.createSong(tmpSongName,tmpArtistName, tmpGenreName);
                        etFieldSong.setText("");
                        loadSpinnerSongs(dbHelper.getAllSongs());
                        loadSpinnerAllSongs(dbHelper.getAllSongs());
                        Toast.makeText(MainActivity.this, "Song successfully added", Toast.LENGTH_SHORT).show();
                    } else {
                        if (dbHelper.checkGenreExists(tmpGenreName)) {
                            Toast.makeText(MainActivity.this, "Genre exists", Toast.LENGTH_SHORT).show();
                        } else {
                            dbHelper.createGenre(tmpGenreName);
                            loadSpinnerGenres(dbHelper.getAllGenres());
                            loadSpinnerSearchGnrS(dbHelper.getAllGenres());
                            loadSpinnerSearchGnrA(dbHelper.getAllGenres());
                            Toast.makeText(MainActivity.this, "Genre successfully added", Toast.LENGTH_SHORT).show();
                        }
                        dbHelper.createSong(tmpSongName,tmpArtistName, tmpGenreName);
                        etFieldSong.setText("");
                        loadSpinnerArtists(dbHelper.getAllArtists());
                        loadSpinnerSearchArtS(dbHelper.getAllArtists());
                        Toast.makeText(MainActivity.this, "Artist successfully added", Toast.LENGTH_SHORT).show();
                        loadSpinnerSongs(dbHelper.getAllSongs());
                        loadSpinnerAllSongs(dbHelper.getAllSongs());
                        Toast.makeText(MainActivity.this, "Song successfully added", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btnAddArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] artistInfo = etFieldArtist.getText().toString().trim().split(",");
                if (artistInfo.length != 2) {
                    Toast.makeText(MainActivity.this, "Please enter Artist and Genre separated by a comma", Toast.LENGTH_SHORT).show();
                    return;
                }
                String tmpArtistName = artistInfo[0].trim();
                String tmpGenreName = artistInfo[1].trim();
                if (dbHelper.checkArtistExists(tmpArtistName)) {
                    Toast.makeText(MainActivity.this, "Artist exists", Toast.LENGTH_SHORT).show();
                } else {
                    if (dbHelper.checkGenreExists(tmpGenreName)) {
                        Toast.makeText(MainActivity.this, "Genre exists", Toast.LENGTH_SHORT).show();
                    } else {
                        dbHelper.createGenre(tmpGenreName);
                        loadSpinnerGenres(dbHelper.getAllGenres());
                        loadSpinnerSearchGnrS(dbHelper.getAllGenres());
                        loadSpinnerSearchGnrA(dbHelper.getAllGenres());
                        Toast.makeText(MainActivity.this, "Genre successfully added", Toast.LENGTH_SHORT).show();
                    }
                    dbHelper.createArtist(tmpArtistName, tmpGenreName);
                    etFieldArtist.setText("");
                    loadSpinnerArtists(dbHelper.getAllArtists());
                    loadSpinnerSearchArtS(dbHelper.getAllArtists());
                    Toast.makeText(MainActivity.this, "Artist successfully added", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAddGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmpName = etFieldGenre.getText().toString().trim();
                if (dbHelper.checkGenreExists(tmpName)) {
                    Toast.makeText(MainActivity.this, "Genre exists", Toast.LENGTH_SHORT).show();
                } else {
                    dbHelper.createGenre(tmpName);
                    etFieldGenre.setText("");
                    loadSpinnerGenres(dbHelper.getAllGenres());
                    loadSpinnerSearchGnrS(dbHelper.getAllGenres());
                    loadSpinnerSearchGnrA(dbHelper.getAllGenres());
                    Toast.makeText(MainActivity.this, "Genre successfully added", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnChangeSongToArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lyArtist.setVisibility(View.VISIBLE);
                lySongs.setVisibility(View.GONE);
            }
        });

        btnChangeSongToGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lyGenre.setVisibility(View.VISIBLE);
                lySongs.setVisibility(View.GONE);
            }
        });

        btnChangeArtistToSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lySongs.setVisibility(View.VISIBLE);
                lyArtist.setVisibility(View.GONE);
            }
        });

        btnChangeArtistToGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lyGenre.setVisibility(View.VISIBLE);
                lyArtist.setVisibility(View.GONE);
            }
        });

        btnChangeGenreToSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lySongs.setVisibility(View.VISIBLE);
                lyGenre.setVisibility(View.GONE);
            }
        });

        btnChangeGenreToArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lyArtist.setVisibility(View.VISIBLE);
                lyGenre.setVisibility(View.GONE);
            }
        });

        btnChangeSongToPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lyPlaylist.setVisibility(View.VISIBLE);
                lySongs.setVisibility(View.GONE);
                loadSpinnerPlaylists(dbHelper.getAllPlaylist(id));
                loadSpinnerAllSongs(dbHelper.getAllSongs());
            }
        });

        btnChangeArtistToPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lyPlaylist.setVisibility(View.VISIBLE);
                lyArtist.setVisibility(View.GONE);
                loadSpinnerPlaylists(dbHelper.getAllPlaylist(id));
                loadSpinnerAllSongs(dbHelper.getAllSongs());
            }
        });

        btnChangeGenreToPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lyPlaylist.setVisibility(View.VISIBLE);
                lyGenre.setVisibility(View.GONE);
                loadSpinnerPlaylists(dbHelper.getAllPlaylist(id));
                loadSpinnerAllSongs(dbHelper.getAllSongs());
            }
        });
        btnChangePlaylistToSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lyPlaylist.setVisibility(View.GONE);
                lySongs.setVisibility(View.VISIBLE);
            }
        });


        btnRemoveSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spnSongs.getSelectedItem() != null) {
                    String song = spnSongs.getSelectedItem().toString();
                    dbHelper.deleteSong(song);
                    loadSpinnerSongs(dbHelper.getAllSongs());
                    loadSpinnerArtists(dbHelper.getAllArtists());
                    loadSpinnerGenres(dbHelper.getAllGenres());
                    loadSpinnerSearchArtS(dbHelper.getAllArtists());
                    loadSpinnerSearchGnrS(dbHelper.getAllGenres());
                    loadSpinnerSearchGnrA(dbHelper.getAllGenres());
                    loadSpinnerAllSongs(dbHelper.getAllSongs());
                    if(spnPlaylists.getSelectedItem() == null) {
                    	loadSpinnerSongsOnPlaylist(null);
                    } 
                    else {
            		    loadSpinnerSongsOnPlaylist(dbHelper.getAllSongsOnPlaylist(dbHelper.getIdPlaylist(spnPlaylists.getSelectedItem().toString())));
                    }
                }
                else
                    Toast.makeText(MainActivity.this, "Song isn't selected", Toast.LENGTH_SHORT).show();
            }
        });

        btnRemoveArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spnArtists.getSelectedItem() != null) {
                    String artist = spnArtists.getSelectedItem().toString();
                    dbHelper.deleteArtist(artist);
                    loadSpinnerSongs(dbHelper.getAllSongs());
                    loadSpinnerArtists(dbHelper.getAllArtists());
                    loadSpinnerGenres(dbHelper.getAllGenres());
                    loadSpinnerSearchArtS(dbHelper.getAllArtists());
                    loadSpinnerSearchGnrS(dbHelper.getAllGenres());
                    loadSpinnerSearchGnrA(dbHelper.getAllGenres());
                    loadSpinnerAllSongs(dbHelper.getAllSongs());
                }
                else
                    Toast.makeText(MainActivity.this, "Artist isn't selected", Toast.LENGTH_SHORT).show();
            }
        });

        btnRemoveGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spnGenres.getSelectedItem() != null) {
                    String genre = spnGenres.getSelectedItem().toString();
                    dbHelper.deleteGenre(genre);
                    loadSpinnerSongs(dbHelper.getAllSongs());
                    loadSpinnerArtists(dbHelper.getAllArtists());
                    loadSpinnerGenres(dbHelper.getAllGenres());
                    loadSpinnerSearchArtS(dbHelper.getAllArtists());
                    loadSpinnerSearchGnrS(dbHelper.getAllGenres());
                    loadSpinnerSearchGnrA(dbHelper.getAllGenres());
                    loadSpinnerAllSongs(dbHelper.getAllSongs());
                }
                else
                    Toast.makeText(MainActivity.this, "Genre isn't selected", Toast.LENGTH_SHORT).show();
            }
        });

        btnEditSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spnSongs.getSelectedItem() != null) {
                    String song = spnSongs.getSelectedItem().toString();
                    dbHelper.updateSong(song, etFieldSong.getText().toString().trim());
                    etFieldSong.setText("");
                    loadSpinnerSongs(dbHelper.getAllSongs());
                    loadSpinnerAllSongs(dbHelper.getAllSongs());
                }
                else
                    Toast.makeText(MainActivity.this, "Song isn't selected", Toast.LENGTH_SHORT).show();
            }
        });

        btnEditArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spnArtists.getSelectedItem() != null) {
                    String artist = spnArtists.getSelectedItem().toString();
                    dbHelper.updateArtist(artist, etFieldArtist.getText().toString().trim());
                    etFieldArtist.setText("");
                    loadSpinnerArtists(dbHelper.getAllArtists());
                    loadSpinnerSearchArtS(dbHelper.getAllArtists());
                }
                else
                    Toast.makeText(MainActivity.this, "Artist isn't selected", Toast.LENGTH_SHORT).show();
            }
        });

        btnEditGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spnGenres.getSelectedItem() != null) {
                    String genre = spnGenres.getSelectedItem().toString();
                    dbHelper.updateGenre(genre, etFieldGenre.getText().toString().trim());
                    etFieldGenre.setText("");
                    loadSpinnerGenres(dbHelper.getAllGenres());
                    loadSpinnerSearchGnrS(dbHelper.getAllGenres());
                    loadSpinnerSearchGnrA(dbHelper.getAllGenres());
                }
                else
                    Toast.makeText(MainActivity.this, "Genre isn't selected", Toast.LENGTH_SHORT).show();
            }
        });

        btnSearchSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etFieldSong.getText().toString().isEmpty()) {
                    loadSpinnerSongs(dbHelper.getAllSongs());
                }
                else {
                    loadSpinnerSongs(dbHelper.searchSong(etFieldSong.getText().toString().trim()));
                    etFieldSong.setText("");
                }
            }
        });

        btnSearchArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etFieldArtist.getText().toString().isEmpty()) {
                    loadSpinnerArtists(dbHelper.getAllArtists());
                }
                else {
                    loadSpinnerArtists(dbHelper.searchArtist(etFieldArtist.getText().toString().trim()));
                    etFieldArtist.setText("");
                }
            }
        });

        btnSearchGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etFieldGenre.getText().toString().isEmpty()) {
                    loadSpinnerGenres(dbHelper.getAllGenres());
                }
                else {
                    loadSpinnerGenres(dbHelper.searchGenre(etFieldGenre.getText().toString().trim()));
                    etFieldGenre.setText("");
                }
            }
        });

        btnSearchByArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String artist = spnArtS.getSelectedItem().toString();
                loadSpinnerSongs(dbHelper.searchSongByArtist(artist));
            }
        });

        btnSearchByGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String genre = spnGnrA.getSelectedItem().toString();
                loadSpinnerArtists(dbHelper.searchArtistByGenre(genre));
            }
        });

        btnSearchByGenreS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String genre = spnGnrS.getSelectedItem().toString();
                loadSpinnerSongs(dbHelper.searchSongByGenre(genre));
            }
        });

        btnAddPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String playlist = etFieldPlaylist.getText().toString().trim();
                if(!playlist.isEmpty()) {
                    dbHelper.createPlaylist(playlist, id);
                    loadSpinnerPlaylists(dbHelper.getAllPlaylist(id));
                }
                if(spnPlaylists.getSelectedItem() == null) {
                    loadSpinnerSongsOnPlaylist(null);
                }
                else {
                    loadSpinnerSongsOnPlaylist(dbHelper.getAllSongsOnPlaylist(dbHelper.getIdPlaylist(spnPlaylists.getSelectedItem().toString())));
                }
                etFieldPlaylist.setText("");
            }
        });
        btnRemovePlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String playlist = spnPlaylists.getSelectedItem().toString();
                if(spnPlaylists.getSelectedItem() == null) {
                    loadSpinnerSongsOnPlaylist(null);
                }
                else {
                    dbHelper.deletePlaylist(playlist, id);
                    loadSpinnerPlaylists(dbHelper.getAllPlaylist(id));
                    loadSpinnerSongsOnPlaylist(null);
                }
            }
        });
        btnReplacePlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String playlist = spnPlaylists.getSelectedItem().toString();
                String newPlaylist = etFieldPlaylist.getText().toString().trim();
                if(!playlist.isEmpty() && !newPlaylist.isEmpty()) {
                    dbHelper.updatePlaylist(playlist, newPlaylist);
                    loadSpinnerPlaylists(dbHelper.getAllPlaylist(id));
                    }
                if(spnPlaylists.getSelectedItem() == null) {
                    loadSpinnerSongsOnPlaylist(null);
                }
                else {
                    loadSpinnerSongsOnPlaylist(dbHelper.getAllSongsOnPlaylist(dbHelper.getIdPlaylist(spnPlaylists.getSelectedItem().toString())));
                }
                etFieldPlaylist.setText("");
            }
        });
        btnAddToPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( spnPlaylists.getSelectedItem() == null || spnAllSongs.getSelectedItem() == null ) {
                    loadSpinnerSongsOnPlaylist(null);
                }
                else {
                    String song = spnAllSongs.getSelectedItem().toString();
                    String playlist = spnPlaylists.getSelectedItem().toString();
                    dbHelper.addSongOnPlaylist(song, playlist);
                    loadSpinnerSongsOnPlaylist(dbHelper.getAllSongsOnPlaylist(dbHelper.getIdPlaylist(playlist)));
                }
            }
        });
        btnRemoveToPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spnPlaylists.getSelectedItem() == null || spnAllSongs.getSelectedItem() == null) {
                    loadSpinnerSongsOnPlaylist(null);
                }
                else {
                    String song = spnAllSongs.getSelectedItem().toString();
                    String playlist = spnPlaylists.getSelectedItem().toString();
                    dbHelper.removeSongOnPlaylist(song, playlist);
                    loadSpinnerSongsOnPlaylist(dbHelper.getAllSongsOnPlaylist(dbHelper.getIdPlaylist(playlist)));
                }
            }
        });

        spnPlaylists.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String playlist= parent.getItemAtPosition(position).toString();

                if (playlist.isEmpty()) {
                    loadSpinnerSongsOnPlaylist(null);
                } else {
                    loadSpinnerSongsOnPlaylist(dbHelper.getAllSongsOnPlaylist(dbHelper.getIdPlaylist(playlist)));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                loadSpinnerSongsOnPlaylist(null);
            }
        });

    }

    void loadSpinnerSongs (ArrayList<Song> allS){
        if (allS == null) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnSongs.setAdapter(dataAdapter);
        } else {
            ArrayList<String> sNames = new ArrayList<>();
            for (Song tmpSong : allS) {
                sNames.add(tmpSong.getName());
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sNames);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnSongs.setAdapter(dataAdapter);
        }
    }
    void loadSpinnerGenres (ArrayList<Genre> allG){
        if (allG == null) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnGenres.setAdapter(dataAdapter);
        } else {
            ArrayList<String> gNames = new ArrayList<>();
            for (Genre tmpGenre : allG) {
                gNames.add(tmpGenre.getName());
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gNames);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnGenres.setAdapter(dataAdapter);
        }
    }
    void loadSpinnerArtists (ArrayList<Artist> allA){
        if (allA == null) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnArtists.setAdapter(dataAdapter);
        } else {
            ArrayList<String> aNames = new ArrayList<>();
            for (Artist tmpArtist : allA) {
                aNames.add(tmpArtist.getName());
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, aNames);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnArtists.setAdapter(dataAdapter);
        }
    }

    void loadSpinnerSearchArtS (ArrayList<Artist> allA){
        if (allA == null) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnArtS.setAdapter(dataAdapter);
        } else {
            ArrayList<String> aNames = new ArrayList<>();
            for (Artist tmpArtist : allA) {
                aNames.add(tmpArtist.getName());
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, aNames);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnArtS.setAdapter(dataAdapter);
        }
    }

    void loadSpinnerSearchGnrS (ArrayList<Genre> allG){
        if (allG == null) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnGnrS.setAdapter(dataAdapter);
        } else {
            ArrayList<String> gNames = new ArrayList<>();
            for (Genre tmpGenre : allG) {
                gNames.add(tmpGenre.getName());
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gNames);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnGnrS.setAdapter(dataAdapter);
        }
    }
    void loadSpinnerSearchGnrA (ArrayList<Genre> allG){
        if (allG == null) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnGnrA.setAdapter(dataAdapter);
        } else {
            ArrayList<String> gNames = new ArrayList<>();
            for (Genre tmpGenre : allG) {
                gNames.add(tmpGenre.getName());
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gNames);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnGnrA.setAdapter(dataAdapter);
        }
    }

    void loadSpinnerPlaylists (ArrayList<Playlist> allG){
        if (allG == null) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnPlaylists.setAdapter(dataAdapter);
        } else {
            ArrayList<String> gNames = new ArrayList<>();
            for (Playlist tmpPlaylist : allG) {
                gNames.add(tmpPlaylist.getName());
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gNames);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnPlaylists.setAdapter(dataAdapter);
        }
    }

    void loadSpinnerAllSongs (ArrayList<Song> allG){
        if (allG == null) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnAllSongs.setAdapter(dataAdapter);
        } else {
            ArrayList<String> gNames = new ArrayList<>();
            for (Song tmpSong : allG) {
                gNames.add(tmpSong.getName());
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gNames);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnAllSongs.setAdapter(dataAdapter);
        }
    }

    void loadSpinnerSongsOnPlaylist (ArrayList<Song> allG){
        if (allG == null) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnSongsOnPlaylist.setAdapter(dataAdapter);
        } else {
            ArrayList<String> gNames = new ArrayList<>();
            for (Song tmpSong : allG) {
                gNames.add(tmpSong.getName());
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gNames);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnSongsOnPlaylist.setAdapter(dataAdapter);
        }
    }

    void createTablesAndInitData(){
        dbHelper.createTables();
        if (dbHelper.getAllSongs().isEmpty()) {
            dbHelper.createGenre("Pop Rock");
            dbHelper.createGenre("Hip Hop");
            dbHelper.createGenre("R&B");
            dbHelper.createGenre("Pop");
            dbHelper.createGenre("Traditional Pop");

            dbHelper.createSong("Imagine", "John Lenon", "Pop Rock");
            dbHelper.createSong("Lose Yourself", "Eminem", "Hip Hop");
            dbHelper.createSong("I Will Always Love You", "Whitney Houston", "R&B");
            dbHelper.createSong("Billie Jean", "Michael Jackson", "Pop");
            dbHelper.createSong("Something Stupid", "Frank Sinatra", "Traditional Pop");
        }

        loadSpinnerSongs(dbHelper.getAllSongs());
        loadSpinnerArtists(dbHelper.getAllArtists());
        loadSpinnerGenres(dbHelper.getAllGenres());

        loadSpinnerSearchArtS(dbHelper.getAllArtists());
        loadSpinnerSearchGnrS(dbHelper.getAllGenres());
        loadSpinnerSearchGnrA(dbHelper.getAllGenres());

    }

}
