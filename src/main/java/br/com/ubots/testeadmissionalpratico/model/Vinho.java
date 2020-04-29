package br.com.ubots.testeadmissionalpratico.model;

public class Vinho {
	private String produto;
	private String variedade;
	private String pais;
	private String categoria;
	private int safra;
	private double preco;
	
	
	public Vinho() {
		
	}
	
	public Vinho(String produto, String variedade, String pais, String categoria, int safra, double preco) {
		this.produto = produto;
		this.variedade = variedade;
		this.pais = pais;
		this.categoria = categoria;
		this.safra = safra;
		this.preco = preco;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	public String getVariedade() {
		return variedade;
	}
	public void setVariedade(String variedade) {
		this.variedade = variedade;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public int getSafra() {
		return safra;
	}
	public void setSafra(int safra) {
		this.safra = safra;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	
}
