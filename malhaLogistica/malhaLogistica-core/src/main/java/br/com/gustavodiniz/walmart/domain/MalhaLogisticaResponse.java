package br.com.gustavodiniz.walmart.domain;

import java.util.List;

/**
 * Classe de representação de resposta da malha logistica,
 * utilizada como wrapper para resposta de requisicoes. 
 * @author gustavodinizdossantos
 *
 */
public class MalhaLogisticaResponse {

	private String mensagem;
	private List<MalhaLogistica> malhasLogisticas;
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public List<MalhaLogistica> getMalhasLogisticas() {
		return malhasLogisticas;
	}
	public void setMalhasLogisticas(List<MalhaLogistica> malhasLogisticas) {
		this.malhasLogisticas = malhasLogisticas;
	}

	
}
