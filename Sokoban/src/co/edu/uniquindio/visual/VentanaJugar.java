package co.edu.uniquindio.visual;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import co.edu.uniquindio.logica.Logica;
import co.edu.uniquindio.modelo.Jugador;

public class VentanaJugar extends JFrame implements KeyListener{
	private JPanel panelMapa;
	private JLabel[][] labels;
	private int[][] mapa;
	private int[][] mapaOriginal;
	private Logica logica;
	private Jugador jugador;
	private int iPasos;
	private boolean atras;

	public VentanaJugar(String nombre, boolean cargarPartida)
	{
		logica = new Logica();
		jugador = logica.buscarJugador(nombre);
		iPasos = -1;
		atras = false;
		if(cargarPartida){
			if(jugador == null){
				JOptionPane.showMessageDialog(null, "No se pudo cargar");
				setVisible(false);
				return;
			}
			jugador.getPasos().clear();
			mapa = jugador.getPartidaGuardada();
			mapaOriginal = logica.obtenerMapa();
		}
		else{
			if(jugador == null){
				jugador = new Jugador();
				jugador.setNombre(nombre);
			}
			mapa = logica.obtenerMapa();
			mapaOriginal = logica.clonarMapa(mapa);
			jugador.getPasos().clear();
			int[][] copiaMapa = logica.clonarMapa(mapa);
			jugador.getPasos().add(copiaMapa);
		}
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
		{
			panelMapa = new JPanel();
			
			labels = new JLabel[mapa.length][mapa[0].length];
			panelMapa.setLayout(new GridLayout(mapa.length, mapa[0].length));
			getContentPane().add(panelMapa);
			panelMapa.setBounds(0, 0, 700, 700);
			crearMapa();
		}
		pack();
		this.setSize(700, 730);
		addKeyListener(this);
	}

	public void crearMapa()
	{
		for (int i = 0; i < labels.length; i++) 
		{
			for (int j = 0; j < labels[0].length; j++) 
			{
				JLabel label = new JLabel();
				labels[i][j] = label;
				labels[i][j].setLocation(i*20, j*20);
				labels[i][j].setOpaque(true);
				
				if(mapa[i][j] == 0){
					labels[i][j].setName(Integer.toString(logica.CAMINO));
				}
				if(mapa[i][j] == 1){
					labels[i][j].setName(Integer.toString(logica.AVATAR));
				}
				if(mapa[i][j] == 2){
					labels[i][j].setName(Integer.toString(logica.MURO));
				}
				if(mapa[i][j] == 3){
					labels[i][j].setName(Integer.toString(logica.CAJA));
				}
				if(mapa[i][j] == 4){
					labels[i][j].setName(Integer.toString(logica.PUNTO_DESTINO));
				}
				if(mapa[i][j] == 5){
					labels[i][j].setName(Integer.toString(logica.CAJA_DESTINO));
				}
				
				panelMapa.add(labels[i][j]);
				panelMapa.updateUI();
				ponerImagen(labels[i][j]);
			}
		}
	}
	
