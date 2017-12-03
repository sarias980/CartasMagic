package com.example.sergi.cartasmagic;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sergi.cartasmagic.databinding.LvCartasRowBinding;

import java.util.List;


/**
 * Created by Sergi on 10/11/2017.
 */

public class CartasAdapter extends ArrayAdapter<Cartas> {


    public CartasAdapter(Context context, int resource, List<Cartas> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Obtenim l'objecte en la possició corresponent
        Cartas carta = getItem(position);

        LvCartasRowBinding binding = null;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            binding = DataBindingUtil.inflate(inflater, R.layout.lv_cartas_row, parent, false);
        } else {
            binding = DataBindingUtil.getBinding(convertView);
        }

        binding.name.setText(carta.getNombre());
        binding.habilidades.setText("Habilidades: " + carta.getHabilidades());
        binding.fuerzaDefensa.setText("("+ carta.getFuerza()+ "/" + carta.getDefensa() + ")");
        Glide.with(getContext()).load(carta.getImagenURL()).into(binding.imageCard);

        return binding.getRoot();
    }


}
