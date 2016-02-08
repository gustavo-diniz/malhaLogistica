package br.com.gustavodiniz.walmart.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import br.com.gustavodiniz.walmart.dao.RotaDAO;
import br.com.gustavodiniz.walmart.domain.Aresta;
import br.com.gustavodiniz.walmart.domain.Grafo;
import br.com.gustavodiniz.walmart.domain.MalhaLogistica;
import br.com.gustavodiniz.walmart.domain.MalhaLogisticaResponse;
import br.com.gustavodiniz.walmart.domain.ResponseCodes;
import br.com.gustavodiniz.walmart.domain.Rotas;
import br.com.gustavodiniz.walmart.domain.RotasResponse;
import br.com.gustavodiniz.walmart.domain.Vertice;
import br.com.gustavodiniz.walmart.service.CalculoRotasService;
import br.com.gustavodiniz.walmart.service.RotasService;

@Service
@Transactional
public class RotasServiceImpl implements RotasService {

	@Autowired
	private RotaDAO rotaDAO;

	@Autowired
	private CalculoRotasService calculoRotasService;
	
	@Override
	public ResponseCodes incluirRota(String mapa, String origem, String destino, Integer distancia) {
		
		MalhaLogistica malhaLogistica = new MalhaLogistica();
		malhaLogistica.setNomeMapa(mapa);
		malhaLogistica.setPontoOrigem(origem);
		malhaLogistica.setPontoDestino(destino);
		malhaLogistica.setDistancia(distancia);
		
		ResponseCodes responseCodes = new ResponseCodes();
		try{  
			rotaDAO.incluirMalhaLogistica(malhaLogistica);
			responseCodes.setRetornoMensagem("Registro incluído com sucesso !");
		
		}catch(Exception e){
			responseCodes.setRetornoMensagem("Erro ao incluir registro !");
		}
		
		return responseCodes;
		
	}

	
	@Override
	public MalhaLogisticaResponse listarMalhasLogisticas() {
		MalhaLogisticaResponse malhaResponse = new MalhaLogisticaResponse();
		try{
			List<MalhaLogistica> listaMalhas = rotaDAO.listarMalhasLogisticas();

			if(listaMalhas.size() == 0){
				malhaResponse.setMensagem("Nenhum registro encontrado !");
			}else{
				malhaResponse.setMalhasLogisticas(listaMalhas);
			}
		}catch(Exception ex){
			malhaResponse.setMensagem("Erro ao listar Malhas Logisticas !");
		}
		
		return malhaResponse;
	}


	@Override
	public RotasResponse calcularMelhorCaminho(String origem, String destino, Double autonomiaVeiculo, Double vlLitroConbustivel) {
		
		RotasResponse rotasResponse = new RotasResponse();
		
		//1- listar rotas disponiveis e inicialiar valores no objeto Arestas
		List<MalhaLogistica> ListaMalhaLogistica = rotaDAO.listarMalhasLogisticas();
		Aresta[] arestas = new Aresta[ListaMalhaLogistica.size()];
		for(int i=0; i<ListaMalhaLogistica.size(); i++){
			arestas[i] = new Aresta(ListaMalhaLogistica.get(i).getPontoOrigem(), 
								    ListaMalhaLogistica.get(i).getPontoDestino(), 
								    ListaMalhaLogistica.get(i).getDistancia());
		}	
		
		//2 - incluir valores no grafo abaixo
		Grafo grafo = new Grafo(arestas);
		
		//3 - inicia o calculo de rotas
		Map<String, Vertice> listMap = calculoRotasService.calcularPossibilidadesDeRota(origem, grafo.getGrafo());
		
		//4 - retorna o objeto com os valores de rota.
		Rotas rota = calculoRotasService.calculaRota(destino, autonomiaVeiculo, vlLitroConbustivel, listMap);
		
		if(rota == null){
			rotasResponse.setMensagem("Cálculo de rota não encontrado para a solicitação: "+origem+" - "+destino);
		
		}else{
			rota.setOrigem(origem);
			rota.setDestino(destino);
			rotasResponse.setRotaPesquisada(rota);
		}
		
		return rotasResponse;
		
	}
	
	
}
