package br.com.gustavodiniz.walmart.domain;

/**
 * Classe Aresta - utiliza no calculo de malha logistica
 * @author gustavodinizdossantos
 *
 */
public class Aresta {

	public final String v1, v2;
	public final int dist;

	public Aresta(String v1, String v2, int dist) {
		this.v1 = v1;
		this.v2 = v2;
		this.dist = dist;
	}

}
