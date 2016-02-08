package br.com.gustavodiniz.walmart.domain;

import java.util.HashMap;
import java.util.Map;

public class Grafo {

	private Map<String, Vertice> grafo; 
	
	/** Builds a graph from a set of edges */
	   public Grafo(Aresta[] arestas) {
		   grafo = new HashMap<>(arestas.length);
	 
	      for (Aresta e : arestas) {
	         if (!grafo.containsKey(e.v1)) grafo.put(e.v1, new Vertice(e.v1));
	         if (!grafo.containsKey(e.v2)) grafo.put(e.v2, new Vertice(e.v2));
	      }
	 
	      //another pass to set neighbouring vertices
	      for (Aresta e : arestas) {
	         grafo.get(e.v1).neighbours.put(grafo.get(e.v2), e.dist);
	         //graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); // also do this for an undirected graph
	      }
	   }

	public Map<String, Vertice> getGrafo() {
		return grafo;
	}

	public void setGrafo(Map<String, Vertice> grafo) {
		this.grafo = grafo;
	}
	   
	   
	 
}
