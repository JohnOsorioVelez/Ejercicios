package co.edu.uniquindio.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Jugador implements Serializable{

	private String nombre;
	private int[][] partidaGuardada;
	private List<int[][]> pasos;
	private int puntaje;

	public Jugador(){
		nombre = "";
		partidaGuardada = null;
		pasos = new ArrayList<int[][]>();
		puntaje = 0;
	}
	
	public Jugador(String nombre, int[][] partidaGuardada, List<int[][]> pasos, int puntaje) {
		this.nombre = nombre;
		this.partidaGuardada = partidaGuardada;
		this.pasos = pasos;
		this.puntaje = puntaje;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<int[][]> getPasos() {
		return pasos;
	}

	public void setPasos(List<int[][]> pasos) {
		this.pasos = pasos;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public int[][] getPartidaGuardada() {
		return partidaGuardada;
	}

	public void setPartidaGuardada(int[][] partidaGuardada) {
		this.partidaGuardada = partidaGuardada;
	}
	
}
