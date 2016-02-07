package br.com.gustavodiniz.walmart.domain;

public class Rotas {

	private String nomeRota;
	private String origem;
	private String destino;
	private Double autonomia;
	private Double valLitro;

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

}
