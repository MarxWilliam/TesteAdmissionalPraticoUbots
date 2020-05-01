package br.com.ubots.testeadmissionalpratico.model;

public class Cliente {
	private int id;
	private String nome;
	private String cpf;

	public Cliente() {

	}
	
	public Cliente(int id, String nome, String cpf) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
	}

	public void printCliente() {
		System.out.println("id: " + this.getId() + " nome: " + this.getNome() + " cpf: " + this.getCpf());
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	
}
