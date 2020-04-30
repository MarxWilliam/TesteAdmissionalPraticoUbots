package br.com.ubots.testeadmissionalpratico.util;

import java.util.Comparator;

import br.com.ubots.testeadmissionalpratico.model.Vinho;

public class VinhoComparator implements Comparator<Vinho>{

	@Override
	public int compare(Vinho v1, Vinho v2) {
		if(v1.getPontuacao()>v2.getPontuacao()) {
			if(v1.getDistancia()>v2.getDistancia()) {
				return -2;
			} else if(v1.getDistancia()<v2.getDistancia()) {
				return -4;
			} else {
				return -3;
			}
		} else if(v1.getPontuacao()<v2.getPontuacao()) {
			if(v1.getDistancia()>v2.getDistancia()) {
				return 4; 
			} else if(v1.getDistancia()<v2.getDistancia()) {
				return 2;
			} else {
				return 3;
			}
		} else {
			if(v1.getDistancia()>v2.getDistancia()) {
				return 1;
			} else if(v1.getDistancia()<v2.getDistancia()) {
				return -1;
			} else {
				return 0;	
			}
		}
		
		
		
	}

	
}
