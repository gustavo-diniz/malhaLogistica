package br.com.gustavodiniz.walmart.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional(readOnly=false)
public class RotasServiceImpl implements RotasService {

	@Autowired
	private RotaDAO rotaDAO;

	@Autowired
	private CalculoRotasService calculoRotasService;
	
	@Override
	public ResponseCodes incluirMalhaLogistica(String mapa, String origem, String destino, Integer distancia) {
		ResponseCodes responseCodes = new ResponseCodes();
		
		if(!validaCamposInclusaoMalhaLogistica(mapa, origem, destino, distancia, responseCodes)){
			return responseCodes;
		
		}else{
			persisteMalhaLogistica(mapa, origem, destino, distancia, responseCodes);
			return responseCodes;
		}
	}

	/**
	 * Efetua a persistencia da malha logistica.
	 * @param mapa
	 * @param origem
	 * @param destino
	 * @param distancia
	 * @param responseCodes
	 */
	private void persisteMalhaLogistica(String mapa, String origem, String destino, Integer distancia, ResponseCodes responseCodes) {
		MalhaLogistica malhaLogistica = new MalhaLogistica();
		malhaLogistica.setNomeMapa(mapa.toUpperCase());
		malhaLogistica.setPontoOrigem(origem.toUpperCase());
		malhaLogistica.setPontoDestino(destino.toUpperCase());
		malhaLogistica.setDistancia(distancia);
		
		try{  
			rotaDAO.incluirMalhaLogistica(malhaLogistica);
			responseCodes.setRetornoMensagem("Registro incluído com sucesso !");
		
		}catch(Exception e){
			responseCodes.setRetornoMensagem("Erro ao incluir registro !");
		}
	}

	/**
	 * Valida os campos de malha logistica, e caso encontre incosistencias
	 * , informa a mensagem no objeto responseCodes
	 * @param mapa
	 * @param origem
	 * @param destino
	 * @param distancia
	 * @param responseCodes
	 * @return
	 * @author gustavodinizdossantos
	 */
	private boolean validaCamposInclusaoMalhaLogistica(String mapa, String origem, String destino, Integer distancia, ResponseCodes responseCodes) {
		if(mapa == null || mapa.equals("")){
			responseCodes.setRetornoMensagem("informe o nome do mapa ! (ex: mapa=SP) ");
			return false;
		
		}if(mapa.length() > 60){
			responseCodes.setRetornoMensagem("Tamanho do nome do mapa excede o limite de 60 caracteres ! ");
			return false;
		
		}else if(origem == null || origem.equals("")){
			responseCodes.setRetornoMensagem("informe a origem ! (ex: origem=pontoA)");
			return false;
		
		}else if(origem.length() > 60){
			responseCodes.setRetornoMensagem("Tamanho da origem excede o limite de 60 caracteres ! ");
			return false;
		
		}else if(destino ==  null || destino.equals("")){
			responseCodes.setRetornoMensagem("Informe o destino ! (ex: destino=pontoF)");
			return false;
		
		}else if(destino.length() > 60){
			responseCodes.setRetornoMensagem("Tamanho do destino excede o limite de 60 caracteres ! ");
			return false;
		
		}else if(distancia == null || distancia < 0){
			responseCodes.setRetornoMensagem("Informe a distancia correta ! (ex: distancia=30 )");
			return false;
		
		}else if(rotaDAO.listarMalhasLogisticas(mapa, origem, destino).size() > 0){
			responseCodes.setRetornoMensagem("Malha logistica informada já existente !");
			return false;
		}
		
		return true;
	}
	
	@Override
	public MalhaLogisticaResponse listarMalhasLogisticas(String mapa) {
		MalhaLogisticaResponse malhaResponse = new MalhaLogisticaResponse();
		try{
			if(mapa == null){
				malhaResponse.setMensagem("Informe o mapa para listar as malhas logisticas !");
				return malhaResponse;
			}
			
			efetuaConsultaMalhaLogistica(mapa, malhaResponse);
			
		}catch(Exception ex){
			malhaResponse.setMensagem("Erro ao listar Malhas Logisticas !");
		}
		
		return malhaResponse;
	}

	/**
	 * Efetua a consulta de malha logistica de acordo com o mapa pesquisado,
	 * caso encontre incosistencias, persiste o objeto malhaResponse com a mensagem, ou 
	 * lista de malhas encontradas.
	 * 
	 * @param mapa
	 * @param malhaResponse
	 */
	private void efetuaConsultaMalhaLogistica(String mapa, MalhaLogisticaResponse malhaResponse) {
		List<MalhaLogistica> listaMalhas = rotaDAO.listarMalhasLogisticas(mapa);

		if(listaMalhas.size() == 0){
			malhaResponse.setMensagem("Nenhum registro encontrado !");
		}else{
			malhaResponse.setMalhasLogisticas(listaMalhas);
		}
	}

