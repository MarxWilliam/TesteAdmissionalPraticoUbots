package br.com.ubots.testeadmissionalpratico;

import br.com.ubots.testeadmissionalpratico.services.VendaVinhosService;
import br.com.ubots.testeadmissionalpratico.services.VendaVinhosServiceImplementation;

public class Main {
	public static void main(String[] args) {
		VendaVinhosServiceImplementation vinhos = new VendaVinhosServiceImplementation();
		vinhos.maiorValorTotaldeComprasproCliente();
		
		System.out.println("\n~ ~ ~ ~ ~ ~ ~ ~ ~ ~  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n");
		vinhos.clienteComMaiorCompraUnica(2016);
	}
}