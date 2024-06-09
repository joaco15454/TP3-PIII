package logica;

import java.util.*;

public class cliqueMaxima {
	

    public static void main(String[] args) {
        Grafo grafo = new Grafo(7);
        String archivo = "archivo.json";
        grafo.cargarDesdeJson(archivo);

        Result result = encontrarCliqueMaximaConEstadisticas(grafo);
        
        imprimirClique(result.clique, result.pesoTotal);
        imprimirEstadisticas(result);
    }

    public static Result encontrarCliqueMaximaConEstadisticas(Grafo grafo) {
        long startTime = System.currentTimeMillis();

        List<Integer> vertices = ordenarVerticesPorPesoAleatorio(grafo);

        Set<Integer> clique = construirCliqueAleatoria(grafo, vertices);

        double pesoTotal = calcularPesoTotal(grafo, clique);
        long endTime = System.currentTimeMillis();
        long tiempoTotal = endTime - startTime;

        int nodosEvaluados = vertices.size();

        return new Result(clique, pesoTotal, tiempoTotal, nodosEvaluados);
    }

    private static List<Integer> ordenarVerticesPorPesoAleatorio(Grafo grafo) {
        int n = grafo.tamanio();
        List<Integer> vertices = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vertices.add(i);
        }
        Collections.shuffle(vertices);
        vertices.sort((v1, v2) -> Double.compare(grafo.obtenerPeso(v2), grafo.obtenerPeso(v1)));
        return vertices;
    }

    private static Set<Integer> construirCliqueAleatoria(Grafo grafo, List<Integer> vertices) {
        Set<Integer> clique = new HashSet<>();
        Random rand = new Random();

        for (int v : vertices) {
            if (esClique(grafo, clique, v)) {
                clique.add(v);
                if (rand.nextDouble() < 0.5) {
                    clique.remove(v);
                }
            }
        }
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

    private static double calcularPesoTotal(Grafo grafo, Set<Integer> clique) {
        double pesoTotal = 0;
        for (int v : clique) {
            pesoTotal += grafo.obtenerPeso(v);
        }
        return pesoTotal;
    }

    public static void imprimirClique(Set<Integer> clique, double pesoTotal) {
        System.out.println("Clique de peso máximo: " + clique);
        System.out.println("Peso total de la clique: " + pesoTotal);
    }

    public static void imprimirEstadisticas(Result result) {
        System.out.println("Tiempo total de ejecución: " + result.tiempoTotal + " ms");
        System.out.println("Nodos evaluados: " + result.nodosEvaluados);
    }

    
}