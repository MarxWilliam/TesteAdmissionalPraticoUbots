package br.com.ubots.testeadmissionalpratico.model;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public class Venda {
	private String codigo;
	private ZonedDateTime data;
	private String cpfCliente;
	private List<Item> itens;
	private double valorTotalVenda;
	
    public Venda() {
    	
    }	
	
	public Venda(String codigo, ZonedDateTime data, String cpfCliente, List<Item> itens, double valorTotal) {
		this.codigo = codigo;
		this.data = data;
		this.cpfCliente = cpfCliente;
		this.itens = itens;
		this.valorTotalVenda = valorTotal;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public ZonedDateTime getData() {
		return data;
	}
	public void setData(ZonedDateTime data) {
		this.data = data;
	}
	public String getCpfCliente() {
		return cpfCliente;
	}
	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}
	public List<Item> getItens() {
		return itens;
	}
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	public double getValorTotalVenda() {
		return valorTotalVenda;
	}
	public void setValorTotalVenda(double valorTotal) {
		this.valorTotalVenda = valorTotal;
	}
	
	
}
