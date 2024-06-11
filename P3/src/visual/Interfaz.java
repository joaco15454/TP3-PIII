package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Interfaz {

	private JFrame frame;
	private JButton btnSalir;
	private JButton btnSiguiente;
	private JButton btnComenzar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	
	private void inicialiarComponenetes() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 192, 203));
		frame.setBounds(100, 100, 836, 447);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenidos al problema de la clique maxima!");
		lblNewLabel.setFont(new Font("Modern No. 20", Font.PLAIN, 26));
		lblNewLabel.setBounds(142, 52, 590, 70);
		frame.getContentPane().add(lblNewLabel);
		
		btnComenzar = new JButton("Comenzar");
		btnComenzar.setBackground(new Color(255, 255, 255));
		btnComenzar.setBounds(544, 192, 239, 23);
		frame.getContentPane().add(btnComenzar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBackground(new Color(255, 255, 255));
		btnSalir.setBounds(284, 362, 216, 23);
		frame.getContentPane().add(btnSalir);
		
		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBackground(new Color(255, 255, 240));
		btnSiguiente.setBounds(544, 273, 239, 23);
		frame.getContentPane().add(btnSiguiente);
		
		JLabel lblSiQuiereUtilizar = new JLabel("Si quiere utilizar el grafo ya cargado en sistema:");
		lblSiQuiereUtilizar.setFont(new Font("Modern No. 20", Font.PLAIN, 26));
		lblSiQuiereUtilizar.setBounds(10, 165, 500, 70);
		frame.getContentPane().add(lblSiQuiereUtilizar);
		
		JLabel lblSiQuiereCargar = new JLabel("Si quiere cargar un nuevo grafo:\r\n");
		lblSiQuiereCargar.setFont(new Font("Modern No. 20", Font.PLAIN, 26));
		lblSiQuiereCargar.setBounds(10, 246, 641, 70);
		frame.getContentPane().add(lblSiQuiereCargar);
	}
	private void EscucharSalir() {
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}
	
	private void EscucharSiguiente() {
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				IngresarManualmente.main(null);
			}
		});
	}
	
	private void EscucharComenzar() {
		btnComenzar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Resultados.main(null);
			}
		});
	}
	
	public Interfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		inicialiarComponenetes();
		EscucharSalir();
		EscucharSiguiente(); 
		EscucharComenzar();
	}
}
