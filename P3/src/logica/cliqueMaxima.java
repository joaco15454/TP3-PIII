package logica;

import java.util.*;

public class cliqueMaxima {

    public static Set<Integer> encontrarCliqueMaxima(Grafo grafo) {
        int n = grafo.tamanio();
        List<Integer> vertices = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vertices.add(i);
        }
        vertices.sort((v1, v2) -> Double.compare(grafo.obtenerPeso(v2), grafo.obtenerPeso(v1)));

        Set<Integer> clique = new HashSet<>();
        double pesoTotal = 0;

        for (int v : vertices) {
            if (esClique(grafo, clique, v)) {
                clique.add(v);
                pesoTotal += grafo.obtenerPeso(v);
            }
        }

        System.out.println("Clique de peso máximo: " + clique);
        System.out.println("Peso total de la clique: " + pesoTotal);

        return clique;
    }

    private static boolean esClique(Grafo grafo, Set<Integer> clique, int v) {
        for (int u : clique) {
            if (!grafo.existeArista(u, v)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Grafo grafo = new Grafo(7);

        String archivo = "archivo.json"; // Ruta relativa al archivo JSON en la raíz del proyecto
        grafo.cargarDesdeJson(archivo);

        encontrarCliqueMaxima(grafo);
    }
}