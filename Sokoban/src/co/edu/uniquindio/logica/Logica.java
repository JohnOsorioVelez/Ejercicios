package co.edu.uniquindio.logica;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniquindio.modelo.Jugador;
import co.edu.uniquindio.persistencia.Archivo;

public class Logica {

	public final static int CAMINO = 0;
	public final static int AVATAR = 1;
	public final static int MURO = 2;
	public final static int CAJA = 3;
	public final static int PUNTO_DESTINO = 4;
	public final static int CAJA_DESTINO = 5;
	public final static int CAMINADO = 6;
	public final static int CAMINADO_CAJA = 7;
	private static Archivo archivo;
	private static List<int[][]> soluciones;
	
	public static void main(String[] args) {
		Logica logica = new Logica();
		int[][] m = logica.obtenerMapa();
		int[] p = logica.buscarAvatar(m);
		m[p[0]][p[1]] = CAMINO;
		logica.backtracking(m, logica.clonarMapa(m), p[0], p[1], "e");
		
		for (int[][] mapas : soluciones) {
			for (int i = 0; i < mapas.length; i++) {
				for (int j = 0; j < mapas[0].length; j++) {
					System.out.print(mapas[i][j] + ",");
				}
				System.out.println();
			}
			System.out.println();
		}
		System.out.println("fin");
	}
	
	public Logica() {
		archivo = new Archivo();
		soluciones = new ArrayList<>();
	}

	public boolean validarMapa(int[][] mapa){
		int contadorAvatar = 0;
		int contadorCajas = 0;
		int contadorDestinos = 0;
		
		for (int i = 0; i < mapa.length; i++) {
			for (int j = 0; j < mapa[0].length; j++) {
				if(mapa[i][j] == AVATAR){
					contadorAvatar++;
				}
				if(mapa[i][j] == CAJA){
					contadorCajas++;
				}
				if(mapa[i][j] == PUNTO_DESTINO){
					contadorDestinos++;
				}
			}
		}
		
		if(contadorAvatar == 1 && contadorCajas != 0 && contadorCajas == contadorDestinos){
			return true;
		}
		else{
			return false;
		}
	}
	
	public int[][] moverDerecha(int[][] mapa, int[][] mapaOriginal, int i, int j){
		if(j+1<mapa[0].length && (mapa[i][j+1] == CAMINO || mapa[i][j+1] == PUNTO_DESTINO)){
			if(mapaOriginal[i][j] == AVATAR || mapaOriginal[i][j] == CAMINO || mapaOriginal[i][j] == CAJA){
				mapa[i][j] = CAMINO;
				mapa[i][j+1] = AVATAR;
			}
			else if(mapaOriginal[i][j] == PUNTO_DESTINO || mapaOriginal[i][j] == CAJA_DESTINO){
				mapa[i][j] = PUNTO_DESTINO;
				mapa[i][j+1] = AVATAR;
			}
		}
		else if(j+2 < mapa[0].length && (mapa[i][j+1] == CAJA || mapa[i][j+1] == CAJA_DESTINO)
				&& mapa[i][j+2] != MURO){
			if(mapaOriginal[i][j] == AVATAR || mapaOriginal[i][j] == CAMINO || mapaOriginal[i][j] == CAJA){
				if(mapa[i][j+2] != CAJA){
					mapa[i][j] = CAMINO;
					mapa[i][j+1] = AVATAR;
					if(mapa[i][j+2] == PUNTO_DESTINO){
						mapa[i][j+2] = CAJA_DESTINO;
					}
					else{
						mapa[i][j+2] = CAJA;
					}
				}
			}
			else if(mapaOriginal[i][j] == PUNTO_DESTINO || mapaOriginal[i][j] == CAJA_DESTINO){
				mapa[i][j] = PUNTO_DESTINO;
				mapa[i][j+1] = AVATAR;
				mapa[i][j+2] = CAJA;
			}
		}
		return mapa;
	}
	
