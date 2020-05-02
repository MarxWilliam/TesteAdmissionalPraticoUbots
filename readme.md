
	//	# 1 - Liste os clientes ordenados pelo maior valor total em compras.
	public void maiorValorTotaldeComprasporCliente(); 

	//	# 2 - Mostre o cliente com maior compra única no último ano (2016).
	public void clienteComMaiorCompraUnica(int ano);

	//	# 3 - Liste os clientes mais fiéis.
	public void selecionaListaClientesMaisFieis(ZonedDateTime ano); //"dd-MM-yyyy" DataPadrao.parse("15-05-2016")

	//	# 4 - Recomende um vinho para um determinado cliente a partir do histórico
	public void  recomendaVinho(String cpf, int quant); //cpf do cliente e quantidade de recomendações.



#Grafo de compras

compras de fulano:
	Vinho 1 tipo 1 2009
	Vinho 2 tipo 2 2010

		vinho 1 vinho 2 tipo 1 tipo 2 2009 2010
vinho 1    1              1            1            
vinho 2            1             1           1       
tipo 1     1              1            1            
tipo 2             1             1           1    
2009       1              1            1             
2010               1             1           1      


Algoritmo de ordenação das recomendações:

	> <  -4
	> =  -3
	> >  -2
	= <  -1
	= =  0
	= >  1
	< <  2
	< =  3
	< >  4

Clientes mais fiéis:

montante valor das compras/quantidade compras;