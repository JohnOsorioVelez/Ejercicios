package co.edu.uniquindio.visual;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import co.edu.uniquindio.logica.Logica;
import co.edu.uniquindio.persistencia.Archivo;

public class VentanaMapa extends JFrame implements ActionListener, MouseListener
{

	private JPanel panelBotones;
	private JPanel panelMapa;
	private JLabel[][] labels;
	private JButton btnGuardar;
	private JButton btnVolver;
	private int[][] mapa;
	private Logica logica;

	public VentanaMapa()
	{
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
		{
			panelBotones = new JPanel();
			getContentPane().add(panelBotones);
			panelBotones.setBounds(710, 10, 275, 680);
			panelBotones.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.LOWERED));
			panelBotones.setLayout(null);
			logica = new Logica();
			{
				btnGuardar = new JButton();
				panelBotones.add(btnGuardar);
				btnGuardar.setBounds(70, 100, 135, 100);
				btnGuardar.setText("Guardar");
				btnGuardar.addActionListener(this);
			}
			{
				btnVolver = new JButton();
				panelBotones.add(btnVolver);
				btnVolver.setBounds(70, 500, 135, 100);
				btnVolver.setText("Volver");
				btnVolver.addActionListener(this);
			}
		}
		{
			panelMapa = new JPanel();
			mapa = logica.obtenerMapa();
			labels = new JLabel[mapa.length][mapa[0].length];
			panelMapa.setLayout(new GridLayout(mapa.length, mapa[0].length));
			getContentPane().add(panelMapa);
			panelMapa.setBounds(0, 0, 700, 700);
			crearMapa();
		}
		pack();
		this.setSize(1000, 730);
	}

	public void crearMapa()
	{
		for (int i = 0; i < labels.length; i++) 
		{
			for (int j = 0; j < labels[0].length; j++) 
			{
				JLabel label = new JLabel();
				label.addMouseListener(this);
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
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGuardar) {
			if(logica.validarMapa(mapa)){
				logica.salvarMapa(mapa);
				JOptionPane.showMessageDialog(null, "Mapa guardado.");
			}
			else{
				JOptionPane.showMessageDialog(null, "Mapa no valido");
			}
			
		}
		if (e.getSource() == btnVolver) {
			VentanaPrincipal v = new VentanaPrincipal();
			v.setVisible(true);
			setVisible(false);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent evento) {
		JLabel label = (JLabel)evento.getComponent();
		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < labels[0].length; j++) {
				if(label == labels[i][j]){
					if(mapa[i][j] < 4){
						mapa[i][j] += 1;
					}
					else{
						mapa[i][j] = 0;
					}
					label.setName(Integer.toString(mapa[i][j]));
					ponerImagen(label);
				}
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
}
