package br.com.gustavodiniz.walmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import br.com.gustavodiniz.walmart.domain.MalhaLogisticaResponse;
import br.com.gustavodiniz.walmart.domain.ResponseCodes;
import br.com.gustavodiniz.walmart.domain.Rotas;
import br.com.gustavodiniz.walmart.domain.RotasResponse;
import br.com.gustavodiniz.walmart.service.RotasService;


@Controller
@RequestMapping("/rotas")
public class RotasController {
	
	@Autowired
	private RotasService rotasService;

	@RequestMapping(value="/listarMalhaLogistica", method = RequestMethod.GET)
	@ResponseBody
	public String consultarMalhaLogistica(@RequestParam(value = "mapa", required=false) String mapa ){
		
		MalhaLogisticaResponse response = rotasService.listarMalhasLogisticas();
		String retorno = new Gson().toJson(response, MalhaLogisticaResponse.class); 
		
		return retorno;
	}
	
	@RequestMapping(value="/incluirMalhaLogistica", method = RequestMethod.GET)
	@ResponseBody
	public String incluirMalhaLogistica(@RequestParam(value = "mapa", required=true) String mapa,
										@RequestParam(value = "origem", required=true) String origem,
										@RequestParam(value = "destino", required=true) String destino,
										@RequestParam(value = "distancia", required=true) Integer distancia){
		
		ResponseCodes response = rotasService.incluirRota(mapa, origem, destino, distancia);
		String retorno = new Gson().toJson(response, ResponseCodes.class); 
		
		return retorno;
	}
	
	@RequestMapping(value="/consultaMelhorRota", method = RequestMethod.GET)
	@ResponseBody
	public String consultaMelhorRota(@RequestParam(value = "mapa", required=true) String mapa, 
									 @RequestParam(value = "origem", required=true) String origem,
									 @RequestParam(value = "destino", required=true) String destino,
									 @RequestParam(value = "autonomia", required=true) Double autonomia,
									 @RequestParam(value = "valLitro", required=true) Double valLitro ){
		
		RotasResponse rotas = rotasService.calcularMelhorCaminho(origem, destino, autonomia, valLitro);
		String retorno = new Gson().toJson(rotas, RotasResponse.class); 
		
		return retorno;
	}
	
}
