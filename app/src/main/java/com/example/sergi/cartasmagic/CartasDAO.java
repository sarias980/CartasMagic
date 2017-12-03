package com.example.sergi.cartasmagic;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Sergi on 01/12/2017.
 */

@Dao
public interface CartasDAO {
    @Query("select * from cartas")
    LiveData<List<Cartas>> getCartas();

    @Insert
    void addCarta(Cartas carta);

    @Insert
    void addCartas(List<Cartas> cartas);

    @Delete
    void deleteCarta(Cartas carta);

    @Query("DELETE FROM cartas")
    void deleteCartas();


}
