package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import logica.Grafo;
import logica.cliqueMaxima;
import logica.Result;

public class IngresarManualmente {

    private JFrame frame;
    private JPanel panel;
    private JTextField nodoField;
    private Grafo grafo;
    private List<String> nodos;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    IngresarManualmente window = new IngresarManualmente();
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
    public IngresarManualmente() {
        nodos = new ArrayList<>();
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        nodoField = new JTextField();
        nodoField.setColumns(10);
        panel.add(nodoField);

        JButton btnAgregarNodo = new JButton("Agregar Nodo");
        btnAgregarNodo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarNodo();
            }
        });
        panel.add(btnAgregarNodo);

        JButton btnFinalizar = new JButton("Finalizar");
        btnFinalizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                finalizarIngresoNodos();
            }
        });
        panel.add(btnFinalizar);
    }

    private void agregarNodo() {
        String nodo = nodoField.getText();
        if (nodo.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "El campo de nodo no puede estar vacio");
            return;
        }
        nodos.add(nodo);
        JOptionPane.showMessageDialog(frame, "Nodo " + nodo + " agregado");
        nodoField.setText("");
    }

    private void finalizarIngresoNodos() {
        if (nodos.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Debe agregar al menos un nodo");
            return;
        }

        grafo = new Grafo(nodos.size());
        for (int i = 0; i < nodos.size(); i++) {
            String inputPeso = JOptionPane.showInputDialog(frame, "Ingrese el peso del nodo " + nodos.get(i) + ":");
            if (inputPeso == null) return;  // Cancelar entrada
            double peso = Double.parseDouble(inputPeso);
            grafo.agregarPeso(i, peso);
        }

        for (int i = 0; i < nodos.size(); i++) {
            for (int j = i + 1; j < nodos.size(); j++) {
                String input = JOptionPane.showInputDialog(frame,
                        "¿Existe una arista entre el nodo " + nodos.get(i) + " y el nodo " + nodos.get(j) + "? (si/no):");
                if ("si".equalsIgnoreCase(input)) {
                    grafo.agregarArista(i, j);
                }
            }
        }

        Result result = cliqueMaxima.encontrarCliqueMaximaConEstadisticas(grafo);

        JOptionPane.showMessageDialog(frame,
                "Clique de peso máximo: " + result.clique + "\n" +
                        "Peso total de la clique: " + result.pesoTotal + "\n" +
                        "Tiempo total de ejecución: " + result.tiempoTotal + " ms\n" +
                        "Nodos evaluados: " + result.nodosEvaluados,
                "Resultados de Clique Máxima",
                JOptionPane.INFORMATION_MESSAGE);
    }
}