package br.com.ubots.testeadmissionalpratico.model;

public class Celula {
	private String titulo;
	private String categoria;
	private int peso;
	
	
	public Celula() {
		
	}
	
	public Celula(String titulo, String categoria, int peso) {
		this.titulo = titulo;
		this.categoria = categoria;
		this.peso = peso;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
}
