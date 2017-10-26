package com.example.sergi.cartasmagic;

import android.os.AsyncTask;
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

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

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

        String[] data = {
                "The Monarch",
                "Vampire Token (Black 1/1)",
                "Angel Token (White 4/4)",
                "Wurm Token (Artifact 3/3 Lifelink)",
                "Golem Token (Artifact 3/3)",
                "Elf Warrior Token (Green 1/1)",
                "Bird Token (White 1/1)"
        };

        items = new ArrayList<>(Arrays.asList(data));

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

    private void refresh() {
        RefreshDataTask task = new RefreshDataTask();
        task.execute();
    }

    private class RefreshDataTask extends AsyncTask<Void, Void, ArrayList<Cartas>> {
        @Override
        protected ArrayList<Cartas> doInBackground(Void... voids) {
            MagicAPI api = new MagicAPI();
            ArrayList<Cartas> result = api.get100Cartas();

            Log.d("DEBUG", result.toString());

            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Cartas> cartas) {
            adapter.clear();
            for (Cartas carta : cartas) {
                adapter.add(carta.getNombre());
            }
        }
    }
}
