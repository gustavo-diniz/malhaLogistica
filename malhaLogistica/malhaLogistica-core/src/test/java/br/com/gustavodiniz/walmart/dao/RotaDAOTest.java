package br.com.gustavodiniz.walmart.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.gustavodiniz.walmart.domain.MalhaLogisticaResponse;
import br.com.gustavodiniz.walmart.domain.ResponseCodes;
import br.com.gustavodiniz.walmart.domain.RotasResponse;
import br.com.gustavodiniz.walmart.service.RotasService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class RotaDAOTest {

	@Autowired
	private RotasService rotaService;
	
	/**
	 * Efetua teste simples do serviço de inclusão de malha logistica e valida o retorno da mansagemd e sucesso ! 
	 */
	@Test
	public void incluirMalhaLogisticaTest(){  
		
		long atual = System.currentTimeMillis();
		String mapa = "SP - "+atual;
		
		ResponseCodes response = rotaService.incluirMalhaLogistica(mapa,"PONTO_A", "PONTO_B", 20);
		Assert.assertTrue(response.getRetornoMensagem().equals("Registro incluído com sucesso !"));
	}
	
	/**
	 * Efetua teste de validação de malha ja existente na inclusão de malha logistica e valida a mensagem de retorno ! 
	 */
	@Test
	public void incluirMalhaLogisticaJaInseridaTest(){  
		
		long atual = System.currentTimeMillis();
		String mapa = "SP - "+atual;
		
		ResponseCodes response = rotaService.incluirMalhaLogistica(mapa,"PONTO_A", "PONTO_B", 20);
					  response = rotaService.incluirMalhaLogistica(mapa,"PONTO_A", "PONTO_B", 20);
		
		Assert.assertTrue(response.getRetornoMensagem().equals("Malha logistica informada já existente !"));
	}
	
	/**
	 * Valida o metodo de listar malhas logisticas pelo nome do mapa !
	 */
	@Test
	public void listarMalhasLogisticasTest(){
		long atual = System.currentTimeMillis();
		String mapa = "SP - "+atual;
		
		ResponseCodes response = rotaService.incluirMalhaLogistica(mapa,"PONTO_A", "PONTO_B", 20);
		
		MalhaLogisticaResponse lista = rotaService.listarMalhasLogisticas(mapa);
		
		Assert.assertTrue(lista.getMalhasLogisticas().size() > 0);
	}
	
	/**
	 * Valida o metodo de listar malhas logisticas pelo nome do mapa e valida a mensagem de retorno !
	 */
	@Test
	public void listarMalhasLogisticasInexistentesTest(){
		long atual = System.currentTimeMillis();
		String mapa = "SP - "+atual;
		
		MalhaLogisticaResponse lista = rotaService.listarMalhasLogisticas(mapa);
		
		Assert.assertTrue(lista.getMensagem().equals("Nenhum registro encontrado !"));
	}
	
	/**
	 * Efetua o teste semi integrado simples de calculo de melhor caminho de rota !
	 */
	@Test
	public void calcularMelhorCaminhoRotaTest(){
		
		boolean testOk = false;
		long atual = System.currentTimeMillis();
		String mapa = "SP - "+atual;
		
		ResponseCodes response = rotaService.incluirMalhaLogistica(mapa,"A", "B", 10);
					  response = rotaService.incluirMalhaLogistica(mapa,"B", "D", 15);
					  response = rotaService.incluirMalhaLogistica(mapa,"A", "C", 20);
					  response = rotaService.incluirMalhaLogistica(mapa,"C", "D", 30);
					  response = rotaService.incluirMalhaLogistica(mapa,"B", "E", 50);
					  response = rotaService.incluirMalhaLogistica(mapa,"D", "E", 30);
		
		RotasResponse rotaResponse = rotaService.calcularMelhorCaminhoRota(mapa, "A", "D", 10.0, 2.50);
		
		if(rotaResponse.getRotaPesquisada().getMenorCaminho().equals("A - B - D") &&
		   rotaResponse.getRotaPesquisada().getCustoRota() == 6.25){
			testOk = true;
		}
		
		Assert.assertTrue(testOk);
	}
	
	
	
}
