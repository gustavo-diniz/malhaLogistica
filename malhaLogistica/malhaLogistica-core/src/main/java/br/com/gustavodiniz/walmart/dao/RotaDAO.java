package br.com.gustavodiniz.walmart.dao;

import java.util.List;

import br.com.gustavodiniz.walmart.domain.MalhaLogistica;

/**
 * Interface RotaDAO - para operações de persistencia e consultas relacionas a rotas
 * @author gustavodinizdossantos
 *
 */
public interface RotaDAO {
	
	/**
	 * Método responsável por persistir uma malha logistica na tabela
	 * TB_MALHA_LOGISTICA 
	 * @param malhaLogistica
	 * @author gustavodinizdossantos
	 */
	public void incluirMalhaLogistica(MalhaLogistica malhaLogistica);
	
	/**
	 * Lista as malhas logisticas existentes na tabela TB_MALHA_LOGISTICA
	 * de acordo com o mapa informado.
	 * @param mapa
	 * @return
	 * @author gustavodinizdossantos
	 */
	public List<MalhaLogistica> listarMalhasLogisticas(String mapa);
	
	/**
	 * Lista as malhas logisticas existentes na tabela TB_MALHA_LOGISTICA
	 * de acordo com o mapa, origem e destino informados.
	 * @param mapa
	 * @param origem
	 * @param destino
	 * @return
	 * @author gustavodinizdossantos
	 */
	public List<MalhaLogistica> listarMalhasLogisticas(String mapa, String origem, String destino);
	
	
	
	
}
