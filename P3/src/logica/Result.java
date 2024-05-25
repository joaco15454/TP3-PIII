package logica;

import java.util.Set;

	 class Result {
	        Set<Integer> clique;
	        double pesoTotal;
	        long tiempoTotal;
	        int nodosEvaluados;

	        Result(Set<Integer> clique, double pesoTotal, long tiempoTotal, int nodosEvaluados) {
	            this.clique = clique;
	            this.pesoTotal = pesoTotal;
	            this.tiempoTotal = tiempoTotal;
	            this.nodosEvaluados = nodosEvaluados;
	        }
	    }

