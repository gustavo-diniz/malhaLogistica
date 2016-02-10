package br.com.gustavodiniz.walmart.service.impl;

import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gustavodiniz.walmart.domain.Rotas;
import br.com.gustavodiniz.walmart.domain.Vertice;
import br.com.gustavodiniz.walmart.service.CalculoRotasService;

/**
 * Classe de implementação da interface CalculoRotasService
 * @author gustavodinizdossantos
 *
 */
@Service
@Transactional
public class CalculoRotasServiceImpl implements CalculoRotasService {

	@Override
	public Map<String, Vertice> calcularPossibilidadesDeRota(String origem, Map<String, Vertice> grafo) {
	      if (!grafo.containsKey(origem)) {
	         return null;
	      }
	      
	      final Vertice verticeOrigem = grafo.get(origem);
	      NavigableSet<Vertice> q = new TreeSet<>();
	 
	      // Configura as vertices
	      for (Vertice vertice : grafo.values()) {
	    	 vertice.anterior = vertice == verticeOrigem ? verticeOrigem : null;
	    	 vertice.distanciaMaxima = vertice == verticeOrigem ? 0 : Integer.MAX_VALUE;
	         q.add(vertice);
	      }
	 
	      verificaVerticesMaisProximos(q);
	      
	      return grafo;
	   }
	   
	   /**
	    * Avalia os vertices mais próximos e adiciona na árvore de vértices.
	    * @param q
	    */
	   private void verificaVerticesMaisProximos(final NavigableSet<Vertice> arvoreVertices) {      
	      Vertice ultimoVertice;
	      Vertice verticeProximo;
	      
	      while (!arvoreVertices.isEmpty()) {
	 
	    	 ultimoVertice = arvoreVertices.pollFirst(); 
	         if (ultimoVertice.distanciaMaxima == Integer.MAX_VALUE) break; 
	 
	         for (Map.Entry<Vertice, Integer> a : ultimoVertice.verticesProximas.entrySet()) {
	        	
	        	//o vertica mais proximo nesta iteração
	        	verticeProximo = a.getKey(); 
	 
	            final int alternateDist = ultimoVertice.distanciaMaxima + a.getValue();
	            
	            //vertice com caminho mais curto encontrado 
	            if (alternateDist < verticeProximo.distanciaMaxima) { 
	               arvoreVertices.remove(verticeProximo);
	               verticeProximo.distanciaMaxima = alternateDist;
	               verticeProximo.anterior = ultimoVertice;
	               arvoreVertices.add(verticeProximo);
	            } 
	         }
	      }
	   }
	   
	   public Rotas calculaRotaComMenorCaminho(String endName, Double autonomiaVeiculo, Double valLitroCombustivel, Map<String, Vertice> grafo) {
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
	   
	   /**
	    * Efetua o cálculo de custo da rota a partir da distância calculado, de acordo com
	    * a autonomia e valor de combustível.
	    * @param distancia
	    * @param autonomia
	    * @param valLitro
	    * @return
	    */
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
