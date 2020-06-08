/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ciudades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import modelo.Arista;
import modelo.Camino;

/**
 *
 * @author John Carlos
 */
public class Ciudades {
    
    private static String RUTA_ARCHIVO = "input.txt";
    private ArrayList<String> ciudades = new ArrayList<String>();
    private ArrayList<Arista> aristas = new ArrayList<>();
    private ArrayList<Camino> caminos = new ArrayList<>();
    private int numeroCiudades = 0;
    private int inicio;
    private int fin;
    private int matrizDistancias[][];

    public Ciudades(){
        cargar();
        matrizDistancias = new int[numeroCiudades][numeroCiudades];
        for (Arista arista : aristas) {
            int i = ciudades.indexOf(arista.inicio);
            int f = ciudades.indexOf(arista.fin);
            
            matrizDistancias[i][f] = arista.distancia;
            matrizDistancias[f][i] = arista.distancia;
        }
        
        ArrayList<Integer> ruta = new ArrayList<>();
        ruta.add(inicio);
        rutaCorta(inicio, ruta, 0);
        
        for (Integer r : obtenerMenor()) {
            System.out.print(ciudades.get(r) + ", ");
        }
        System.out.println(ciudades.get(fin));
    }
    
    public void cargar(){
        try(BufferedReader entrada = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String texto = "";
            while((texto = entrada.readLine()) != null){
                String a[] = texto.split(",");
                if(!ciudades.contains(a[0])){
                    numeroCiudades++;
                    ciudades.add(a[0]);
                }
                if(!ciudades.contains(a[1])){
                    numeroCiudades++;
                    ciudades.add(a[1]);
                }
                if(a.length<3){
                    inicio = ciudades.indexOf(a[0]);
                    fin = ciudades.indexOf(a[1]);
                }else{
                    aristas.add(new Arista(a[0], a[1], Integer.parseInt(a[2])));
                }
            }
        } catch (Exception e) {
        }
    }
    
    public void rutaCorta(int i, ArrayList<Integer> ruta, int valor){
        for(int j = 0; j < matrizDistancias.length; j++) {
            if(j == fin && matrizDistancias[i][j] != 0){
                Camino c = new Camino(ruta, valor+matrizDistancias[i][j]);
                caminos.add(c);
                break;
            }else if(matrizDistancias[i][j] != 0 && !ruta.contains(j)){
                ArrayList<Integer> r = new ArrayList<>(ruta);
                r.add(j);
                rutaCorta(j, r, valor+matrizDistancias[i][j]);
            }
        }
    }
    
    public ArrayList<Integer> obtenerMenor(){
        int menor = Integer.MAX_VALUE;
        Camino camino = new Camino();
        for (Camino c : caminos) {
            if(c.valor < menor){
                menor = c.valor;
                camino = c;
            }
        }
        return camino.ruta;
    }
    
    public static void main(String[] args) {
        Ciudades ciudades = new Ciudades();
    }
}
