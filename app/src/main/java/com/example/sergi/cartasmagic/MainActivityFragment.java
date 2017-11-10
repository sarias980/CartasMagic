package com.example.sergi.cartasmagic;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ArrayList<Cartas> items;
    private ArrayAdapter<Cartas> adapter;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView lvCartas = (ListView) view.findViewById(R.id.lvCartas);

        items = new ArrayList<>();

        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.lv_cartas_row,
                R.id.tvCarta,
                items
        );
        lvCartas.setAdapter(adapter);

        RefreshDataTask task = new RefreshDataTask();
        task.execute();

        return view;

    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_cartas_fragment, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            refresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }
    private void refresh() {
        RefreshDataTask task = new RefreshDataTask();
        task.execute();
    }

    private class RefreshDataTask extends AsyncTask<Void, Void, ArrayList<Cartas>> {
        @Override
        protected ArrayList<Cartas> doInBackground(Void... voids) {
            MagicAPI api = new MagicAPI();


            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            //String rareza = preferences.getString("Rareza", "All");
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
        protected void onPostExecute(ArrayList<Cartas> cartas) {
            adapter.clear();
            for (Cartas carta : cartas) {
                adapter.add(carta);
            }
        }
    }

}
