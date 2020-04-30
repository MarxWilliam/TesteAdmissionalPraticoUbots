package br.com.ubots.testeadmissionalpratico.model;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

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
