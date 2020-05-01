package br.com.ubots.testeadmissionalpratico.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import br.com.ubots.testeadmissionalpratico.model.Cliente;
import br.com.ubots.testeadmissionalpratico.model.Vinho;
import br.com.ubots.testeadmissionalpratico.model.Venda;
import br.com.ubots.testeadmissionalpratico.util.DataPadrao;

public class LeJSON {	
	public List<Cliente> leJSONCliente(File file) {

		JSONObject jsonObject = new JSONObject();
		JSONParser parser = new JSONParser();

		List<Cliente> listaInfo = new ArrayList<Cliente>();

		try {
			Object json = (Object) parser.parse(new FileReader(file));

			JSONArray array = (JSONArray) json;

			Iterator it = array.iterator();
			while (it.hasNext()) {
				listaInfo.add(parseInfo((JSONObject) it.next()));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return listaInfo;
	}

	public Cliente parseInfo(JSONObject infoJson) {
		Cliente tmpInfo = new Cliente();
		tmpInfo.setId(Integer.parseInt(infoJson.get("id").toString()));
		tmpInfo.setNome(infoJson.get("nome").toString());
		tmpInfo.setCpf(trataCpf(infoJson.get("cpf").toString()));

		return tmpInfo;
	}
	
	public List<Venda> leJSONCompras(File file) {

		//JSONObject jsonObject = new JSONObject();
		JSONParser parser = new JSONParser();

		List<Venda> listaInfo = new ArrayList<Venda>();

		try {
			Object json = (Object) parser.parse(new FileReader(file));

			JSONArray array = (JSONArray) json;

			Iterator it = array.iterator();
			while (it.hasNext()) {
				listaInfo.add(parseVendas((JSONObject) it.next()));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return listaInfo;
	}
	
	public Venda parseVendas(JSONObject infoJson) {
		Venda tmpInfo = new Venda();
		tmpInfo.setCodigo(infoJson.get("codigo").toString());
		String data = infoJson.get("data").toString();
		tmpInfo.setData(DataPadrao.parse(data));
		tmpInfo.setCpfCliente(trataCpf(infoJson.get("cliente").toString()));
		tmpInfo.setValorTotalVenda(Double.parseDouble(infoJson.get("valorTotal").toString()));
		tmpInfo.setItens(parseVendasItens((JSONArray)infoJson.get("itens")));
		return tmpInfo;
	}
	
	public List<Vinho> parseVendasItens(JSONArray array) {
		List<Vinho> lista = new ArrayList<>();
		
		Iterator it = array.iterator();
		while (it.hasNext()) {
			lista.add(parseVendasItem((JSONObject) it.next()));
		}
	
		return lista;
	}
	public Vinho parseVendasItem(JSONObject infoJson) {
		Vinho tmpInfo = new Vinho();
		tmpInfo.setProduto(infoJson.get("produto").toString());
		tmpInfo.setVariedade(infoJson.get("variedade").toString());
		tmpInfo.setPais(infoJson.get("pais").toString());
		tmpInfo.setCategoria(infoJson.get("categoria").toString());
		tmpInfo.setSafra(Integer.parseInt(infoJson.get("safra").toString()));
		tmpInfo.setPreco(Double.parseDouble(infoJson.get("preco").toString()));

		return tmpInfo;
	}
	
	public String trataCpf(String cpf) {
		cpf = cpf.replaceAll("\\.|-", "");
		if(cpf.length() < 11) {
			StringBuilder str = new StringBuilder();
			for(int i = 0; i < 11-cpf.length(); i++) {
				str.append("0");
			}
			str.append(cpf);
			
			return str.toString();
			
		} else if(cpf.length() > 11) {
			
			return cpf.substring(cpf.length()-11);
			
		} else {
			
			return cpf;
		}
	}
}