	public int[][] moverIzquierda(int[][] mapa, int[][] mapaOriginal, int i, int j){
		if(j-1 >= 0 && (mapa[i][j-1] == CAMINO || mapa[i][j-1] == PUNTO_DESTINO)){
			if(mapaOriginal[i][j] == AVATAR || mapaOriginal[i][j] == CAMINO || mapaOriginal[i][j] == CAJA){
				mapa[i][j] = CAMINO;
				mapa[i][j-1] = AVATAR;
			}
			else if(mapaOriginal[i][j] == PUNTO_DESTINO || mapaOriginal[i][j] == CAJA_DESTINO){
				mapa[i][j] = PUNTO_DESTINO;
				mapa[i][j-1] = AVATAR;
			}
		}
		else if(j-2 >= 0 && (mapa[i][j-1] == CAJA || mapa[i][j-1] == CAJA_DESTINO)
				&& mapa[i][j-2] != MURO){
			if(mapaOriginal[i][j] == AVATAR || mapaOriginal[i][j] == CAMINO || mapaOriginal[i][j] == CAJA){
				if(mapa[i][j-2] != CAJA){	
					mapa[i][j] = CAMINO;
					mapa[i][j-1] = AVATAR;
					if(mapa[i][j-2] == PUNTO_DESTINO){
						mapa[i][j-2] = CAJA_DESTINO;
					}
					else{
						mapa[i][j-2] = CAJA;
					}
				}
			}
			else if(mapaOriginal[i][j] == PUNTO_DESTINO || mapaOriginal[i][j] == CAJA_DESTINO){
				mapa[i][j] = PUNTO_DESTINO;
				mapa[i][j-1] = AVATAR;
				mapa[i][j-2] = CAJA;
			}
		}
		return mapa;
	}
	
	public int[][] moverArriba(int[][] mapa, int[][] mapaOriginal, int i, int j){
		if(i-1>=0 && (mapa[i-1][j] == CAMINO || mapa[i-1][j] == PUNTO_DESTINO)){
			if(mapaOriginal[i][j] == AVATAR || mapaOriginal[i][j] == CAMINO || mapaOriginal[i][j] == CAJA){
				mapa[i][j] = CAMINO;
				mapa[i-1][j] = AVATAR;
			}
			else if(mapaOriginal[i][j] == PUNTO_DESTINO || mapaOriginal[i][j] == CAJA_DESTINO){
				mapa[i][j] = PUNTO_DESTINO;
				mapa[i-1][j] = AVATAR;
			}
		}
		else if(i-2>=0 && (mapa[i-1][j] == CAJA || mapa[i-1][j] == CAJA_DESTINO)
				&& mapa[i-2][j] != MURO){
			if(mapaOriginal[i][j] == AVATAR || mapaOriginal[i][j] == CAMINO || mapaOriginal[i][j] == CAJA){
				if(mapa[i-2][j] != CAJA){
					mapa[i][j] = CAMINO;
					mapa[i-1][j] = AVATAR;
					if(mapa[i-2][j] == PUNTO_DESTINO){
						mapa[i-2][j] = CAJA_DESTINO;
					}
					else{
						mapa[i-2][j] = CAJA;
					}
				}	
			}
			else if(mapaOriginal[i][j] == PUNTO_DESTINO || mapaOriginal[i][j] == CAJA_DESTINO){
				mapa[i][j] = PUNTO_DESTINO;
				mapa[i-1][j] = AVATAR;
				mapa[i-2][j] = CAJA;
			}
		}
		return mapa;
	}
	