	public void ponerImagen(JLabel label)
	{
		int icono = Integer.parseInt(label.getName());
		
		String[] imagenes = { "avatar.png", "muro.png", "caja.png", 
				"puntoDestino.png", "cajaDestino.png"};
		
		if(icono > 0){
			label.setIcon(new ImageIcon(getClass().getClassLoader()
					.getResource("co/edu/uniquindio/imagenes/" + imagenes[icono-1])));
		}
		else{
			label.setIcon(null);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			int[] posiciones = logica.buscarAvatar(mapa);
			int i = posiciones[0];
			int j = posiciones[1];
			if(atras){
				jugador.getPasos().clear();
				iPasos = -1;
				atras = false;
			}
			mapa = logica.moverAbajo(mapa, mapaOriginal, i, j);
			iPasos++;
			int[][] copiaMapa = logica.clonarMapa(mapa);
			jugador.getPasos().add(copiaMapa);
			labels[i][j].setName(Integer.toString(mapa[i][j]));
			ponerImagen(labels[i][j]);
			if(i+1<mapa.length){
				labels[i+1][j].setName(Integer.toString(mapa[i+1][j]));
				ponerImagen(labels[i+1][j]);
			}
			if(i+2<mapa.length){
					labels[i+2][j].setName(Integer.toString(mapa[i+2][j]));
			ponerImagen(labels[i+2][j]);
			}
			if(logica.estaSolucionado(mapa, mapaOriginal))
			{
				int puntaje = logica.calcularPuntaje(jugador.getPasos());
				jugador.setPuntaje(puntaje);
				jugador.setPartidaGuardada(mapa);
				logica.guardarJugador(jugador);
				JOptionPane.showMessageDialog(null, "Has ganado.\nTu puntaje es: " + puntaje);
			}
			System.out.println("abajo");
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP){
			int[] posiciones = logica.buscarAvatar(mapa);
			int i = posiciones[0];
			int j = posiciones[1];
			if(atras){
				jugador.getPasos().clear();
				iPasos = -1;
				atras = false;
			}
			mapa = logica.moverArriba(mapa, mapaOriginal, i, j);
			iPasos++;
			int[][] copiaMapa = logica.clonarMapa(mapa);
			jugador.getPasos().add(copiaMapa);
			labels[i][j].setName(Integer.toString(mapa[i][j]));
			ponerImagen(labels[i][j]);
			if(i-1 >= 0){
				labels[i-1][j].setName(Integer.toString(mapa[i-1][j]));
				ponerImagen(labels[i-1][j]);
			}
			if(i-2 >= 0){
				labels[i-2][j].setName(Integer.toString(mapa[i-2][j]));
				ponerImagen(labels[i-2][j]);
			}
			if(logica.estaSolucionado(mapa, mapaOriginal))
			{
				int puntaje = logica.calcularPuntaje(jugador.getPasos());
				jugador.setPuntaje(puntaje);
				jugador.setPartidaGuardada(mapa);
				logica.guardarJugador(jugador);
				JOptionPane.showMessageDialog(null, "Has ganado.\nTu puntaje es: " + puntaje);
			}
			System.out.println("arriba");
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			int[] posiciones = logica.buscarAvatar(mapa);
			int i = posiciones[0];
			int j = posiciones[1];
			if(atras){
				jugador.getPasos().clear();
				iPasos = -1;
				atras = false;
			}
			mapa = logica.moverIzquierda(mapa, mapaOriginal, i, j);
			iPasos++;
			int[][] copiaMapa = logica.clonarMapa(mapa);
			jugador.getPasos().add(copiaMapa);
			labels[i][j].setName(Integer.toString(mapa[i][j]));
			ponerImagen(labels[i][j]);
			if(j-1 >= 0){
				labels[i][j-1].setName(Integer.toString(mapa[i][j-1]));
				ponerImagen(labels[i][j-1]);
			}
			if(j-2 >= 0){
				labels[i][j-2].setName(Integer.toString(mapa[i][j-2]));
				ponerImagen(labels[i][j-2]);
			}
			if(logica.estaSolucionado(mapa, mapaOriginal))
			{
				int puntaje = logica.calcularPuntaje(jugador.getPasos());
				jugador.setPuntaje(puntaje);
				jugador.setPartidaGuardada(mapa);
				logica.guardarJugador(jugador);
				JOptionPane.showMessageDialog(null, "Has ganado.\nTu puntaje es: " + puntaje);
			}
			System.out.println("izquierda");
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			int[] posiciones = logica.buscarAvatar(mapa);
			int i = posiciones[0];
			int j = posiciones[1];
			if(atras){
				jugador.getPasos().clear();
				iPasos = -1;
				atras = false;
			}
			mapa = logica.moverDerecha(mapa, mapaOriginal, i, j);
			iPasos++;
			int[][] copiaMapa = logica.clonarMapa(mapa);
			jugador.getPasos().add(copiaMapa);
			labels[i][j].setName(Integer.toString(mapa[i][j]));
			ponerImagen(labels[i][j]);
			if(j+1<mapa[0].length){
				labels[i][j+1].setName(Integer.toString(mapa[i][j+1]));
				ponerImagen(labels[i][j+1]);
			}
			if(j+2<mapa[0].length){
				labels[i][j+2].setName(Integer.toString(mapa[i][j+2]));
				ponerImagen(labels[i][j+2]);
			}
			if(logica.estaSolucionado(mapa, mapaOriginal))
			{
				int puntaje = logica.calcularPuntaje(jugador.getPasos());
				jugador.setPuntaje(puntaje);
				jugador.setPartidaGuardada(mapa);
				logica.guardarJugador(jugador);
				JOptionPane.showMessageDialog(null, "Has ganado.\nTu puntaje es: " + puntaje);
			}
			System.out.println("derecha");
		}
		else if(e.getKeyCode() == KeyEvent.VK_G){
			jugador.setPartidaGuardada(mapa);
			logica.guardarJugador(jugador);
			JOptionPane.showMessageDialog(null, "Partida guardada.");
		}
		else if(e.getKeyCode() == KeyEvent.VK_A){
			iPasos--;
			if(iPasos >= 0){
				mapa = jugador.getPasos().get(iPasos);
				panelMapa.removeAll();
				crearMapa();
				atras = true;
			}
			System.out.println("atras");
		}
		else if(e.getKeyCode() == KeyEvent.VK_V){
			setVisible(false);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}
