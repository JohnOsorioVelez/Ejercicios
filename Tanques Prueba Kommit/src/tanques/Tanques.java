/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanques;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import modelo.Tuberia;

/**
 *
 * @author John Carlos
 */
public class Tanques {
    public static String RUTA_TANQUES = "tanques.txt";
    public ArrayList<Integer> tanques = new ArrayList<>();
    public ArrayList<Tuberia> tuberias = new ArrayList<>();
    public ArrayList<String> nombresTuberia = new ArrayList<>();
    public int estados[];
    public int flujo = 8;
    public int tiempo = 20;
    
    public Tanques(){
        cargar();
        estadoTiempo(0);
    }

    public void cargar(){
        tanques.add(10);
        tanques.add(50);
        tanques.add(30);
        tanques.add(53);
        estados = new int[tanques.size()];
        
        nombresTuberia.add("A");
        nombresTuberia.add("B");
        nombresTuberia.add("C");
        nombresTuberia.add("D");
        
        tuberias.add(new Tuberia(0, 1, 5));
        tuberias.add(new Tuberia(0, 2, 4));
        tuberias.add(new Tuberia(2, 3, 8));  
    }
    
    public void estadoTiempo(int i){
        if(tiempo == i ){
            System.out.println("los estados en el tiempo "+ tiempo +" es:");
            for (int j = 0; j < estados.length; j++) {
                System.out.println(nombresTuberia.get(j) +" = "+ estados[j]);
            }
        }else{
            if(i == 0){
                estados[0] = flujo;
                
            }else{
                pasar(0, flujo);
            }
            estadoTiempo(i+1);
        }
    }
    
    public void pasar(int tanque, int cantidadPasar){
        if(!tieneTuberia(tanque)){
            estados[tanque] += cantidadPasar;
        }else{
            for (Tuberia tuberia : tuberiasDe(tanque)) {
                if(estados[tanque] <= tuberia.capacidad){ //si L en tanque <= que tuberia
                    if( !estaLleno(tuberia.fin, estados[tanque]) ){
                        pasar(tuberia.fin, estados[tanque]);
                        estados[tanque] = 0;
                    }else{
                        int p = tanques.get(tuberia.fin)-estados[tuberia.fin];
                        pasar(tuberia.fin, p);
                        estados[tanque] -= p;
                    }
                    
                }else{
                    if( !estaLleno(tuberia.fin, tuberia.capacidad) ){
                        pasar(tuberia.fin, tuberia.capacidad);
                        estados[tanque] -= tuberia.capacidad;
                    }else{
                        int p = tanques.get(tuberia.fin)-estados[tuberia.fin];
                        pasar(tuberia.fin, p);
                        estados[tanque] -= p;
                    }
                    
                }
            }
            if(!estaLleno(tanque, cantidadPasar)){
                estados[tanque] += cantidadPasar;
            }else{
                estados[tanque] = tanques.get(tanque);
            }
        }
    }
    
    public boolean estaLleno(int tanque, int cantidad){
        if(estados[tanque] == tanques.get(tanque) || (estados[tanque]+cantidad) > tanques.get(tanque)){
            return true;
        }
        return false;
    }
    
    public boolean tieneTuberia(int tanque){
        for (Tuberia tuberia : tuberias) {
            if(tuberia.inicio == tanque){
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Tuberia> tuberiasDe(int tanque){
        ArrayList<Tuberia> t = new ArrayList<>();
        for (Tuberia tuberia : tuberias) {
            if(tuberia.inicio == tanque){
                t.add(tuberia);
            }
        }
        return t;
    }
    
    public static void main(String[] args) {
        Tanques t = new Tanques();
    }
}
