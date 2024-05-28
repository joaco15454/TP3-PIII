package logica;

import java.util.Set;

	 public class Result {
	        public Set<Integer> clique;
	        public double pesoTotal;
	        public long tiempoTotal;
	        public int nodosEvaluados;

	        Result(Set<Integer> clique, double pesoTotal, long tiempoTotal, int nodosEvaluados) {
	            this.clique = clique;
	            this.pesoTotal = pesoTotal;
	            this.tiempoTotal = tiempoTotal;
	            this.nodosEvaluados = nodosEvaluados;
	        }
	    }

