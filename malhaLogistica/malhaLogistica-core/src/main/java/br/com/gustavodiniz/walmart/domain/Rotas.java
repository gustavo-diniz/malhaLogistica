package br.com.gustavodiniz.walmart.domain;


/**
 * Classe de representação de consulta de rotas
 * @author gustavodinizdossantos
 *
 */
public class Rotas {

	private String nomeRota;
	private String origem;
	private String destino;
	private Double autonomia;
	private Double valLitro;
	
	private String menorCaminho;
	private Integer distanciaTotal;
	private Double custoRota;

	public String getNomeRota() {
		return nomeRota;
	}

	public void setNomeRota(String nomeRota) {
		this.nomeRota = nomeRota;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Double getAutonomia() {
		return autonomia;
	}

	public void setAutonomia(Double autonomia) {
		this.autonomia = autonomia;
	}

	public Double getValLitro() {
		return valLitro;
	}

	public void setValLitro(Double valLitro) {
		this.valLitro = valLitro;
	}

	public String getMenorCaminho() {
		return menorCaminho;
	}

	public void setMenorCaminho(String menorCaminho) {
		this.menorCaminho = menorCaminho;
	}

	public Integer getDistanciaTotal() {
		return distanciaTotal;
	}

	public void setDistanciaTotal(Integer distanciaTotal) {
		this.distanciaTotal = distanciaTotal;
	}

	public Double getCustoRota() {
		return custoRota;
	}

	public void setCustoRota(Double custoRota) {
		this.custoRota = custoRota;
	}

}
