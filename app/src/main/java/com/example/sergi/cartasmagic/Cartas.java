package com.example.sergi.cartasmagic;

/**
 * Created by Sergi on 19/10/2017.
 */

public class Cartas {
    private String nombre;
    private String imagenURL;
    private int fuerza;
    private int defensa;
    private String tipo;
    private String habilidades;
    private String rareza;

    public Cartas(){

    }

    public Cartas(String nombre, String imagenURL, int fuerza, int defensa, String tipo, String habilidades, String rareza, String descripcion) {
        this.nombre = nombre;
        this.imagenURL = imagenURL;
        this.fuerza = fuerza;
        this.defensa = defensa;
        this.tipo = tipo;
        this.habilidades = habilidades;
        this.rareza = rareza;
        this.descripcion = descripcion;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }

    public String getRareza() {
        return rareza;
    }

    public void setRareza(String rareza) {
        this.rareza = rareza;
    }

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
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
                imagenURL + '\n' +
                "(" + fuerza + "/" + defensa + ")\n tipo=" + tipo +
                "\nhabilidades=" + habilidades +
                "\ndescripcion='" + descripcion +
                '}';
    }
}
