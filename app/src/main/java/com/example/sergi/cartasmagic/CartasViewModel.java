package com.example.sergi.cartasmagic;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergi on 23/11/2017.
 */

class CartasViewModel extends AndroidViewModel {
    private final Application app;
    private MutableLiveData<List<Cartas>> cartas;

    public CartasViewModel(Application application) {
        super(application);

        this.app = application;
    }

    public LiveData<List<Cartas>> getCartas() {
        Log.d("DEBUG", "ENTRA");

        if (cartas == null) {
            cartas = new MutableLiveData<>();
            reload();
        }
        return cartas;
    }

    private void reload() {
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

            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Cartas> results) {
            cartas.postValue(results);
        }
    }
}
