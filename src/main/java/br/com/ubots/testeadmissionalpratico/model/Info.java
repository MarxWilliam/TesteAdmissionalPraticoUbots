package br.com.ubots.testeadmissionalpratico.model;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;



/*
 * Classe que armazena todas as informações referente ao um cliente em específico 
 * 
 */
public class Info {
	Cliente cliente;
	String codigoMaiorCompradoCliente;
	double valorMaiorCompraCliente;
	double totalComprasCliente;
	List<Venda> listaVendas;
	LinkedList<Categoria> grafo;
	
	double valorMedioVinhos;
	double desvioPadraoValorMedioVinhos;
	
	
	
	public Info() {
		
	}

	public Info(Cliente cliente, double totalComprasCliente, List<Venda> listaVendas) {
		this.cliente = cliente;
		this.totalComprasCliente = totalComprasCliente;
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
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public double getTotalComprasCliente() {
		return totalComprasCliente;
	}
	public void setTotalComprasCliente(double totalComprasCliente) {
		this.totalComprasCliente = totalComprasCliente;
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
	
}
