package com.example.sergi.cartasmagic;

import android.app.Application;
import android.app.ProgressDialog;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergi on 23/11/2017.
 */

public class CartasViewModel extends AndroidViewModel {
    private final Application app;
    private final AppDatabase appDatabase;
    private final CartasDAO cartasDAO;
    private LiveData<List<Cartas>> cartas;
    private static final int PAGES = 10;

    public CartasViewModel(Application application) {
        super(application);

        this.app = application;
        this.appDatabase = AppDatabase.getDatabase(this.getApplication());
        this.cartasDAO = appDatabase.getMovieDao();
    }

    public LiveData<List<Cartas>> getCartas() {
        return cartasDAO.getCartas();
    }

    public void reload() {
        RefreshDataTask task = new RefreshDataTask();
        task.execute();
    }

    private class RefreshDataTask extends AsyncTask<Void, Void, ArrayList<Cartas>> {
        @Override
        protected ArrayList<Cartas> doInBackground(Void... voids) {
            MagicAPI api = new MagicAPI();
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(app.getApplicationContext());
            String color = preferences.getString("Color", " ");
            ArrayList<Cartas> result = null;

            if (!color.equals(" ")) {
                result = api.getColorCartas(color);
            } else {
                result = api.get100Cartas();
            }

            Log.d("DEBUG", result.toString());

            cartasDAO.deleteCartas();
            cartasDAO.addCartas(result);

            return result;
        }

    }
}
