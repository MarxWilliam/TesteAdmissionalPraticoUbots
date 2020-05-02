package br.com.ubots.testeadmissionalpratico.model;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;



/*
 * Classe que armazena todas as informações referente ao um cliente em específico 
 * e suas compras.
 */
public class Info {
	private Cliente cliente;
	private String codigoMaiorCompradoCliente;
	private List<Venda> listaVendas;
	private LinkedList<Categoria> grafo;
	private double valorMaiorCompraCliente;
	private double valorTotalComprasCliente;
	private double valorMedioVinhos = 0;
	private double desvioPadraoValorMedioVinhos = 0;
	private double montanteComprasUltimoAno = 0;
	private int quantidadeTotalVInhos = 0; //comprados
//	private int quantidadeItensUltimoAno = 0;
	
	
	public Info() {
		
	}

	public Info(Cliente cliente, double totalComprasCliente, List<Venda> listaVendas) {
		this.cliente = cliente;
		this.valorTotalComprasCliente = totalComprasCliente;
		this.listaVendas = listaVendas;
	}

	public Cliente getCliente() {
		return cliente;
	}

	
	/**
	 * Calcula a média do valor de todos os vinhos vendidos para este cliente e o desvio padrão.
	 */
	public void calculaValorMediodeVinhoseDesvio() {
		int count = 0;
		double soma = 0;
		for(Venda v : this.listaVendas) {
			for(Vinho vinho : v.getItens()) {
				soma += vinho.getPreco();
				count++;
			}
		}
		setValorMedioVinhos(soma/(double)count);
		double somatorio = 0;
		for(Venda v : this.listaVendas) {
			for(Vinho vinho : v.getItens()) {
				somatorio += Math.pow((vinho.getPreco() - getValorMedioVinhos()), 2);
			}
		}
		double desvio = Math.sqrt(somatorio/(double)count);
		setDesvioPadraoValorMedioVinhos(desvio);
	}
	
	public void printInfo() {
		System.out.println("// - // - //    // - // - //    // - // - //");
		this.getCliente().printCliente();
		System.out.println("// - // - //");
		printCompras();
		System.out.println("Total compras do cliente: " + this.getValorTotalComprasCliente());
		System.out.println("// - // - //    // - // - //    // - // - //\n\n");
	}
	
	private void printCompras() {
		for (Venda venda : this.listaVendas) {
			System.out.println("Código: " + venda.getCodigo() + " Data: " + venda.getData() + " Cliente: "
					+ venda.getCpfCliente());
			for (Vinho vinho : venda.getItens()) {
				System.out.println("\tProduto: " + vinho.getProduto() + " Variedade: " + vinho.getVariedade()
						+ " País: " + vinho.getPais() + " Categoria: " + vinho.getCategoria() + " Safra: "
						+ vinho.getSafra() + " Preço: " + vinho.getPreco());
			}
			System.out.println("Valor total dessa venda: " + venda.getValorTotalVenda() + "\n//  //  //");
		}
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public double getValorTotalComprasCliente() {
		return valorTotalComprasCliente;
	}
	public void setValorTotalComprasCliente(double totalComprasCliente) {
		this.valorTotalComprasCliente = totalComprasCliente;
	}
	public List<Venda> getListaVendas() {
		return listaVendas;
	}
	public void setListaVendas(List<Venda> listaVendas) {
		this.listaVendas = listaVendas;
	}

	public String getCodigoMaiorCompradoCliente() {
		return codigoMaiorCompradoCliente;
	}

	public void setCodigoMaiorCompradoCliente(String codigoMaiorCompradoCliente) {
		this.codigoMaiorCompradoCliente = codigoMaiorCompradoCliente;
	}

	public double getValorMaiorCompraCliente() {
		return valorMaiorCompraCliente;
	}

	public void setValorMaiorCompraCliente(double valorMaiorCompraCliente) {
		this.valorMaiorCompraCliente = valorMaiorCompraCliente;
	}


	public double getValorMedioVinhos() {
		return valorMedioVinhos;
	}

	public void setValorMedioVinhos(double valorMedioVinhos) {
		this.valorMedioVinhos = valorMedioVinhos;
	}

	public double getDesvioPadraoValorMedioVinhos() {
		return desvioPadraoValorMedioVinhos;
	}

	public void setDesvioPadraoValorMedioVinhos(double desvioPadraoValorMedioVinhos) {
		this.desvioPadraoValorMedioVinhos = desvioPadraoValorMedioVinhos;
	}

	public LinkedList<Categoria> getGrafo() {
		return grafo;
	}

	public void setGrafo(LinkedList<Categoria> grafo) {
		this.grafo = grafo;
	}

	public double getMontanteComprasUltimoAno() {
		return montanteComprasUltimoAno;
	}

	public void setMontanteComprasUltimoAno(double montanteComprasUltimoAno) {
		this.montanteComprasUltimoAno = montanteComprasUltimoAno;
	}

	public int getQuantidadeTotalVInhos() {
		return quantidadeTotalVInhos;
	}

	public void setQuantidadeTotalVInhos(int quantidadeTotalVInhos) {
		this.quantidadeTotalVInhos = quantidadeTotalVInhos;
	}
	
//	public int getQuantidadeItensUltimoAno() {
//		return quantidadeItensUltimoAno;
//	}
//
//	public void setQuantidadeItensUltimoAno(int quantidadeItensUltimoAno) {
//		this.quantidadeItensUltimoAno = quantidadeItensUltimoAno;
//	}
	
}
