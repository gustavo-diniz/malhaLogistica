package br.com.gustavodiniz.walmart.service;

import java.util.Map;

import br.com.gustavodiniz.walmart.domain.Rotas;
import br.com.gustavodiniz.walmart.domain.Vertice;

public interface CalculoRotasService {
	
	public Map<String, Vertice> calcularPossibilidadesDeRota(String startName, Map<String, Vertice> grafo);
	
	public Rotas calculaRota(String endName, Double autonomiaVeiculo, Double valLitroCombustivel, Map<String, Vertice> grafo);
	
}
