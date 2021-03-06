package br.com.ubots.testeadmissionalpratico.services;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import br.com.ubots.testeadmissionalpratico.dao.LeJSON;
import br.com.ubots.testeadmissionalpratico.model.Cliente;
import br.com.ubots.testeadmissionalpratico.model.Info;
import br.com.ubots.testeadmissionalpratico.model.Vinho;
import br.com.ubots.testeadmissionalpratico.util.InfoClienteComparator;
import br.com.ubots.testeadmissionalpratico.util.VinhoComparator;
import br.com.ubots.testeadmissionalpratico.model.Categoria;
import br.com.ubots.testeadmissionalpratico.model.Celula;
import br.com.ubots.testeadmissionalpratico.model.Venda;

public class VendaVinhosServiceImplementation2 implements IVendaVinhosService {
	private List<Cliente> listaClientes;
	private List<Venda> listaVendas;
	private LinkedHashMap<String, Info> map;
	//private double mediaVendas = 0;
	//private double mediaValorVinhosVendidos = 0;
	////private double desvioPadraoMediaVendas = 0;
	//private double desvioPadraoMediaVinhos = 0;
	//private int countVendas = 0;
	//private int countVinhosVendidos = 0;

	public VendaVinhosServiceImplementation2() {
		this.map = new LinkedHashMap<>();
		try {
			File f = new File(".");
			File fileClientes = new File(f.getCanonicalPath() + "\\src\\resources\\598b16291100004705515ec5.json");
			LeJSON leJSON = new LeJSON();
			listaClientes = leJSON.leJSONCliente(fileClientes);
			//this.printClientes(listaClientes);

			File fileCompras = new File(f.getCanonicalPath() + "\\src\\resources\\598b16861100004905515ec7.json");
			listaVendas = leJSON.leJSONCompras(fileCompras);
			//this.printCompras();
			mesclaInfo();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void mesclaInfo() { // "0000.000.000.02"
		for (Cliente cliente : this.listaClientes) {
			map.put(cliente.getCpf(), new Info(cliente, 0, new ArrayList<Venda>()));
		}
		for (Venda venda : listaVendas) {
			// System.out.println("cpf: "+ venda.getCpfCliente());
			Info m = map.get(venda.getCpfCliente());
			m.getListaVendas().add(venda);

			m.setValorTotalComprasCliente(m.getValorTotalComprasCliente() + venda.getValorTotalVenda());
		}
	}
	
	public void calculaPotuacaoTotal(boolean print) {
		for (String cpf : map.keySet()) {
			System.out.print( print  ? map.get(cpf).getCliente().getNome() + "\n" : "");
			//System.out.println(map.get(cpf).getCliente().getNome());
			Info info = map.get(cpf);
			for (Venda v : info.getListaVendas()) {
				for(Vinho vinho:v.getItens()) {
					vinho.setPontuacao(calculaPontuacao(vinho, cpf));
					System.out.print(print ? vinho.getProduto() + " - " + vinho.getVariedade() + " - " + " - " + vinho.getPais() 
					+ " - "+ vinho.getCategoria() + " - " +  vinho.getSafra() + " - " + vinho.getPreco() + " - Peso: "
				    + vinho.getPontuacao() + "\n": "");
				}
				
			}
		}
		System.out.print(print? "\n":"");
	}

	@Override
	public void  recomendaVinho(String cpf, int quant) {
		montaTabelaSimples();
		calculaPotuacaoTotal(false);
		List<Vinho> listaVinhos = new LinkedList<>();
		for (Venda v : map.get(cpf).getListaVendas()) {
			for(Vinho vinho:v.getItens()) {
			    vinho.setPontuacao(calculaPontuacao(vinho, cpf));
			    listaVinhos.add(vinho);
			}	
		}
		Info info = map.get(cpf);
		info.calculaValorMediodeVinhoseDesvio(); //calcula media e desvio padrão do preço dos vinhos comprados
		double valor = info.getValorMedioVinhos() + info.getDesvioPadraoValorMedioVinhos();
		for (Vinho v : listaVinhos) {
			v.setDistancia(Math.abs(valor - v.getPreco()));
		}
		System.out.println("Quanto maior a pontuação e menor a distância do maior preço que o cliente costuma pagar melhor.");
		System.out.println("Media: " + info.getValorMedioVinhos() + " Desvio: " + info.getDesvioPadraoValorMedioVinhos() + " soma: " + (info.getValorMedioVinhos() + info.getDesvioPadraoValorMedioVinhos()) + "\n");
		Collections.sort(listaVinhos, new VinhoComparator());
		for (int i = 0; i < (quant < listaVinhos.size() ? quant : listaVinhos.size()); i++) {
			Vinho vinho = listaVinhos.get(i);
			System.out.println(vinho.getProduto() + " - " + vinho.getVariedade() + " - " + " - " + vinho.getPais() 
			+ " - "+ vinho.getCategoria() + " - " +  vinho.getSafra() + " - R$:" + vinho.getPreco() + " - Peso: " + vinho.getPontuacao() + "% Dist: " + vinho.getDistancia());		
		}
	
	}
	
	@Override
	public double calculaPontuacao(Vinho vinho, String cpf) {
		double soma = 0;
		Info info = map.get(cpf);
		info.setQuantidadeTotalVInhos(0);
		for (Venda v : info.getListaVendas()) {
			info.setQuantidadeTotalVInhos(info.getQuantidadeTotalVInhos()+v.getItens().size());
		}
		for (Celula c : info.getTabelaSimples()) {
				if(vinho.getProduto().equalsIgnoreCase(c.getTitulo()) && c.getCategoria().equalsIgnoreCase("vinho")) {
					soma += c.getPeso();
				}
				if(vinho.getVariedade().equalsIgnoreCase(c.getTitulo()) && c.getCategoria().equalsIgnoreCase("variedade")) {
					soma += c.getPeso();
				}
				if(vinho.getPais().equalsIgnoreCase(c.getTitulo()) && c.getCategoria().equalsIgnoreCase("pais")) {
					soma += c.getPeso();
				}
				if(vinho.getCategoria().equalsIgnoreCase(c.getTitulo()) && c.getCategoria().equalsIgnoreCase("categoria")) {
					soma += c.getPeso();
				}
				if(String.valueOf(vinho.getSafra()).equalsIgnoreCase(c.getTitulo()) && c.getCategoria().equalsIgnoreCase("safra")) {
					soma += c.getPeso();
				}
		}
		return (soma/(double)(info.getQuantidadeTotalVInhos()*5))*100.0;
	}

	public void procuraMaiorCompraTodososCliente(int ano) {
		for (String cpf : map.keySet()) {
			double valorMaiorCompradoCliente = 0;
			String codigoMaiorCompradoCliente = null;
			for (Venda v : map.get(cpf).getListaVendas()) {
				if (v.getData().getYear() == ano && v.getValorTotalVenda() > valorMaiorCompradoCliente) {
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
		try {
		procuraMaiorCompraTodososCliente(ano);
		String cpfMax = null;
		double maxValorCompra = 0;
		String codigoMaiorCompra = null;
		for (String cpf : map.keySet()) {
			if (map.get(cpf).getValorMaiorCompraCliente() > maxValorCompra) {
				Info info = map.get(cpf); // info Por Cliente
				codigoMaiorCompra = info.getCodigoMaiorCompradoCliente();
				maxValorCompra = info.getValorMaiorCompraCliente();
				cpfMax = cpf;
			}
		}

		System.out.println("Cliente: " + map.get(cpfMax).getCliente().getNome() + " CPF: " + cpfMax + " Compra código: "
				+ codigoMaiorCompra + " Valor: " + maxValorCompra);
		} catch(NullPointerException ex) {
			System.out.println("Esse ano não consta na base de dados.");
		}
	}

	@Override
	public void maiorValorTotaldeComprasporCliente() {
		this.ordenaPorTotalcomprasClienteDesc(this.getMap());
		//System.out.println("///////////////////////////////////////////////////////////////");
		//this.printMap();
		for(String cpf : map.keySet()) {
			Info c = map.get(cpf);
			System.out.println("Cliente: " + c.getCliente().getNome() + " CPF: " + cpf + " Montante: " + c.getValorTotalComprasCliente());
		}
	}
	
	public void selecionaListaClientesMaisFieis(ZonedDateTime ano) {
		//calculaMediaValorVendaseVinhoscomDesvio();
		System.out.println("Clientes mais Fieis: ");
		LinkedList<Info> clientesFieis = new LinkedList<>();
		double montante = 0;
		for (String cpf : map.keySet()) {
			Info info = map.get(cpf);
			for (Venda v : info.getListaVendas()) {
				if (v.getData().isAfter(ano.minusYears(1)) && v.getData().isBefore(ano)) {
					 info.setMontanteComprasUltimoAno(info.getMontanteComprasUltimoAno() + v.getValorTotalVenda());
					 //info.setQuantidadeItensUltimoAno(info.getQuantidadeItensUltimoAno() + v.getItens().size());
					 montante += v.getValorTotalVenda();
				}
			}
		}
		
		double media = montante/map.size();
		
		for (String cpf : map.keySet()) {
			if(map.get(cpf).getMontanteComprasUltimoAno()>media) {
				clientesFieis.add(map.get(cpf));
			}
		}
		
		Collections.sort(clientesFieis, new InfoClienteComparator());		
		
		System.out.println("Med. de Venda: "  + media + "Montante: " + montante);
		for(Info info : clientesFieis) {
			info.getCliente().printCliente();
			System.out.println("Montante: " + info.getMontanteComprasUltimoAno());
		}
	}
	
	public void printMap() {
		for (String cpf : map.keySet()) {
			map.get(cpf).printInfo();
		}
	}

	public void printClientes(List<Cliente> listaClientes) {
		for (Cliente cli : listaClientes) {
			System.out.println("id: " + cli.getId() + " nome: " + cli.getNome() + " cpf: " + cli.getCpf());
		}
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

	public <K, V> void ordenaPorTotalcomprasClienteDesc(Map<String, Info> mapVenda) {
		List<Map.Entry<String, Info>> entries = new ArrayList<>(mapVenda.entrySet());

		Collections.sort(entries, new Comparator<Map.Entry<String, Info>>() {
			@Override
			public int compare(Map.Entry<String, Info> a, Map.Entry<String, Info> b) {
				Info i1 = (Info) a.getValue();
				Info i2 = (Info) b.getValue();

				return (int) (i2.getValorTotalComprasCliente() - i1.getValorTotalComprasCliente());
			}
		});

		mapVenda.clear();
		for (Map.Entry<String, Info> e : entries) {
			mapVenda.put(e.getKey(), e.getValue());
		}
	}
	
	public void procuraMaiorCompraCliente(String cpf, int ano) {
		double valorMaiorCompradoCliente = 0;
		String codigoMaiorCompradoCliente = null;
		for (Venda v : map.get(cpf).getListaVendas()) {
			if (v.getData().getYear() == ano && v.getValorTotalVenda() > valorMaiorCompradoCliente) {
				codigoMaiorCompradoCliente = v.getCodigo();
				valorMaiorCompradoCliente = v.getValorTotalVenda();
			}
		}
		map.get(cpf).setCodigoMaiorCompradoCliente(codigoMaiorCompradoCliente);
		map.get(cpf).setValorMaiorCompraCliente(valorMaiorCompradoCliente);
	
}

	
	public void contabilizaVinhos() {

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

	public void printTabela() {
		for (String cpf : map.keySet()) {
			System.out.println(map.get(cpf).getCliente().getNome());
			System.out.println();
			for (Celula c : map.get(cpf).getTabelaSimples()) {
				System.out.println("Título: " + c.getTitulo() + "\tCategoria: " + c.getCategoria() + " Peso = " + c.getPeso());
			}
			System.out.println();
		}
	}

	public void montaTabelaSimples() {
		for (String cpf : map.keySet()) {
			map.get(cpf).setTabelaSimples(new LinkedList<Celula>());

			// é feita inserção pra cada categoria isso permite que exista nome de produto e
			// categoria iguais
			for (Venda v : map.get(cpf).getListaVendas()) {
				for (Vinho vinho : v.getItens()) {
					boolean vinhoExistente = false;
					boolean paisExistente = false;
					boolean variedadeExistente = false;
					boolean categoriaExistente = false;
					boolean safraExistente = false;
					
					for (Celula c : map.get(cpf).getTabelaSimples()) {
						if (c.getTitulo().equalsIgnoreCase(vinho.getProduto())
								&& c.getCategoria().equalsIgnoreCase("vinho")) {
							vinhoExistente = true;
						}
						if (c.getTitulo().equalsIgnoreCase(vinho.getVariedade())
								&& c.getCategoria().equalsIgnoreCase("variedade")) {
							variedadeExistente = true;
						}
						if (c.getTitulo().equalsIgnoreCase(vinho.getPais())
								&& c.getCategoria().equalsIgnoreCase("pais")) {
							paisExistente = true;
						}
						if (c.getTitulo().equalsIgnoreCase(vinho.getCategoria())
								&& c.getCategoria().equalsIgnoreCase("categoria")) {
							categoriaExistente = true;
						}
						if (c.getTitulo().equalsIgnoreCase(String.valueOf(vinho.getSafra()))
								&& c.getCategoria().equalsIgnoreCase("safra")) {
							safraExistente = true;
						}
					}
					if (!vinhoExistente) {
						map.get(cpf).getTabelaSimples().add(new Celula(vinho.getProduto(), "vinho", 0));
					}
					if (!variedadeExistente) {
						map.get(cpf).getTabelaSimples().add(new Celula(vinho.getVariedade(), "variedade", 0));
					}
					if (!paisExistente) {
						map.get(cpf).getTabelaSimples().add(new Celula(vinho.getPais(), "pais", 0));
					}
					if (!categoriaExistente) {
						map.get(cpf).getTabelaSimples().add(new Celula(vinho.getCategoria(), "categoria", 0));
					}
					if (!safraExistente) {
						map.get(cpf).getTabelaSimples().add(new Celula(String.valueOf(vinho.getSafra()), "safra", 0));
					}
				}
			}
		}
		for (String cpf : map.keySet()) {
			for (Venda v : map.get(cpf).getListaVendas()) {
				for (Vinho vinho : v.getItens()) {
					for (Celula c : map.get(cpf).getTabelaSimples()) {
						if (c.getTitulo().equalsIgnoreCase(vinho.getProduto())
								&& c.getCategoria().equalsIgnoreCase("vinho")) {
								if (vinho.getProduto().equalsIgnoreCase(c.getTitulo())) {
									c.setPeso(c.getPeso() + 1);
								}
						}
						if (c.getTitulo().equalsIgnoreCase(vinho.getVariedade())
								&& c.getCategoria().equalsIgnoreCase("variedade")) {
								if (vinho.getVariedade().equalsIgnoreCase(c.getTitulo())) {
									c.setPeso(c.getPeso() + 1);
								}
						}
						if (c.getTitulo().equalsIgnoreCase(vinho.getPais())
								&& c.getCategoria().equalsIgnoreCase("pais")) {
								if (vinho.getPais().equalsIgnoreCase(c.getTitulo())) {
									c.setPeso(c.getPeso() + 1);
								}
						}
						if (c.getTitulo().equalsIgnoreCase(vinho.getCategoria())
								&& c.getCategoria().equalsIgnoreCase("categoria")) {
								if (vinho.getCategoria().equalsIgnoreCase(c.getTitulo())) {
									c.setPeso(c.getPeso() + 1);
								}
						}
						if (c.getTitulo().equalsIgnoreCase(String.valueOf(vinho.getSafra()))
								&& c.getCategoria().equalsIgnoreCase("safra")) {
								if (String.valueOf(vinho.getSafra()).equalsIgnoreCase(c.getTitulo())) {
									c.setPeso(c.getPeso() + 1);
								}
						}
					}
				}
			}
		}
	}
}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		