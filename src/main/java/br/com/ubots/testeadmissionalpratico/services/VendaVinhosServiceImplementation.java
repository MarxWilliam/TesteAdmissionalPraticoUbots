package br.com.ubots.testeadmissionalpratico.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import br.com.ubots.testeadmissionalpratico.dao.LeJSON;
import br.com.ubots.testeadmissionalpratico.model.Cliente;
import br.com.ubots.testeadmissionalpratico.model.Info;
import br.com.ubots.testeadmissionalpratico.model.Item;
import br.com.ubots.testeadmissionalpratico.model.Venda;

public class VendaVinhosServiceImplementation implements VendaVinhosService {

	private static final int ANDARES = 16; // contando de 0 a 15 são 16 andares.
	private static final int ELEVADORES = 5;

	List<Cliente> listaClientes;
	List<Venda> listaVendas;
	LinkedHashMap<String, Info> map;

	public VendaVinhosServiceImplementation() {
		this.map = new LinkedHashMap<>();
		try {
			File f = new File(".");
			File fileClientes = new File(f.getCanonicalPath() + "\\src\\resources\\598b16291100004705515ec5.json");
			LeJSON leJSON = new LeJSON();
			listaClientes = leJSON.leJSONCliente(fileClientes);
			this.printClientes(listaClientes);
			
			File fileCompras = new File(f.getCanonicalPath() + "\\src\\resources\\598b16861100004905515ec7.json");
			listaVendas = leJSON.leJSONCompras(fileCompras);
			this.printCompras(listaVendas);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void mesclaInfo() { //"0000.000.000.02"
		for(Cliente cliente: this.listaClientes) {
			map.put(cliente.getCpf(), new Info(cliente, 0, new ArrayList<Venda>()));
		}
		for(Venda venda: listaVendas) {
			System.out.println("cpf: "+ venda.getCpfCliente());
			Info m = map.get(venda.getCpfCliente());
			m.getListaVendas().add(venda);
			
			m.setTotalComprasCliente(m.getTotalComprasCliente()+ venda.getValorTotal());
		}
	}
	
	public void printMap() {
		for(String cpf : map.keySet()) {
			printInfo(map.get(cpf));
		}
	}
	
	public void printInfo(Info info) {
		System.out.println("// - // - //    // - // - //    // - // - //");
		printCliente(info.getCliente());
		System.out.println("// - // - //");
		printCompras(info.getListaVendas());
		System.out.println("Total compras do cliente: " + info.getTotalComprasCliente());
		System.out.println("// - // - //    // - // - //    // - // - //\n\n");
	}
	
	private void printCompras(List<Venda> listaVendas) {
		for(Venda venda:listaVendas) {
			System.out.println("Código: " + venda.getCodigo() + " Data: "+ venda.getData() + " Cliente: " + venda.getCpfCliente());
			for(Item item:venda.getItens()) {
				System.out.println("\tProduto: " + item.getProduto() + " Variedade: " + item.getVariedade() + " País: " + item.getPais() + " Categoria: " + item.getCategoria() + " Safra: " + item.getSafra() + " Preço: "+ item.getPreco());
			}
			System.out.println("Valor total dessa venda: " + venda.getValorTotal() + "\n//  //  //");
		}
		
	}

	public void printCliente(Cliente cliente) {
		System.out.println("id: " + cliente.getId() + " nome: " + cliente.getNome() + " cpf: " + cliente.getCpf());
	}

	
	public void printClientes(List<Cliente> listaClientes) {
		for(Cliente cli:listaClientes) {
			System.out.println("id: " + cli.getId() + " nome: " + cli.getNome() + " cpf: " + cli.getCpf());
		}
	}
	
}
