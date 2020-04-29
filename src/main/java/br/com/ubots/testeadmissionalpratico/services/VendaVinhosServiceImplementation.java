package br.com.ubots.testeadmissionalpratico.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.com.ubots.testeadmissionalpratico.dao.LeJSON;
import br.com.ubots.testeadmissionalpratico.model.Cliente;
import br.com.ubots.testeadmissionalpratico.model.Info;
import br.com.ubots.testeadmissionalpratico.model.Item;
import br.com.ubots.testeadmissionalpratico.model.Venda;

public class VendaVinhosServiceImplementation implements VendaVinhosService {
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
			mesclaInfo();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void mesclaInfo() { //"0000.000.000.02"
		for(Cliente cliente: this.listaClientes) {
			map.put(cliente.getCpf(), new Info(cliente, 0, new ArrayList<Venda>()));
		}
		for(Venda venda: listaVendas) {
			//System.out.println("cpf: "+ venda.getCpfCliente());
			Info m = map.get(venda.getCpfCliente());
			m.getListaVendas().add(venda);
			
			m.setTotalComprasCliente(m.getTotalComprasCliente()+ venda.getValorTotalVenda());
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
			System.out.println("Valor total dessa venda: " + venda.getValorTotalVenda() + "\n//  //  //");
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
	
	public <K, V> void ordenaPorTotalcomprasClienteDesc(Map<String, Info> mapVenda) {
		List<Map.Entry<String, Info>> entries = new ArrayList<>(mapVenda.entrySet());

		Collections.sort(entries, new Comparator<Map.Entry<String, Info>>() {
			@Override
			public int compare(Map.Entry<String, Info> a, Map.Entry<String, Info> b) {
				Info i1 = (Info) a.getValue();
				Info i2 = (Info) b.getValue();
				
				return (int)(i2.getTotalComprasCliente() - i1.getTotalComprasCliente());
			}
		});

		mapVenda.clear();
		for (Map.Entry<String, Info> e : entries) {
			mapVenda.put(e.getKey(), e.getValue());
		}
	}
	
	@Override
	public void maiorValorTotaldeComprasproCliente(){
		this.ordenaPorTotalcomprasClienteDesc(this.getMap());
		System.out.println("///////////////////////////////////////////////////////////////");
		this.printMap();
	}
	
	public void procuraMaiorCompraCliente(int ano) {
		for(String cpf : map.keySet()) {
			double valorMaiorCompradoCliente = 0;
			String codigoMaiorCompradoCliente = null;
			for( Venda v :map.get(cpf).getListaVendas()) {
				if(v.getData().getYear() == ano && v.getValorTotalVenda() > valorMaiorCompradoCliente) {
					codigoMaiorCompradoCliente = v.getCodigo();
					valorMaiorCompradoCliente = v.getValorTotalVenda();
				}
			}
			map.get(cpf).setCodigoMaiorCompradoCliente(codigoMaiorCompradoCliente);
			map.get(cpf).setValorMaiorCompraCliente(valorMaiorCompradoCliente);
		}
	}
	
	@Override
	public void clienteComMaiorCompraUnica(int ano) {
		procuraMaiorCompraCliente(ano);
		String cpfMax = null;
		double maxValorCompra = 0;
		String codigoMaiorCompra = null;
		for(String cpf : map.keySet()) {
			if(map.get(cpf).getValorMaiorCompraCliente() > maxValorCompra) {
				Info info = map.get(cpf); //info Por Cliente
				codigoMaiorCompra = info.getCodigoMaiorCompradoCliente();
				maxValorCompra = info.getValorMaiorCompraCliente();
				cpfMax = cpf; 
			}
		}
		
		System.out.println("Cliente: " + map.get(cpfMax).getCliente().getNome() + " CPF: " + cpfMax + " Compra código: " + codigoMaiorCompra + " Valor: " + maxValorCompra);
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<Venda> getListaVendas() {
		return listaVendas;
	}

	public void setListaVendas(List<Venda> listaVendas) {
		this.listaVendas = listaVendas;
	}

	public LinkedHashMap<String, Info> getMap() {
		return map;
	}

	public void setMap(LinkedHashMap<String, Info> map) {
		this.map = map;
	}
}
