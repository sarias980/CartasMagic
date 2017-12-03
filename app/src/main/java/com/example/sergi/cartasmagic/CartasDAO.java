package com.example.sergi.cartasmagic;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Sergi on 01/12/2017.
 */

public interface CartasDAO {
    @Query("select * from movie")
    LiveData<List<Cartas>> getMovies();

    @Insert
    void addMovie(Cartas movie);

    @Delete
    void deleteMovie(Cartas movie);

    @Query("DELETE FROM movie")
    void deleteMovies();


}
