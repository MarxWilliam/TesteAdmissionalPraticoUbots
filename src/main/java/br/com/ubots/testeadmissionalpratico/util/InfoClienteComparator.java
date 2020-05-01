package br.com.ubots.testeadmissionalpratico.util;

import java.util.Comparator;

import br.com.ubots.testeadmissionalpratico.model.Cliente;
import br.com.ubots.testeadmissionalpratico.model.Info;

public class InfoClienteComparator implements Comparator<Info> {

	@Override
	public int compare(Info arg0, Info arg1) {
		
		return (int) (arg1.getMontanteComprasUltimoAno() - arg0.getMontanteComprasUltimoAno());
	}

}
