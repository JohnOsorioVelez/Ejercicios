/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import modelo.Aristas;

/**
 *
 * @author John Carlos
 */
public class Archivo {
    private static String FILE_PATH = "input.txt";
    private int[][] distancias;
    private ArrayList<String> ciudades = new ArrayList<>();
    private int numeroNodos;
    private String inicio;
    private String fin;
    
    public Archivo(){
        ArrayList<Aristas> aristas = cargar();
        distancias = new int[numeroNodos][numeroNodos];
        for (Aristas arista : aristas) {
            int i = ciudades.indexOf(arista.inicio);
            int f = ciudades.indexOf(arista.fin);
            distancias[i][f] = arista.distancia;
            distancias[f][i] = arista.distancia;
        }
        for (int i = 0; i < distancias.length; i++) {
            for (int j = 0; j < distancias.length; j++) {
                System.out.print(distancias[i][j] + ",");
            }
            System.out.print("\n");
        }
    }
    
    public ArrayList<Aristas> cargar(){
        ArrayList<Aristas> aristas = new ArrayList<>();
        try(BufferedReader input = new BufferedReader(new FileReader(FILE_PATH))){
            String t = "";
            while((t = input.readLine()) != null){
                String a[]= t.split(",");
                if(!ciudades.contains(a[0])){
                    ciudades.add(a[0]);
                    numeroNodos++;
                }
                if(!ciudades.contains(a[1])){
                    ciudades.add(a[1]);
                    numeroNodos++;
                }
                if(a.length>2){
                    aristas.add(new Aristas(a[0], a[1], Integer.parseInt(a[2])));
                }else{
                    inicio = a[0];
                    fin = a[1];
                }
            }
        } catch (Exception e) {
        }
        return aristas;
    }
}
