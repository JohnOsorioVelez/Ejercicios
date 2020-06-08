/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author John Carlos
 */
public class Tuberia {
    public int inicio;
    public int fin;
    public int capacidad;

    public Tuberia(int inicio, int fin, int capacidad) {
        this.inicio = inicio;
        this.fin = fin;
        this.capacidad = capacidad;
    }

    public Tuberia() {
    }
}
