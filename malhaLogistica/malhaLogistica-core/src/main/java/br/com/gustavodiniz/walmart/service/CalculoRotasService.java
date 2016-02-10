package br.com.gustavodiniz.walmart.service;

import java.util.Map;

import br.com.gustavodiniz.walmart.domain.Rotas;
import br.com.gustavodiniz.walmart.domain.Vertice;

/**
 * Classe de serviço responsável pelo calculo de rotas.
 * @author gustavodinizdossantos
 *
 */
public interface CalculoRotasService {
	
	/**
	 * Serviço responsavel por avaliar as possibilidades de rota contidas em um grafo a partir
	 * do ponto inicial informado na variavel origem;
	 * @param origem
	 * @param grafo
	 * @return
	 */
	public Map<String, Vertice> calcularPossibilidadesDeRota(String origem, Map<String, Vertice> grafo);
	
	/**
	 * Serviço responsável por calcular a rota com o menor caminho possível ate o destino informado atraves
	 * da variavel destino, no objeto tambem é calculado o custo desta rota através da
	 * autonomia e valor de combustivel pela distancia percorrida.
	 * @param origem
	 * @param autonomiaVeiculo
	 * @param valLitroCombustivel
	 * @param grafo
	 * @return
	 */
	public Rotas calculaRotaComMenorCaminho(String destino, Double autonomiaVeiculo, Double valLitroCombustivel, Map<String, Vertice> grafo);
	
}
