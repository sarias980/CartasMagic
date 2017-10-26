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

    ArrayList<Cartas> get100Cartas() {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("cards")
                .build();
        String url = builtUri.toString();

        return doCall(url);
    }

    private ArrayList<Cartas> doCall(String url) {
        try {
            String JsonResponse = HttpUtils.get(url);
            return processJason(JsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Cartas> processJason(String jsonResponse) {
        ArrayList<Cartas> cartas = new ArrayList<>();
        try {
            JSONObject data = new JSONObject(jsonResponse);
            JSONArray jsonCartas = data.getJSONArray("results");
            for (int i = 0; i < jsonCartas.length(); i++) {
                JSONObject jsonCarta = jsonCartas.getJSONObject(i);

                Cartas carta = new Cartas();
                carta.setNombre(jsonCarta.getString("name"));
                carta.setImagenURL(jsonCarta.getString("imageUrl"));
                carta.setDescripcion(jsonCarta.getString("text"));
                carta.setFuerza(jsonCarta.getInt("power"));
                carta.setDefensa(jsonCarta.getInt("toughness"));
                carta.setTipo(jsonCarta.getString("type"));
                carta.setRareza(jsonCarta.getString("supertypes"));
                carta.setAvilidades(jsonCarta.getString("layout"));

                cartas.add(carta);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cartas;
    }

}
