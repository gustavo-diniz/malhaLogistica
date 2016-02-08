package br.com.gustavodiniz.walmart.service;

import br.com.gustavodiniz.walmart.domain.MalhaLogisticaResponse;
import br.com.gustavodiniz.walmart.domain.ResponseCodes;
import br.com.gustavodiniz.walmart.domain.RotasResponse;

public interface RotasService {

	public ResponseCodes incluirRota(String mapa, String origem, String destino, Integer distancia);
	public MalhaLogisticaResponse listarMalhasLogisticas();	
	public RotasResponse calcularMelhorCaminho(String origem, String destino, Double autonomiaVeiculo, Double vlLitroConbustivel);
	
}
