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
public class Arista {
    public String inicio;
    public String fin;
    public int distancia;

    public Arista(String inicio, String fin, int distancia) {
        this.inicio = inicio;
        this.fin = fin;
        this.distancia = distancia;
    }
    
    
}
