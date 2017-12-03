package com.example.sergi.cartasmagic;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sergi on 20/10/2017.
 */

public class MagicAPI {
    private final String BASE_URL = "https://api.magicthegathering.io/v1";
    //private final int PAGES = 5;

    ArrayList<Cartas> get100Cartas() {
        return doCall("cards", " ");
    }

    ArrayList<Cartas> getColorCartas(String color) {
        return doCall("cards", color);
    }

    private String getUrlPage(String funcion, String filtro) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath(funcion)
                .appendQueryParameter("filtro", filtro)
                .build();
        return builtUri.toString();
    }

    private ArrayList<Cartas> doCall(String funcion, String filtro) {
        ArrayList<Cartas> cartas = new ArrayList<>();

        //for (int i = 0; i < PAGES; i++){
            try{
                String url = getUrlPage(funcion, filtro);
                String JsonResponse = HttpUtils.get(url);
                ArrayList<Cartas> list = processJason(JsonResponse);
                cartas.addAll(list);
            } catch (IOException e) {
                e.printStackTrace();
            }
        //}
        return cartas;
    }

    private ArrayList<Cartas> processJason(String jsonResponse) {
        ArrayList<Cartas> cartas = new ArrayList<>();
        try {
            JSONObject data = new JSONObject(jsonResponse);
            JSONArray jsonCartas = data.getJSONArray("cards");
            for (int i = 0; i < jsonCartas.length(); i++) {
                JSONObject jsonCarta = jsonCartas.getJSONObject(i);

                Cartas carta = new Cartas();
                carta.setNombre(jsonCarta.getString("name"));
                if(jsonCarta.has("imageUrl")){carta.setImagenURL(jsonCarta.getString("imageUrl"));}
                if(jsonCarta.has("text")){carta.setDescripcion(jsonCarta.getString("text"));}
                if(jsonCarta.has("power")){carta.setFuerza(jsonCarta.getInt("power"));}
                if(jsonCarta.has("toughness")){carta.setDefensa(jsonCarta.getInt("toughness"));}
                if(jsonCarta.has("type")){carta.setTipo(jsonCarta.getString("type"));}
                if(jsonCarta.has("supertype")){carta.setRareza(jsonCarta.getString("supertypes"));}
                if(jsonCarta.has("layout")){carta.setHabilidades(jsonCarta.getString("layout"));}

                cartas.add(carta);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cartas;
    }

}
