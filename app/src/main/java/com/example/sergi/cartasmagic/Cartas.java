package com.example.sergi.cartasmagic;

import java.util.ArrayList;

/**
 * Created by Sergi on 19/10/2017.
 */

public class Cartas {
    private String nombre;
    private int fuerza;
    private int defensa;
    private ArrayList<String> tipo;
    private ArrayList<String> avilidades;
    private String descripcion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public ArrayList<String> getTipo() {
        return tipo;
    }

    public void setTipo(ArrayList<String> tipo) {
        this.tipo = tipo;
    }

    public ArrayList<String> getAvilidades() {
        return avilidades;
    }

    public void setAvilidades(ArrayList<String> avilidades) {
        this.avilidades = avilidades;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Cartas{" +
                "nombre='" + nombre + '\n' +
                "(" + fuerza + "/" + defensa + ")\n tipo=" + tipo +
                "\navilidades=" + avilidades +
                "\ndescripcion='" + descripcion +
                '}';
    }
}
