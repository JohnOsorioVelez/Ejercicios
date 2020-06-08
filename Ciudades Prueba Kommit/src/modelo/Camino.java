/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author John Carlos
 */
public class Camino {
    public ArrayList<Integer> ruta;
    public int valor;

    public Camino(ArrayList<Integer> rutas, int valor) {
        this.ruta = rutas;
        this.valor = valor;
    }

    public Camino() {
    }
    
    
    
}
