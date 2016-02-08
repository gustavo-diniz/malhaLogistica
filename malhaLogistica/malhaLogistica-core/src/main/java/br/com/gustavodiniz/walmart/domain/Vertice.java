package br.com.gustavodiniz.walmart.domain;

import java.util.HashMap;
import java.util.Map;



public class Vertice implements Comparable<Vertice>{
    public final String name;
    public int dist = Integer.MAX_VALUE; // MAX_VALUE assumed to be infinity
    public Vertice previous = null;
    public final Map<Vertice, Integer> neighbours = new HashMap<>();

    public Vertice(String name) {
       this.name = name;
    }

    public void printPath() {
       if (this == this.previous) {
          //System.out.printf("%s", this.name);
    	   System.out.println(this.name);
       } else if (this.previous == null) {
//          System.out.printf("%s(unreached)", this.name);
    	   System.out.println("Não encontrado: "+this.name);
       } else {
          this.previous.printPath();
//          System.out.printf(" -> %s(%d)", this.name, this.dist);
          System.out.println(this.name+" - "+this.dist);
       }
    }
    
    public Rotas calculaDistancia(Rotas rota) {
        if (this == this.previous) {
           implementaCaminhoRota(rota);
     	   
        } else if (this.previous == null) {
     	   System.out.println("Não encontrado: "+this.name);
        } else {
           this.previous.calculaDistancia(rota);
           implementaCaminhoRota(rota);
           rota.setDistanciaTotal(this.dist);
        }
        return rota;
     }

	private void implementaCaminhoRota(Rotas rota) {
		if(rota.getMenorCaminho() != null && !rota.getMenorCaminho().equals("")){
        	   rota.setMenorCaminho(rota.getMenorCaminho()+" - "+this.name);
           }else{
        	   rota.setMenorCaminho(rota.getMenorCaminho()+this.name);
           }
	}

    public int compareTo(Vertice outroVertice) {
       return Integer.compare(dist, outroVertice.dist);
    }
 
	
}
