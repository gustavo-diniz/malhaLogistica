package br.com.gustavodiniz.walmart.domain;

/**
 * Classe de representação de respostas para requisições.
 * @author gustavodinizdossantos
 *
 */
public class ResponseCodes {

	private String retornoMensagem;
	private String response;

	public String getRetornoMensagem() {
		return retornoMensagem;
	}

	public void setRetornoMensagem(String retornoMensagem) {
		this.retornoMensagem = retornoMensagem;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	

}
