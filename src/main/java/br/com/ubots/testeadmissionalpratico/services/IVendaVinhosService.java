package br.com.ubots.testeadmissionalpratico.services;

import java.time.ZonedDateTime;

import br.com.ubots.testeadmissionalpratico.model.Vinho;
import br.com.ubots.testeadmissionalpratico.util.DataPadrao;

public interface IVendaVinhosService {
	//	# 1 - Liste os clientes ordenados pelo maior valor total em compras.
	public void maiorValorTotaldeComprasporCliente(); 

	//	# 2 - Mostre o cliente com maior compra única no último ano (2016).
	public void clienteComMaiorCompraUnica(int ano);

	//	# 3 - Liste os clientes mais fiéis.
	public void selecionaListaClientesMaisFieis(ZonedDateTime ano); //"dd-MM-yyyy" DataPadrao.parse("15-05-2016")

	//	# 4 - Recomende um vinho para um determinado cliente a partir do histórico
	public void  recomendaVinho(String cpf, int quant); //cpf do cliente e quantidade de recomendações.

	public double calculaPontuacao(Vinho vinho, String cpf);
}