	public int[][] moverAbajo(int[][] mapa, int[][] mapaOriginal, int i, int j){
		if(i+1 < mapa.length && (mapa[i+1][j] == CAMINO || mapa[i+1][j] == PUNTO_DESTINO)){
			if(mapaOriginal[i][j] == AVATAR || mapaOriginal[i][j] == CAMINO || mapaOriginal[i][j] == CAJA){
				mapa[i][j] = CAMINO;
				mapa[i+1][j] = AVATAR;
			}
			else if(mapaOriginal[i][j] == PUNTO_DESTINO || mapaOriginal[i][j] == CAJA_DESTINO){
				mapa[i][j] = PUNTO_DESTINO;
				mapa[i+1][j] = AVATAR;
			}
		}
		else if(i+2 <mapa.length && (mapa[i+1][j] == CAJA || mapa[i+1][j] == CAJA_DESTINO)
				&& mapa[i+2][j] != MURO){
			if(mapaOriginal[i][j] == AVATAR || mapaOriginal[i][j] == CAMINO || mapaOriginal[i][j] == CAJA){
				if(mapa[i+2][j] != CAJA){	
					mapa[i][j] = CAMINO;
					mapa[i+1][j] = AVATAR;
					if(mapa[i+2][j] == PUNTO_DESTINO){
						mapa[i+2][j] = CAJA_DESTINO;
					}
					else{
						mapa[i+2][j] = CAJA;
					}
				}
			}
			else if(mapaOriginal[i][j] == PUNTO_DESTINO || mapaOriginal[i][j] == CAJA_DESTINO){
				mapa[i][j] = PUNTO_DESTINO;
				mapa[i+1][j] = AVATAR;
				mapa[i+2][j] = CAJA;
			}
		}
		return mapa;
	}
	
	public int[] buscarAvatar(int[][] mapa){
		int[] posiciones = new int[2];
		for (int i = 0; i < mapa.length; i++) {
			for (int j = 0; j < mapa[0].length; j++) {
				if(mapa[i][j] == AVATAR){
					posiciones[0] = i;
					posiciones[1] = j;
					break;
				}
			}
		}
		return posiciones;
	}
	
	public void backtracking(int[][] mapa, int[][] mapaOriginal, int i, int j, String orientacion){
		if(mapa[i][j] == CAJA){
			backtrackingCaja(mapa, i, j, orientacion);
		}
		else{
			if(j+1 < mapa[0].length){
				if(mapa[i][j+1] == CAMINO || mapa[i][j+1] == CAJA){
					mapa[i][j] = CAMINADO;
					backtracking(mapa, mapaOriginal, i, j+1, "e");
				}
			}
			if(i+1 < mapa.length){
				if(mapa[i+1][j] == CAMINO || mapa[i+1][j] == CAJA){
					mapa[i][j] = CAMINADO;
					backtracking(mapa, mapaOriginal, i+1, j, "s");
				}
			}
			if(j-1 >= 0){
				if(mapa[i][j-1] == CAMINO || mapa[i][j-1] == CAJA){
					mapa[i][j] = CAMINADO;
					backtracking(mapa, mapaOriginal, i, j-1, "o");
				}
			}
			if(i-1 >= 0){
				if(mapa[i-1][j] == CAMINO || mapa[i-1][j] == CAJA){
					mapa[i][j] = CAMINADO;
					backtracking(mapa, mapaOriginal, i-1, j, "n");
				}
			}
		}
	}
	