	@Override
	public RotasResponse calcularMelhorCaminhoRota(String mapa, String origem, String destino, Double autonomiaVeiculo, Double vlLitroConbustivel) {
		RotasResponse rotasResponse = new RotasResponse();
		
		if(!validaCamposCalculoDeRota(mapa, origem, destino, autonomiaVeiculo, vlLitroConbustivel, rotasResponse)){
			return rotasResponse;
		
		}else{
			Aresta[] arestas = inicializaArestasComMapaPesquisado(mapa);
			
			if(arestas.length == 0){
				rotasResponse.setMensagem("Não foram localizados rotas disponíveis para este mapa: "+mapa);
			}else{
				Grafo grafo = new Grafo(arestas);
				Map<String, Vertice> listMap = calculoRotasService.calcularPossibilidadesDeRota(origem, grafo.getGrafo());
				
				if(listMap == null){
					rotasResponse.setMensagem("Rota não localizada a partir da origem: "+origem);
					return rotasResponse;
				}else{
					rotasResponse = efetuaCalculoDeRota(origem, destino, autonomiaVeiculo, vlLitroConbustivel, listMap);
				}
			}
		}
		return rotasResponse;
	}

	/**
	 * Valida os campos do calculo de rotas, caso encontre inconsistências, o objeto
	 * rotasResponse sera persistido com a mensagem da inconsistência localizada.
	 * @param mapa
	 * @param origem
	 * @param destino
	 * @param autonomiaVeiculo
	 * @param vlLitroConbustivel
	 * @param rotasResponse
	 * @return
	 */
	private boolean validaCamposCalculoDeRota(String mapa, String origem, String destino, Double autonomiaVeiculo,
			Double vlLitroConbustivel, RotasResponse rotasResponse) {
		
		if(mapa == null || mapa.equals("")){
			rotasResponse.setMensagem("informe o nome do mapa ! (ex: mapa=SP) ");
			return false;
			
		}if(mapa.length() > 60){
			rotasResponse.setMensagem("Tamanho do nome do mapa excede o limite de 60 caracteres ! ");
			return false;
			
		}else if(origem == null || origem.equals("")){
			rotasResponse.setMensagem("informe a origem ! (ex: origem=pontoA)");
			return false;
			
		}else if(origem.length() > 60){
			rotasResponse.setMensagem("Tamanho da origem excede o limite de 60 caracteres ! ");
			return false;
			
		}else if(destino ==  null || destino.equals("")){
			rotasResponse.setMensagem("Informe o destino ! (ex: destino=pontoF)");
			return false;
			
		}else if(destino.length() > 60){
			rotasResponse.setMensagem("Tamanho do destino excede o limite de 60 caracteres ! ");
			return false;
			
		}else if(autonomiaVeiculo == null || autonomiaVeiculo <= 0){
			rotasResponse.setMensagem("Informe a autonomia correta do veículo ! (ex: autonomia = 5.2)");
			return false;
			
		}else if(vlLitroConbustivel == null || vlLitroConbustivel <= 0){
			rotasResponse.setMensagem("Informe a valor por litro correto do combustível ! (ex: valLitro = 3.2)");
			return false;
		}
		
		return true;
	}	

	/**
	 * Efetua o cálculo de rota e insere no objeto rotasResponse o calculo encontrado.
	 * @param origem
	 * @param destino
	 * @param autonomiaVeiculo
	 * @param vlLitroConbustivel
	 * @param listMap
	 * @return
	 */
	private RotasResponse efetuaCalculoDeRota(String origem, String destino, Double autonomiaVeiculo, Double vlLitroConbustivel, Map<String, Vertice> listMap) {
		
		RotasResponse rotasResponse = new RotasResponse();
		Rotas rota = calculoRotasService.calculaRotaComMenorCaminho(destino, autonomiaVeiculo, vlLitroConbustivel, listMap);
		
		if(rota == null){
			rotasResponse.setMensagem("Cálculo de rota não encontrado para a solicitação: "+origem+" - "+destino);
		}else{
			rota.setOrigem(origem);
			rota.setDestino(destino);
			rotasResponse.setRotaPesquisada(rota);
		}
		
		return rotasResponse;
	}

	/**
	 * Insere um vetor de arestas de acordo com a lista de malhas logisticas encontradas
	 * @param mapa
	 * @return
	 */
	private Aresta[] inicializaArestasComMapaPesquisado(String mapa) {
		List<MalhaLogistica> ListaMalhaLogistica = rotaDAO.listarMalhasLogisticas(mapa);
		Aresta[] arestas = new Aresta[ListaMalhaLogistica.size()];
		for(int i=0; i<ListaMalhaLogistica.size(); i++){
			arestas[i] = new Aresta(ListaMalhaLogistica.get(i).getPontoOrigem(), 
								    ListaMalhaLogistica.get(i).getPontoDestino(), 
								    ListaMalhaLogistica.get(i).getDistancia());
		}
		return arestas;
	}
	
	
}
