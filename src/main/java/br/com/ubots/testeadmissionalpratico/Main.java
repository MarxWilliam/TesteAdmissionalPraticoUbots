package br.com.ubots.testeadmissionalpratico;

import br.com.ubots.testeadmissionalpratico.model.Vinho;
import br.com.ubots.testeadmissionalpratico.services.IVendaVinhosService;
import br.com.ubots.testeadmissionalpratico.services.VendaVinhosServiceImplementation;
import br.com.ubots.testeadmissionalpratico.services.VendaVinhosServiceImplementation2;
import br.com.ubots.testeadmissionalpratico.util.DataPadrao;

public class Main {
	public static void main(String[] args) {
		VendaVinhosServiceImplementation2 vinhos = new VendaVinhosServiceImplementation2();
		vinhos.maiorValorTotaldeComprasporCliente();
		
		System.out.println("\n~ ~ ~ ~ ~ ~ ~ ~ ~ ~  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n");
		vinhos.clienteComMaiorCompraUnica(2014);
		
	
		System.out.println("\n~ ~ ~ ~ ~ ~ ~ ~ ~ ~  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n");
		//analisa um ano pra traz da data colocada nesse caso entre 31-12-2015 até 31-12-2016
		vinhos.selecionaListaClientesMaisFieis(DataPadrao.parse("31-12-2016")); 
		
		System.out.println("\n~ ~ ~ ~ ~ ~ ~ ~ ~ ~  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n");
		System.out.println("Vinho recomendado: ");
		vinhos.recomendaVinho("00000000001", 1);
		
		System.out.println("\n~ ~ ~ ~ ~ ~ ~ ~ ~ ~  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n");
		
		Vinho vinho = new Vinho();
		vinho.setProduto("Vinho que o cliente não comprou");
		vinho.setCategoria("branco");
		vinho.setPais("Brasil");
		vinho.setSafra(2015);
		vinho.setVariedade("Sauvignon Blanc");
		System.out.println("Verifica pontuação sobre vinho ainda não comprado: " + vinhos.calculaPontuacao(vinho, "00000000011") + "%");
		;
	}
}