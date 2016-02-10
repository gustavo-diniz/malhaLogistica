package br.com.gustavodiniz.walmart.service;

import br.com.gustavodiniz.walmart.domain.MalhaLogisticaResponse;
import br.com.gustavodiniz.walmart.domain.ResponseCodes;
import br.com.gustavodiniz.walmart.domain.RotasResponse;

public interface RotasService {
	
	/**
	 * Persiste os dados de malha logistica na tabela TB_MALHA_LOGISTICA
	 * @param mapa
	 * @param origem
	 * @param destino
	 * @param distancia
	 * @return
	 * @author gustavodinizdossantos
	 */
	public ResponseCodes incluirMalhaLogistica(String mapa, String origem, String destino, Integer distancia);
	
	/**
	 * Lista as malhas logisticas de acordo com o mapa informado
	 * @param mapa
	 * @return
	 * @author gustavodinizdossantos
	 */
	public MalhaLogisticaResponse listarMalhasLogisticas(String mapa);
	
	/**
	 * Efetua o calculo de caminho e custo de rota de acordo com a 
	 * malha logistica entre um ponto de origem e destino. 
	 * @param mapa
	 * @param origem
	 * @param destino
	 * @param autonomiaVeiculo
	 * @param vlLitroConbustivel
	 * @return
	 */
	public RotasResponse calcularMelhorCaminhoRota(String mapa, String origem, String destino, Double autonomiaVeiculo, Double vlLitroConbustivel);
	
}
