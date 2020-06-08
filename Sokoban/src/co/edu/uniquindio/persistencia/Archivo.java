package co.edu.uniquindio.persistencia;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniquindio.modelo.Jugador;

/**
 * 
 * @author Esteban Echeverry
 * @author John Carlos Osorio Velez
 *
 * clase que permite el manejo de un archivo.
 */
public class Archivo 
{

	/**
	 * String con la ruta del archivo predeterminada.
	 */
	private static final String RUTA_MAPA = "mapa.txt";
	private static final String RUTA_JUGADORES = "jugadores.txt";
	private List<Jugador> jugadores;

	/**
	 * metodo encargado de leer el archivo que contiene el mapa.
	 * @return matriz del mapa en forma de String.
	 * @throws IOException 
	 */
	public String cargarMapa()
	{
		String cadena = "";
		try(BufferedReader entrada = new BufferedReader(new FileReader(RUTA_MAPA))){
			cadena += entrada.readLine() + "\n";
			int filas = Integer.parseInt(cadena.split(",")[0]);
			for (int i = 0; i < filas; i++) {
				cadena += entrada.readLine()+"\n";
			}
		} 
		catch (IOException e) {
			
		}
		return cadena;
	}
	
	public void guardarMapa(int[][] mapa){
		try(PrintWriter salida = new PrintWriter(new FileWriter(RUTA_MAPA))) {
			String cadena = mapa.length + "," + mapa[0].length + "\n";
			for (int i = 0; i < mapa.length; i++) {
				for (int j = 0; j < mapa[0].length; j++) {
					cadena += mapa[i][j];
				}
				cadena += "\n";
			}
			salida.print(cadena);
		} 
		catch (IOException e) {
			
		}
	}
	
	public void guardarJugadores() {
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(RUTA_JUGADORES))){
			salida.writeObject(jugadores);
		} 
		catch (IOException e){
			
		}
	}
	
	public List<Jugador> cargarJugadores() {
		jugadores = new ArrayList<>();
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(RUTA_JUGADORES))){
			jugadores = (List<Jugador>) entrada.readObject();
		} 
		catch (Exception e) {
			
		}
		return jugadores;
	}
}

