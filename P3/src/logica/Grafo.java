package logica;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Grafo {

    private int[][] grafo;
    private Map<Integer, Double> pesos;

    public Grafo(int vertices) {
        grafo = new int[vertices][vertices];
        pesos = new HashMap<>();
    }

    public void agregarArista(int i, int j) {
        verificarVertice(i);
        verificarVertice(j);
        verificarDistintos(i, j);

        grafo[i][j] = 1;
        grafo[j][i] = 1;
    }

    public void eliminarArista(int i, int j) {
        verificarVertice(i);
        verificarVertice(j);
        verificarDistintos(i, j);

        grafo[i][j] = 0;
        grafo[j][i] = 0;
    }

    public boolean existeArista(int i, int j) {
        verificarVertice(i);
        verificarVertice(j);
        verificarDistintos(i, j);

        return grafo[i][j] != 0;
    }

    public int tamanio() {
        return grafo.length;
    }

    public Set<Integer> vecinos(int i) {
        verificarVertice(i);

        Set<Integer> ret = new HashSet<>();
        for (int j = 0; j < this.tamanio(); ++j) if (i != j) {
            if (this.existeArista(i, j))
                ret.add(j);
        }

        return ret;
    }

    public int retornarArista(int x, int y) {
        return grafo[x][y];
    }

    public int cantVecinos(int i) {
        int cantidadDeVecinos = 0;
        for (int j = 0; j < this.tamanio(); ++j) if (i != j) {
            if (this.existeArista(i, j))
                cantidadDeVecinos++;
        }

        return cantidadDeVecinos;
    }

    protected void verificarVertice(int i) {
        if (i < 0)
            throw new IllegalArgumentException("El vertice no puede ser negativo: " + i);

        if (i >= grafo.length)
            throw new IllegalArgumentException("Los vertices deben estar entre 0 y |V|-1: " + i);
    }

    private void verificarDistintos(int i, int j) {
        if (i == j)
            throw new IllegalArgumentException("No se permiten loops: (" + i + ", " + j + ")");
    }

    public void agregarPeso(int vertice, double peso) {
        verificarVertice(vertice);
        pesos.put(vertice, peso);
    }

    public double obtenerPeso(int vertice) {
        verificarVertice(vertice);
        return pesos.getOrDefault(vertice, 0.0);
    }

    public void cargarDesdeJson(String archivo) {
        try (FileReader reader = new FileReader(archivo)) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject objeto = new JSONObject(tokener);

            JSONArray vertices = objeto.getJSONArray("vertices");
            for (int i = 0; i < vertices.length(); i++) {
                JSONObject vertice = vertices.getJSONObject(i);
                int id = vertice.getInt("id");
                double peso = vertice.getDouble("peso");
                agregarPeso(id, peso);
            }

            JSONArray arcos = objeto.getJSONArray("arcos");
            for (int i = 0; i < arcos.length(); i++) {
            	System.out.println("Llegue aca");
                JSONObject arco = arcos.getJSONObject(i);
                int v1 = arco.getInt("v1");
                int v2 = arco.getInt("v2");

                agregarArista(v1, v2);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}