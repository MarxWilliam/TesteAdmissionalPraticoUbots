package br.com.ubots.testeadmissionalpratico;

import br.com.ubots.testeadmissionalpratico.services.IVendaVinhosService;
import br.com.ubots.testeadmissionalpratico.services.VendaVinhosServiceImplementation;

public class Main {
	public static void main(String[] args) {
		VendaVinhosServiceImplementation vinhos = new VendaVinhosServiceImplementation();
		vinhos.maiorValorTotaldeComprasproCliente();
		
		System.out.println("\n~ ~ ~ ~ ~ ~ ~ ~ ~ ~  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n");
		vinhos.clienteComMaiorCompraUnica(2016);
		
		vinhos.montaMatriz();
		
		vinhos.printGrafoCategoria();
		System.out.println("\n~ ~ ~ ~ ~ ~ ~ ~ ~ ~  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n");
		vinhos.printGrafo();
		
		System.out.println("\n~ ~ ~ ~ ~ ~ ~ ~ ~ ~  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n");
		vinhos.calculaPotuacaoTotal();
		
		System.out.println("\n~ ~ ~ ~ ~ ~ ~ ~ ~ ~  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n");
		vinhos.recomendaVinho("00000000001", 20);
	}
}