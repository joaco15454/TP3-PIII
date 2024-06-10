package logica;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.HashSet;
import java.util.Set;

public class ResultTest {

    private Result result;

    @Before
    public void setUp() {
        Set<Integer> clique = new HashSet<>();
        clique.add(1);
        clique.add(2);
        result = new Result(clique, 10.5, 1000, 5);
    }

    @Test
    public void testConstructorAndGetters() {
        Set<Integer> clique = result.clique;
        double pesoTotal = result.pesoTotal;
        long tiempoTotal = result.tiempoTotal;
        int nodosEvaluados = result.nodosEvaluados;

        assertEquals(2, clique.size());
        assertTrue(clique.contains(1));
        assertTrue(clique.contains(2));
        assertEquals(10.5, pesoTotal, 0.01);
        assertEquals(1000, tiempoTotal);
        assertEquals(5, nodosEvaluados);
    }

    @Test
    public void testSetters() {

        Set<Integer> newClique = new HashSet<>();
        newClique.add(3);
        newClique.add(4);
        result.clique = newClique;
        result.pesoTotal = 15.2;
        result.tiempoTotal = 2000;
        result.nodosEvaluados = 8;

        assertEquals(2, result.clique.size()); 
        assertTrue(result.clique.contains(3));
        assertTrue(result.clique.contains(4));
        assertEquals(15.2, result.pesoTotal, 0.01);
        assertEquals(2000, result.tiempoTotal);
        assertEquals(8, result.nodosEvaluados);
    }
}
