package br.com.gustavodiniz.walmart.domain;

/**
 * Classe de representação de resposta para consulta de rotas.
 * @author gustavodinizdossantos
 */
public class RotasResponse {

	private String mensagem;
	private Rotas rotaPesquisada;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Rotas getRotaPesquisada() {
		return rotaPesquisada;
	}

	public void setRotaPesquisada(Rotas rotaPesquisada) {
		this.rotaPesquisada = rotaPesquisada;
	}

}
