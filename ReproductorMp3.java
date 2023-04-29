/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.reproductormp3;

import java.io.File;
import java.util.Arrays;

/**
 *
 * @author NERY
 */
public class ReproductorMp3 {
    
    private Musica[] Musica;
    
    public ReproductorMp3(String rutaCarpeta) {
        // Obtener archivos de música en la carpeta
        File dir = new File(rutaCarpeta);
        File[] archivos = dir.listFiles((dir1, name) -> name.endsWith(".mp3"));
        
        // Crear arreglo de Musica y ordenarlos alfabéticamente
        Musica = new Musica[archivos.length];
        for (int i = 0; i < archivos.length; i++) {
            Musica[i] = new Musica(archivos[i].getName());
        }
        Arrays.sort(Musica);
        
    }
    
    public void reproducir() {
        if (Musica.length == 0) {
            System.out.println("No hay Musica para reproducir.");
            return;
        }
        
        // Crear estructura de cola y agregar Musica
        Cola cola = new Cola();
        for (Musica cancion : Musica) {
            cola.encolar(cancion);
        }
        
        // Reproducir Musica en orden de la cola
        while (!cola.estaVacia()) {
            Musica cancion = (Musica) cola.desencolar();
            System.out.println(cancion.getNombre());
        }
    }
    
    public static void main(String[] args) {
        ReproductorMp3 reproductor = new ReproductorMp3("C:\\Users\\NERY\\Desktop\\musica");
        reproductor.reproducir();
    }
}

class Musica implements Comparable<Musica> {
    private String nombre;
    
    public Musica(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    @Override
    public int compareTo(Musica otra) {
        return this.nombre.compareTo(otra.nombre);
    }
}

class Cola {
    private Nodo frente;
    private Nodo fondo;
    
    public void encolar(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (fondo == null) {
            frente = nuevo;
            fondo = nuevo;
        } else {
            fondo.setSiguiente(nuevo);
            fondo = nuevo;
        }
    }
    
    public Object desencolar() {
        if (frente == null) {
            return null;
        } else {
            Object dato = frente.getDato();
            frente = frente.getSiguiente();
            if (frente == null) {
                fondo = null;
            }
            return dato;
        }
    }
    
    public boolean estaVacia() {
        return frente == null;
    }
}

class Nodo {
    private Object dato;
    private Nodo siguiente;
    
    public Nodo(Object dato) {
        this.dato = dato;
        this.siguiente = null;
    }
    
    public Object getDato() {
        return dato;
    }
    
    public void setDato(Object dato) {
        this.dato = dato;
    }
    
    public Nodo getSiguiente() {
        return siguiente;
    }
    
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    } 
}