package com.example.sergi.cartasmagic;

import android.arch.lifecycle.LifecycleFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;


import com.example.sergi.cartasmagic.databinding.FragmentMainBinding;

import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends LifecycleFragment {

    private ArrayList<Cartas> items;
    private CartasAdapter adapter;
    private FragmentMainBinding binding;
    private SharedPreferences preferences;
    private CartasViewModel model;


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
        binding = FragmentMainBinding.inflate(inflater);
        View view = binding.getRoot();

        //ListView lvCartas = (ListView) view.findViewById(R.id.lvCartas);

        items = new ArrayList<>();

        adapter = new CartasAdapter(
                getContext(),
                R.layout.lv_cartas_row,
                items
        );
        binding.lvCartas.setAdapter(adapter);

        //RefreshDataTask task = new RefreshDataTask();
        //task.execute();

        binding.lvCartas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

              Cartas carta = (Cartas) adapterView.getItemAtPosition(i);

              Log.d("CARTA ANTES", carta.toString());

              Intent intent = new Intent(getContext(), DetailActivity.class);
              intent.putExtra("carta", carta);

              startActivity(intent);
            }
        });

        model = ViewModelProviders.of(this).get(CartasViewModel.class);
        model.getCartas().observe(this, new Observer<List<Cartas>>() {
            @Override
            public void onChanged(@Nullable List<Cartas> cartas) {
                adapter.clear();
                adapter.addAll(cartas);
            }
        });


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
    }
    private void refresh() {
        model.reload();
    }

}
