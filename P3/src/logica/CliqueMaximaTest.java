package logica;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class CliqueMaximaTest {

    private Grafo grafo;

    @Before
    public void setUp() {
        // Crear un grafo para las pruebas
        grafo = new Grafo(7);
        // Agregar aristas y pesos de prueba al grafo
        grafo.agregarArista(0, 1);
        grafo.agregarArista(0, 2);
        grafo.agregarArista(1, 2);
        grafo.agregarArista(1, 3);
        grafo.agregarArista(2, 3);
        grafo.agregarArista(3, 4);
        grafo.agregarArista(4, 5);
        grafo.agregarArista(4, 6);
        grafo.agregarPeso(0, 1.5);
        grafo.agregarPeso(1, 2.0);
        grafo.agregarPeso(2, 3.0);
        grafo.agregarPeso(3, 2.5);
        grafo.agregarPeso(4, 4.0);
        grafo.agregarPeso(5, 1.0);
        grafo.agregarPeso(6, 2.0);
    }

    @Test
    public void testEncontrarCliqueMaximaConEstadisticas() {
        // Llamar al método para encontrar la clique máxima con estadísticas
        Result result = cliqueMaxima.encontrarCliqueMaximaConEstadisticas(grafo);

        // Verificar que la clique tenga al menos un nodo
        assertFalse(result.clique.isEmpty());

        // Verificar que el peso total de la clique sea mayor que cero
        assertTrue(result.pesoTotal > 0);

        // Verificar que el tiempo total de ejecución sea mayor que cero
        assertTrue(result.tiempoTotal > 0);

        // Verificar que se hayan evaluado todos los nodos
        assertEquals(grafo.tamanio(), result.nodosEvaluados);
    }

    @Test
    public void testOrdenarVerticesPorPesoAleatorio() {
        // Llamar al método para ordenar los vértices por peso aleatorio
        List<Integer> vertices = cliqueMaxima.ordenarVerticesPorPesoAleatorio(grafo);

        // Verificar que el número de vértices ordenados sea igual al tamaño del grafo
        assertEquals(grafo.tamanio(), vertices.size());

        // Verificar que los vértices estén en orden descendente según sus pesos
        for (int i = 0; i < vertices.size() - 1; i++) {
            assertTrue(grafo.obtenerPeso(vertices.get(i)) >= grafo.obtenerPeso(vertices.get(i + 1)));
        }
    }

    @Test
    public void testConstruirCliqueAleatoria() {
        // Crear una lista de vértices ordenados
        List<Integer> vertices = cliqueMaxima.ordenarVerticesPorPesoAleatorio(grafo);
        // Llamar al método para construir una clique aleatoria
        Set<Integer> clique = cliqueMaxima.construirCliqueAleatoria(grafo, vertices);
        // Verificar que la clique sea un conjunto no vacío
        assertFalse(clique.isEmpty());
        // Verificar que todos los vértices de la clique estén conectados entre sí
        for (int v : clique) {
            for (int u : clique) {
                if (u != v) {
                    assertTrue(grafo.existeArista(u, v));
                }
            }
        }
    }

    @Test
    public void testEsClique() {
        
        Set<Integer> clique = new HashSet<>();
        clique.add(0);
        clique.add(1);
        clique.add(2);
        
        assertTrue(cliqueMaxima.esClique(grafo, clique, 0));
        assertTrue(cliqueMaxima.esClique(grafo, clique, 1));
        assertTrue(cliqueMaxima.esClique(grafo, clique, 2));
       
        clique.add(3);
        assertFalse(cliqueMaxima.esClique(grafo, clique, 3));
    }

    @Test
    public void testCalcularPesoTotal() {
        
        Set<Integer> clique = new HashSet<>();
        clique.add(0);
        clique.add(1);
        clique.add(2);
        double pesoTotal = cliqueMaxima.calcularPesoTotal(grafo, clique);
        
        assertEquals(6.5, pesoTotal, 0.01);
    }
}
