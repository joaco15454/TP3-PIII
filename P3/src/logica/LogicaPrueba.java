package logica;

import java.util.Set;

public class LogicaPrueba {

    public static void main(String[] args) {

        Grafo grafo = new Grafo(7);

        String archivo = "archivo.json"; 
        grafo.cargarDesdeJson(archivo);
//Sysout grafo
        for (int i = 0; i < grafo.tamanio(); i++) {
            double peso = grafo.obtenerPeso(i);
            System.out.println("Vértice: " + i + ", Peso: " + peso);

            Set<Integer> vecinos = grafo.vecinos(i);
            System.out.print("Vecinos: ");
            for (Integer vecino : vecinos) {
                System.out.print(vecino + " ");
            }
        }
    }
}