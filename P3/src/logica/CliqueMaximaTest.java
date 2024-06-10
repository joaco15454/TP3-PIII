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
      
        grafo = new Grafo(7);
        
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
        
        Result result = cliqueMaxima.encontrarCliqueMaximaConEstadisticas(grafo);

        
        assertFalse(result.clique.isEmpty());
        assertTrue(result.pesoTotal > 0);
        assertTrue(result.tiempoTotal > 0);

        assertEquals(grafo.tamanio(), result.nodosEvaluados);
    }

    @Test
    public void testOrdenarVerticesPorPesoAleatorio() {
        
        List<Integer> vertices = cliqueMaxima.ordenarVerticesPorPesoAleatorio(grafo);
        assertEquals(grafo.tamanio(), vertices.size());

        for (int i = 0; i < vertices.size() - 1; i++) {
            assertTrue(grafo.obtenerPeso(vertices.get(i)) >= grafo.obtenerPeso(vertices.get(i + 1)));
        }
    }

    @Test
    public void testConstruirCliqueAleatoria() {

        List<Integer> vertices = cliqueMaxima.ordenarVerticesPorPesoAleatorio(grafo);
        Set<Integer> clique = cliqueMaxima.construirCliqueAleatoria(grafo, vertices);

        assertFalse(clique.isEmpty());
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