	public void backtrackingCaja(int[][] mapa, int i, int j, String orientacion){
		if(mapa[i][j] == PUNTO_DESTINO){
			mapa[i][j] = CAJA_DESTINO;
			soluciones.add(mapa);
		}
		else{
			if(j+1 < mapa[0].length){
				if(mapa[i][j+1] == CAMINO || mapa[i][j+1] == CAMINADO || mapa[i][j+1] == PUNTO_DESTINO){
					if(movimientoAvatar(orientacion, "e", mapa, i, j)){
						mapa[i][j] = CAMINADO_CAJA;
						backtrackingCaja(mapa, i, j+1, "e");
					}
				}
			}
			if(i+1 < mapa.length){
				if(mapa[i+1][j] == CAMINO || mapa[i+1][j] == CAMINADO || mapa[i+1][j] == PUNTO_DESTINO){
					if(movimientoAvatar(orientacion, "s", mapa, i, j)){
						mapa[i][j] = CAMINADO_CAJA;
						backtrackingCaja(mapa, i+1, j, "s");
					}
				}
			}
			if(j-1 >= 0){
				if(mapa[i][j-1] == CAMINO || mapa[i][j-1] == CAMINADO || mapa[i][j-1] == PUNTO_DESTINO){
					if(movimientoAvatar(orientacion, "o", mapa, i, j)){
						mapa[i][j] = CAMINADO_CAJA;
						backtrackingCaja(mapa, i, j-1, "o");
					}
				}
			}
			if(i-1 >= 0){
				if(mapa[i-1][j] == CAMINO || mapa[i-1][j] == CAMINADO || mapa[i-1][j] == PUNTO_DESTINO){
					if(movimientoAvatar(orientacion, "n", mapa, i, j)){
						mapa[i][j] = CAMINADO_CAJA;
						backtrackingCaja(mapa, i-1, j, "n");
					}
				}
			}
		}
	}
	
