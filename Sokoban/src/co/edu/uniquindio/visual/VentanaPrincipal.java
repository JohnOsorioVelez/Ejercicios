package co.edu.uniquindio.visual;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class VentanaPrincipal extends JFrame implements ActionListener{

	
	private JButton btnNuevaPartida;
	private JButton btnCargarPartida;
	private JButton btnModificarMapa;
	private JButton btnNuevoMapa;

	public VentanaPrincipal()
	{
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
		{
			btnNuevaPartida = new JButton();
			add(btnNuevaPartida);
			btnNuevaPartida.setBounds(20, 20, 150, 60);
			btnNuevaPartida.setText("Nueva Partida");
			btnNuevaPartida.addActionListener(this);
		}
		{
			btnCargarPartida = new JButton();
			add(btnCargarPartida);
			btnCargarPartida.setBounds(20, 115, 150, 60);
			btnCargarPartida.setText("Cargar Partida");
			btnCargarPartida.addActionListener(this);
		}
		{
			btnModificarMapa = new JButton();
			add(btnModificarMapa);
			btnModificarMapa.setBounds(200, 115, 150, 60);
			btnModificarMapa.setText("Modificar Mapa");
			btnModificarMapa.addActionListener(this);
		}
		{
			btnNuevoMapa = new JButton();
			add(btnNuevoMapa);
			btnNuevoMapa.setBounds(200, 20, 150, 60);
			btnNuevoMapa.setText("Nuevo Mapa");
			btnNuevoMapa.addActionListener(this);
		}
		pack();
		this.setSize(380, 230);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNuevaPartida) {
			String nombre = JOptionPane.showInputDialog("Ingrese nombre de jugador.");
			if(nombre == null || nombre.equals("")){
				JOptionPane.showMessageDialog(null, "Debe ingresar nombre de jugador.");
			}
			else{
				VentanaJugar v = new VentanaJugar(nombre, false);
				v.setVisible(true);
			}
		}
		if (e.getSource() == btnCargarPartida) {
			String nombre = JOptionPane.showInputDialog("Ingrese nombre de jugador.");
			if(nombre == null || nombre.equals("")){
				JOptionPane.showMessageDialog(null, "Debe ingresar nombre de jugador.");
			}
			else{
				VentanaJugar v = new VentanaJugar(nombre, true);
				v.setVisible(true);
			}
		}
		if (e.getSource() == btnModificarMapa) {
			VentanaMapa v = new VentanaMapa();
			v.setVisible(true);
		}
		if (e.getSource() == btnNuevoMapa) {
			
		}
	}
}
