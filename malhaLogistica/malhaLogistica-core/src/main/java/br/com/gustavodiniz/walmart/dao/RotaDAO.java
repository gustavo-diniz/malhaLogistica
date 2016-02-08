package br.com.gustavodiniz.walmart.dao;

import java.util.List;

import br.com.gustavodiniz.walmart.domain.MalhaLogistica;

public interface RotaDAO {

	public void incluirMalhaLogistica(MalhaLogistica malhaLogistica);
	
	public List<MalhaLogistica> listarMalhasLogisticas();
	
	
	
}
