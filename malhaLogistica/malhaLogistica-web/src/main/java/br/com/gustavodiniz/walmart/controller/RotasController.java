package br.com.gustavodiniz.walmart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import br.com.gustavodiniz.walmart.domain.MalhaLogistica;
import br.com.gustavodiniz.walmart.domain.ResponseCodes;
import br.com.gustavodiniz.walmart.domain.Rotas;


@Controller
@RequestMapping("/rotas")
public class RotasController {

	@RequestMapping(value="/listarMalhaLogistica", method = RequestMethod.GET)
	@ResponseBody
	public String consultarMalhaLogistica(@RequestParam(value = "mapa", required=false) String mapa ){
		
		MalhaLogistica rota = new MalhaLogistica();
		rota.setNomeMapa(mapa);
		rota.setPontoOrigem("A");
		rota.setPontoDestino("B");
		rota.setDistancia(10);
		
		String retorno = new Gson().toJson(rota, MalhaLogistica.class); 
		
		return retorno;
	}
	
	@RequestMapping(value="/incluirMalhaLogistica", method = RequestMethod.GET)
	@ResponseBody
	public String incluirMalhaLogistica(@RequestParam(value = "mapa", required=true) String mapa,
										@RequestParam(value = "origem", required=true) String origem,
										@RequestParam(value = "destino", required=true) String destino,
										@RequestParam(value = "distancia", required=true) Integer distancia){
		
		ResponseCodes codes = new ResponseCodes();
		codes.setCodigoMensagem("200");
		codes.setRetornoMensagem("Malha logistica incluída com sucesso !");
		
		String retorno = new Gson().toJson(codes, ResponseCodes.class); 
		
		return retorno;
	}
	
	@RequestMapping(value="/consultaMelhorRota", method = RequestMethod.GET)
	@ResponseBody
	public String consultaMelhorRota(@RequestParam(value = "mapa", required=true) String mapa, 
									 @RequestParam(value = "origem", required=true) String pontoInicio,
									 @RequestParam(value = "destino", required=true) String pontoFinal,
									 @RequestParam(value = "autonomia", required=true) Double autonomia,
									 @RequestParam(value = "valLitro", required=true) Double valLitro ){
		
		Rotas rotas = new Rotas();
		rotas.setNomeRota("Teste SP");
		rotas.setOrigem("A");
		rotas.setDestino("B");
		rotas.setAutonomia(10d);
		rotas.setValLitro(2.50);
		
		
		String retorno = new Gson().toJson(rotas, Rotas.class); 
		
		return retorno;
	}
	
}
