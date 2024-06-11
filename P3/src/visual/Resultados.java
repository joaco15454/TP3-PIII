package visual;
import java.awt.EventQueue;
import java.awt.Polygon;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.MapRectangleImpl;

import logica.Grafo;
import logica.cliqueMaxima;
import logica.Result;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.awt.List;
import java.awt.Choice;
import java.awt.TextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.TextArea;

public class Resultados 
{
    String archivo = "archivo.json";
	Grafo grafo= new Grafo(7);
	
	private JFrame frame;
	private JPanel panelMapa;
	private JPanel panelControles;
	private JMapViewer _mapa;
	private ArrayList< Coordinate > _lasCoordenadas= new ArrayList<>();
	private JLabel lblNewLabel;
	private TextArea Resultados;
	private String cadena="";
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try {
					Resultados window = new Resultados();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void inicilizarComponenetes() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1376, 744);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	    frame.setExtendedState(frame.MAXIMIZED_BOTH);
		panelMapa = new JPanel();
		panelMapa.setBounds(10, 11, 521, 679);
		frame.getContentPane().add(panelMapa);
		
		panelControles = new JPanel();
		panelControles.setBounds(551, 33, 709, 446);
		frame.getContentPane().add(panelControles);
		panelControles.setLayout(null);
		
		lblNewLabel = new JLabel("Para hacer zoom (doble click)");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 34, 662, 56);
		
		panelControles.add(lblNewLabel);
		
		JLabel lblParaMoverseEne = new JLabel("Para moverse en la pantalla (click derecho) \r\n");
		lblParaMoverseEne.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblParaMoverseEne.setBounds(10, 85, 662, 56);
		panelControles.add(lblParaMoverseEne);
		
		JLabel lblNewLabel_1_1 = new JLabel("Para volver a la posicion original (scroll del mouse)");
		lblNewLabel_1_1.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(10, 131, 662, 56);
		panelControles.add(lblNewLabel_1_1);
		
		Resultados = new TextArea();
		Resultados.setBounds(26, 205, 646, 212);
		panelControles.add(Resultados);
		
		
		_mapa = new JMapViewer();
		_mapa.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				_mapa.setDisplayPosition(new Coordinate(-45.972389, -114.275266),5);
			}
		});
		_mapa.setBounds(10, 11, 501, 669);
		_mapa.setDisplayPosition(new Coordinate(-45.972389, -114.275266),5);
		panelMapa.setLayout(null);
		panelMapa.setEnabled(false);
		panelMapa.add(_mapa);
		_mapa.setZoomContolsVisible(false);
		
		grafo.cargarDesdeJson(archivo);
	


	}
	
	private void DibujarPuntos() {
		for (int i=0; i<= grafo.tamanio()-1;i++ ) {
			double cor1= (double) (Math.floor(Math.random()*(-45.972389-(-50.910234)+1)+(-50.910234)));
			double cor2= (double) (Math.floor(Math.random()*(-127.376461-(-114.275266)+1)+(-114.275266)));
			 int nodo = i;    
			 String nodonombre= String.valueOf(nodo);
			Coordinate corde = new Coordinate( cor1, cor2);
			_lasCoordenadas.add(corde);
			_mapa.addMapMarker(new MapMarkerDot(nodonombre, corde));

		}
		
		
	} 
	
	private void Dibujararistas() {
		for (int i=0; i<= grafo.tamanio()-1;i++ ) {	
				Set<Integer> vecinos = new HashSet<>();	
				vecinos=grafo.vecinos(i);
				for(Integer ret: vecinos) {
						_mapa.addMapPolygon(new MapPolygonImpl(_lasCoordenadas.get(i),_lasCoordenadas.get(ret),_lasCoordenadas.get(ret)));
				}
		}
	
	
	}
	

	private void MostrarResultados() {
		  Result result = cliqueMaxima.encontrarCliqueMaximaConEstadisticas(grafo);
		  cliqueMaxima.imprimirClique(result.clique, result.pesoTotal);
		  cliqueMaxima. imprimirEstadisticas(result);
		  	cadena += "Clique de peso máximo:" ;
			  cadena += result.clique.toString();
			  cadena += "\n";
			  cadena += "Peso Total de la clique:" ;
			  cadena += result.pesoTotal;
	          cadena += "\n";
			  cadena += "Tiempo Total:" ;
			  cadena += result.tiempoTotal;
	          cadena += "\n";
	          cadena += "Catidad de nodos evaluados:" ;
			  cadena += result.nodosEvaluados;
	          cadena += "\n";
		  Resultados.setText(cadena);
	    }	    





	


	
	/**
	 * Create the application.
	 */
	public Resultados() 
	{
		initialize();
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		inicilizarComponenetes();
		DibujarPuntos();
		Dibujararistas();
		MostrarResultados();
		
	
	}
}
		
	
