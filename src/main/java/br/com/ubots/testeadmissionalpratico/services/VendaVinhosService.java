package br.com.ubots.testeadmissionalpratico.services;

public interface VendaVinhosService {
//	# 1 - Liste os clientes ordenados pelo maior valor total em compras.
	public void maiorValorTotaldeComprasproCliente(); 
//	# 2 - Mostre o cliente com maior compra única no último ano (2016).
	public void clienteComMaiorCompraUnica(int ano);
//	# 3 - Liste os clientes mais fiéis.
	
//	# 4 - Recomende um vinho para um determinado cliente a partir do histórico


}

/*
 		vinho 1 vinho 2 tipo 1 tipo 2 2009 2010
vinho 1    1              1            1            
vinho 2            1             1           1       
tipo 1     1              1            1            
tipo 2             1             1           1    
2009      1               1            1             
2010               1             1           1      
 */