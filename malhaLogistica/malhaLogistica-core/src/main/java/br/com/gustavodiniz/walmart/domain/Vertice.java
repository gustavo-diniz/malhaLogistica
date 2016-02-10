package br.com.gustavodiniz.walmart.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe de representação da Vertice, utilizada principalmente por Grafos.
 * @author gustavodinizdossantos
 *
 */
public class Vertice implements Comparable<Vertice>{
    public final String nome;
    public int distanciaMaxima = Integer.MAX_VALUE;
    public Vertice anterior = null;
    public final Map<Vertice, Integer> verticesProximas = new HashMap<>();

    public Vertice(String nome) {
       this.nome = nome;
    }
    
    /**
     * Calcula a distancia da rota, através do objeto rota contendo os dados de origem,
     * destino, autonomia e valor de litro de combustivel
     * @param rota
     * @return
     */
    public Rotas calculaDistancia(Rotas rota) {
        if (this == this.anterior) {
           implementaCaminhoRota(rota);
     	   
        } else if (this.anterior == null) {
     	   System.out.println("Não encontrado: "+this.nome);//TODO
        } else {
           this.anterior.calculaDistancia(rota);
           implementaCaminhoRota(rota);
           rota.setDistanciaTotal(this.distanciaMaxima);
        }
        return rota;
     }

    /**
     * Implementa o texto contendo do menor caminho da rota 
     * @param rota
     */
	private void implementaCaminhoRota(Rotas rota) {
		if(rota.getMenorCaminho() != null && !rota.getMenorCaminho().equals("")){
        	   rota.setMenorCaminho(rota.getMenorCaminho()+" - "+this.nome);
           }else{
        	   rota.setMenorCaminho(rota.getMenorCaminho()+this.nome);
           }
	}

    public int compareTo(Vertice outroVertice) {
       return Integer.compare(distanciaMaxima, outroVertice.distanciaMaxima);
    }
 
	
}
