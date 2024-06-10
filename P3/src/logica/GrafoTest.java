package logica;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GrafoTest {

    private Grafo grafo;

    @Before
    public void setUp() {
        grafo = new Grafo(5); 
    }

    @Test
    public void testAgregarYEliminarArista() {
        
        grafo.agregarArista(0, 1);
        assertTrue(grafo.existeArista(0, 1));

        
        grafo.eliminarArista(0, 1);
        assertFalse(grafo.existeArista(0, 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAgregarAristaConVerticesInvalidos() {
        
        grafo.agregarArista(-1, 2);
    }
    
    @Test
    public void testExisteArista() {
        
        grafo.agregarArista(0, 1);

        
        assertTrue(grafo.existeArista(0, 1));

        
        assertFalse(grafo.existeArista(0, 2));
        assertFalse(grafo.existeArista(1, 2));
        assertFalse(grafo.existeArista(2, 3));
    }
    
    @Test
    public void testObtenerPeso() {
        
        grafo.agregarPeso(0, 2.5);
        assertEquals(2.5, grafo.obtenerPeso(0), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testObtenerPesoConVerticeInvalido() {
        
        grafo.obtenerPeso(10);
    }

    @Test
    public void testVerificarVertice() {
        
        grafo.verificarVertice(3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testVerificarVerticeNegativo() {
        
        grafo.verificarVertice(-2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testVerificarVerticeFueraDeRango() {
        
        grafo.verificarVertice(6);
    }

    @Test
    public void testCantVecinos() {
        
        grafo.agregarArista(0, 1);
        grafo.agregarArista(0, 2);
        assertEquals(2, grafo.cantVecinos(0));
    }

    @Test
    public void testAgregarPeso() {
        
        grafo.agregarPeso(3, 4.7);
        assertEquals(4.7, grafo.obtenerPeso(3), 0.01);
    }


}
