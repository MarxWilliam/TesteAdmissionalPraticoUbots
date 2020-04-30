package br.com.ubots.testeadmissionalpratico.model;

import java.util.LinkedList;

public class Categoria extends Celula {
	
	LinkedList<Celula> lista;
	
	public Categoria(String titulo, String categoria, int peso) {
		super(titulo, categoria, peso);
		lista = new LinkedList<Celula>();
	}
	
	public LinkedList<Celula> getLista() {
		return lista;
	}
	
	public void setLista(LinkedList<Celula> lista) {
		this.lista = lista;
	}
	
}