	public boolean movimientoAvatar(String orientacion, String cambioOrientacion, int[][] mapa,
			int i, int j){
		if(orientacion.equals(cambioOrientacion)){
			return true;
		}
		else if(orientacion.equals("s")){
			if(cambioOrientacion.equals("e")){
				if(j-1 >= 0 && i-1 >= 0 && (mapa[i-1][j-1] != MURO && mapa[i][j-1] != MURO) 
						&& (mapa[i-1][j-1] != CAJA && mapa[i][j-1] != CAJA)){
					return true;
				}
			}
			else if(cambioOrientacion.equals("o")){
				if(i-1 >= 0 && j+1 < mapa[0].length && 
						(mapa[i-1][j+1] != MURO && mapa[i][j+1] != MURO) 
						&& (mapa[i-1][j+1] != CAJA && mapa[i][j+1] != CAJA)){
					return true;
				}
			}
		}
		else if(orientacion.equals("e")){
			if(cambioOrientacion.equals("s")){
				if(j-1 >= 0 && i-1 >= 0 && 
						(mapa[i-1][j-1] != MURO && mapa[i-1][j] != MURO) 
						&& (mapa[i-1][j-1] != CAJA && mapa[i-1][j] != CAJA)){
					return true;
				}
			}
			else if(cambioOrientacion.equals("n")){
				if(i+1 < mapa.length && j-1 >= 0 && 
						(mapa[i+1][j-1] != MURO && mapa[i+1][j] != MURO) 
						&& (mapa[i+1][j-1] != CAJA && mapa[i+1][j] != CAJA)){
					return true;
				}
			}
		}
		else if(orientacion.equals("n")){
			if(cambioOrientacion.equals("e")){
				if(i+1 < mapa.length && j-1 >= 0 && 
						(mapa[i+1][j-1] != MURO && mapa[i][j-1] != MURO) 
						&& (mapa[i+1][j-1] != CAJA && mapa[i][j-1] != CAJA)){
					return true;
				}
				else if(cambioOrientacion.equals("o")){
					if(i+1 < mapa.length && j+1 < mapa[0].length && 
							(mapa[i+1][j+1] != MURO && mapa[i][j+1] != MURO) 
						&& (mapa[i+1][j+1] != CAJA && mapa[i][j+1] != CAJA)){
						return true;
					}
				}
			}
		}
		else if(orientacion.equals("o")){
			if(cambioOrientacion.equals("s")){
				if(i-1 >= 0 && j+1 < mapa[0].length && 
						(mapa[i-1][j+1] != MURO && mapa[i-1][j] != MURO) 
						&& (mapa[i-1][j+1] != CAJA && mapa[i-1][j] != CAJA)){
					return true;
				}
			}
			else if(cambioOrientacion.equals("n")){
				if(i+1 < mapa.length && j+1 < mapa[0].length && 
						(mapa[i+1][j+1] != MURO && mapa[i+1][j] != MURO) 
						&& (mapa[i+1][j+1] != CAJA && mapa[i+1][j] != CAJA)){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean estaSolucionado(int[][] mapa, int[][] mapaOriginal){
		int contadorDestinos = contarPuntosDestinos(mapaOriginal);
		int contadorCajaDestino = 0;
		for (int i = 0; i < mapa.length; i++) {
			for (int j = 0; j < mapa[0].length; j++) {
				if(mapa[i][j] == CAJA_DESTINO){
					contadorCajaDestino++;
				}
			}
		}
		
		if(contadorCajaDestino == contadorDestinos){
			return true;
		}
		else{
			return false;
		}
	}
	
	public int contarPuntosDestinos(int[][] mapa){
		int contadorDestino = 0;
		for (int i = 0; i < mapa.length; i++) {
			for (int j = 0; j < mapa[0].length; j++) {
				if(mapa[i][j] == PUNTO_DESTINO){
					contadorDestino++;
				}
			}
		}
		return contadorDestino;
	}
	
	public Jugador buscarJugador(String nombre){
		List<Jugador> jugadores = archivo.cargarJugadores();
		for (Jugador jugador : jugadores) {
			if(jugador.getNombre().equals(nombre)){
				return jugador;
			}
		}
		return null;
	}

	public void guardarJugador(Jugador j){
		Jugador jugador = buscarJugador(j.getNombre());
		if (jugador != null) {
			editarJugador(j);
		} 
		else {
			List<Jugador> jugadores = archivo.cargarJugadores();
			jugadores.add(j);
			archivo.guardarJugadores();
		}
	}
	
	public void editarJugador(Jugador j){
		List<Jugador> jugadores = archivo.cargarJugadores();
		for (int i = 0; i < jugadores.size(); i++) {
			Jugador ju = jugadores.get(i);
			if (ju.getNombre().equals(j.getNombre())) {
				jugadores.set(i, j);
			}
		}
		archivo.guardarJugadores();
	}
	
	public int calcularPuntaje(List<int[][]> pasos){
		return 1000000/pasos.size();
	}
	
	public int[][] obtenerMapa(){
		String archivoMapa = archivo.cargarMapa();
		String[] arregloMapa = archivoMapa.split("\n");
		
		String[] tamano = arregloMapa[0].split(",");
		int filas = Integer.parseInt(tamano[0]);
		int columnas = Integer.parseInt(tamano[1]);
		int[][] mapa = new int[filas][columnas];
		
		for (int i = 0; i < filas; i++){
			for (int j = 0; j < columnas; j++) 
			{
				if(arregloMapa[i+1].charAt(j) == '0'){
					mapa[i][j] = CAMINO;
				}
				if(arregloMapa[i+1].charAt(j) == '1'){
					mapa[i][j] = AVATAR;
				}
				if(arregloMapa[i+1].charAt(j) == '2'){
					mapa[i][j] = MURO;
				}
				if(arregloMapa[i+1].charAt(j) == '3'){
					mapa[i][j] = CAJA;
				}
				if(arregloMapa[i+1].charAt(j) == '4'){
					mapa[i][j] = PUNTO_DESTINO;
				}
			}
		}
		return mapa;
	}
	
	public void salvarMapa(int[][] mapa){
		archivo.guardarMapa(mapa);
	}
	
	public int[][] clonarMapa(int[][] mapa){
		int n = mapa.length;
		int m = mapa[0].length;
		int[][] copiaMapa = new int[n][m];
		for (int i = 0; i < mapa.length; i++) {
			for (int j = 0; j < mapa[0].length; j++) {
				copiaMapa[i][j] = mapa[i][j];
			}
		}
		return copiaMapa;
	}
}
