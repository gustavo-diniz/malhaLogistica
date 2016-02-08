package br.com.gustavodiniz.walmart.service.impl;

import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gustavodiniz.walmart.domain.Rotas;
import br.com.gustavodiniz.walmart.domain.Vertice;
import br.com.gustavodiniz.walmart.service.CalculoRotasService;


@Service
@Transactional
public class CalculoRotasServiceImpl implements CalculoRotasService {

	@Override
	public Map<String, Vertice> calcularPossibilidadesDeRota(String startName, Map<String, Vertice> grafo) {
	      if (!grafo.containsKey(startName)) {
	         System.err.printf("Grafo a posição inicial: ", startName);
	         return null;
	      }
	      
	      final Vertice source = grafo.get(startName);
	      NavigableSet<Vertice> q = new TreeSet<>();
	 
	      // Configura as vertices
	      for (Vertice v : grafo.values()) {
	         v.previous = v == source ? source : null;
	         v.dist = v == source ? 0 : Integer.MAX_VALUE;
	         q.add(v);
	      }
	 
	      verificaVerticesMaisProximos(q);
	      
	      return grafo;
	   }
	   
	   private void verificaVerticesMaisProximos(final NavigableSet<Vertice> q) {      
	      Vertice u, v;
	      while (!q.isEmpty()) {
	 
	         u = q.pollFirst(); 
	         if (u.dist == Integer.MAX_VALUE) break; 
	 
	         for (Map.Entry<Vertice, Integer> a : u.neighbours.entrySet()) {
	            v = a.getKey(); //o vertica mais proximo nesta iteração
	 
	            final int alternateDist = u.dist + a.getValue();
	            if (alternateDist < v.dist) { //vertice com caminho mais curto encontrado 
	               q.remove(v);
	               v.dist = alternateDist;
	               v.previous = u;
	               q.add(v);
	            } 
	         }
	      }
	   }
	   
	   /** Imprime um caminho a partir da fonte para o vértice especificado */
	   public Rotas calculaRota(String endName, Double autonomiaVeiculo, Double valLitroCombustivel, Map<String, Vertice> grafo) {
	      if (!grafo.containsKey(endName)) {
	         System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endName);
	         return null;
	      }
	 
	      Rotas r = new Rotas();
	      r.setMenorCaminho("");
	      
	      grafo.get(endName).calculaDistancia(r);
	      r.setCustoRota(calculaAutonomia(r.getDistanciaTotal(), autonomiaVeiculo, valLitroCombustivel));
	      return r;
	   }
	   
	   public Double calculaAutonomia(Integer distancia, Double autonomia, Double valLitro){
		   
		   if(distancia == null || distancia == 0){
			   return 0d;
		   }
		   
		   if(autonomia == null || autonomia == 0){
			   return 0d;
		   }
		   
		   if(valLitro == null){
			   valLitro = 0d;
		   }
		   
		   return (distancia/autonomia) * valLitro;
	   }
	   

}
