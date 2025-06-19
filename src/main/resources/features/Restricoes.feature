#language: pt
#encoding: UTF-8

@restricoes
Funcionalidade: Restricoes

  Esquema do Cenario: Consultar CPF com restricao
    Dado que o CPF que desejo consultar seja <CPF>
    Entao o status code ao informar um CPF com restricao deve ser 200
    
 	Exemplos:
 	|CPF				  |
 	|"97093236014"|
 	|"60094146012"|
 	|"84809766080"|
 	|"62648716050"|
 	|"26276298085"|
 	|"01317496094"|
 	|"55856777050"|
 	|"19626829001"|
 	|"24094592008"|
 	|"58063164083"|
 	
	Esquema do Cenario: Consultar CPF sem restricao
    Dado que o CPF que desejo consultar seja <CPF>
    Entao o status code ao informar um CPF sem restricao deve ser 204
    
  Exemplos:
 	|CPF				  |
 	|"38011928066"|
 	|"47898076060"|
 	|"75140230038"|

 	
    
    
    
