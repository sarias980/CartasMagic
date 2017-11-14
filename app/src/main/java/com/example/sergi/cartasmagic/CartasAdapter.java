package com.example.sergi.cartasmagic;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

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
        Log.w("XXXX", carta.toString());

        // Mirem a veure si la View s'està reusant, si no es així "inflem" la View
        // https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView#row-view-recycling
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lv_cartas_row, parent, false);
        }

        // Unim el codi en les Views del Layout
        TextView name = convertView.findViewById(R.id.name);
        TextView habilidades = convertView.findViewById(R.id.habilidades);
        TextView fD = convertView.findViewById(R.id.fuerzaDefensa);
        ImageView imagen = convertView.findViewById(R.id.imageCard);

        // Fiquem les dades dels objectes (provinents del JSON) en el layout


        name.setText(carta.getNombre());
        habilidades.setText("Habilidades: " + carta.getHabilidades());
        fD.setText("("+ carta.getFuerza()+ "/" + carta.getDefensa() + ")");
        Glide.with(getContext()).load(carta.getImagenURL()).into(imagen);


        // Retornem la View replena per a mostrarla
        return convertView;
    }


}
