package br.com.gustavodiniz.walmart.domain;

/**
 * Classe de representação da malha logistica.
 * @author gustavodinizdossantos
 *
 */
public class MalhaLogistica {

	private String nomeMapa;
	private String pontoOrigem;
	private String pontoDestino;
	private Integer distancia;

	public String getNomeMapa() {
		return nomeMapa;
	}

	public void setNomeMapa(String nomeMapa) {
		this.nomeMapa = nomeMapa;
	}

	public String getPontoOrigem() {
		return pontoOrigem;
	}

	public void setPontoOrigem(String pontoOrigem) {
		this.pontoOrigem = pontoOrigem;
	}

	public String getPontoDestino() {
		return pontoDestino;
	}

	public void setPontoDestino(String pontoDestino) {
		this.pontoDestino = pontoDestino;
	}

	public Integer getDistancia() {
		return distancia;
	}

	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}

}
