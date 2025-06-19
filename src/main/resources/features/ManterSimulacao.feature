#language: pt
#encoding: UTF-8

@simulacoes
Funcionalidade: Restricoes


  #Criar simulacao
  
  Cenario: criar simulacao
    Dado que o usuario cadastre uma simulacao com o CPF valido o nome "Carlos" o email "teste@teste.com" o valor "2000.02" a quantidade de parcelas 36 o seguro "sim"
    Entao o status code ao criar uma simulacao com sucesso deve ser 201
    
  Cenario: criar simulacao cpf com mascara
    Dado que o usuario cadastre uma simulacao com o CPF com mascara o nome "Carlos" o email "teste@teste.com" o valor "2000.02" a quantidade de parcelas 36 o seguro "sim"
    Entao o status code ao criar uma simulacao com CPF com mascara deve ser 400
    
  Cenario: criar simulacao sem informar campos obrigatorio
    Dado que o usuario cadastre uma simulacao informando apenas o seguro "sim"
    Entao o status code ao criar uma simulacao sem campos obrigatorio deve ser 400 e apresenta as mensagens "Parcelas não pode ser vazio" "Valor não pode ser vazio" "CPF não pode ser vazio" "Nome não pode ser vazio" "E-mail não deve ser vazio"
  @now
  @esperoErro
  Cenario: criar simulacao sem informar campo seguro
    Dado que o usuario cadastre uma simulacao com o CPF com mascara o nome "Carlos" o email "email@email.com" o valor "1200" a quantidade de parcelas 3
    Entao o status code ao criar uma simulacao sem campo seguro deve ser 400 e apresenta as mensagens "Seguro não pode ser vazio"
     #erro esperado pois o sistema retorna 500 ao nao informar o campo seguro ao inves de retornar 400 e a mensagem informando que campo seguro é obrigatorio
  
  @now
  @esperoErro
  Cenario: criar simulacao valor menor que mil
    Dado que o usuario cadastre uma simulacao com o CPF valido o nome "Carlos" o email "teste@teste.com" o valor "999" a quantidade de parcelas 5 o seguro "sim"
    Entao o status code ao criar uma simulacao com valor menor que mil deve ser 400 e apresentar a mensagem "Valor deve ser maior ou igual a R$ 1.000"
    #erro esperado pois o documento informa que se algum dado estiver errado o sistema deve retornar statusCode 400, porem o sistema esta aceitando valo menor que 1000, assim retornando 201
  
  Cenario: criar simulacao valor maior que 40 mil
    Dado que o usuario cadastre uma simulacao com o CPF valido o nome "Carlos" o email "teste@teste.com" o valor "40001" a quantidade de parcelas 12 o seguro "nao"
    Entao o status code ao criar uma simulacao com valor maior que quarenta mil deve ser 400 e apresentar a mensagem "Valor deve ser menor ou igual a R$ 40.000"
  
  Cenario: criar simulacao CPF ja cadastrado
    Dado que o usuario cadastre uma simulacao com o CPF ja cadastrado o nome "Carlos" o email "teste@teste.com" o valor "3000" a quantidade de parcelas 36 o seguro "sim"
    Entao o status code ao criar uma simulacao com CPF duplicado deve ser 400 e apresentar a mensagem "CPF duplicado"
    #status deveria ser 409 de acordo com documento, mas sistema responde 400
    
  Cenario: criar simulacao numero de parcelas menor que 2
    Dado que o usuario cadastre uma simulacao com o CPF valido o nome "Carlos" o email "teste@teste.com" o valor "3000" a quantidade de parcelas 1 o seguro "sim"
    Entao o status code ao criar uma simulacao com parcela menor que dois deve ser 400 e apresentar a mensagem "Parcelas deve ser igual ou maior que 2"
   
  @esperoErro
  Cenario: criar simulacao numero de parcelas maior que 48
    Dado que o usuario cadastre uma simulacao com o CPF valido o nome "Carlos" o email "teste@teste.com" o valor "3000" a quantidade de parcelas 49 o seguro "sim"
    Entao o status code ao criar uma simulacao com parcela maior que quarenta e oito deve ser 400 e apresentar a mensagem "Parcelas deve ser igual ou menor que 48"
    #erro esperado pois o documento informa que se algum dado estiver errado o sistema deve retornar statusCode 400, porem o sistema esta aceitando parcela maior que 48, assim retornando 201

	#Alterar simulacao

	Cenario: Alterar simulacao
    Dado que o usuario altere uma simulacao com o CPF valido o nome "Nome alterado" o email "teste@alterado.com" o valor "7000" a quantidade de parcelas 24 o seguro "sim"
    Entao o status code ao alterar uma simulacao com sucesso deve ser 200
 	
 	@esperoErro
 	Cenario: alterar simulacao valor menor que mil
		Dado que o usuario altere uma simulacao com o CPF valido o nome "Nome alterado" o email "teste@alterado.com" o valor "999" a quantidade de parcelas 24 o seguro "sim"
    Entao o status code ao alterar uma simulacao com valor menor que mil deve ser 400 e apresentar a mensagem "Valor deve ser maior ou igual a R$ 1.000"
    #erro esperado pois o documento informa que se algum dado estiver errado o sistema deve retornar statusCode 400, porem o sistema esta aceitando valo menor que 1000, assim retornando 200
    
  @esperoErro
  Cenario: alterar simulacao valor maior que 40 mil
    Dado que o usuario altere uma simulacao com o CPF valido o nome "Nome alterado" o email "teste@alterado.com" o valor "4000.01" a quantidade de parcelas 24 o seguro "sim"
    Entao o status code ao alterar uma simulacao com valor maior que quarenta mil deve ser 400 e apresentar a mensagem "Valor deve ser menor ou igual a R$ 40.000"  
		#erro esperado pois atributo valor nao esta sendo alterado, assim retornando statusCode 200 (todos os dados sao alterados menos o valor)

	Cenario: alterar simulacao numero de parcelas menor que 2
   	Dado que o usuario altere uma simulacao com o CPF valido o nome "Nome alterado" o email "teste@alterado.com" o valor "4000.01" a quantidade de parcelas 0 o seguro "sim"
    Entao o status code ao alterar uma simulacao com parcela menor que dois deve ser 400 e apresentar a mensagem "Parcelas deve ser igual ou maior que 2"
   
  @esperoErro
  Cenario: alterar simulacao numero de parcelas maior que 48
    Dado que o usuario altere uma simulacao com o CPF valido o nome "Nome alterado" o email "teste@alterado.com" o valor "4000.01" a quantidade de parcelas 49 o seguro "sim"
    Entao o status code ao alterar uma simulacao com parcela maior que quarenta e oito deve ser 400 e apresentar a mensagem "Parcelas deve ser igual ou menor que 48"
    #erro esperado pois o documento informa que se algum dado estiver errado o sistema deve retornar statusCode 400, porem o sistema esta aceitando parcela maior que 48, assim retornando 200
    
    
  #Consultar simulacoes	
  
	Cenario: Consultar todas as simulacoes cadastradas
    Dado que o usuario deseja consultar todas as simulacoes
    Entao o status code ao pesquisar por todas as simulacoes deve ser 200 e retornar a lista com uma ou mais simulacoes cadastradas
    
  Cenario: Consultar uma simulacao pelo CPF
    Dado que o usuario deseja consultar uma simulacao por CPF
    Entao o status code ao pesquisar por um CPF existente deve ser 200 e retornar os dados referentes ao CPF consultado
    
  Cenario: Consultar uma simulacao por CPF inexistente
    Dado que o usuario deseja consultar uma simulacao por um CPF nao cadastrado "91952076005"
    Entao o status code ao pesquisar por um CPF inexistente deve ser 404 e apresenta a mensagem "CPF 91952076005 não encontrado"
    
  #Remover uma simulacao

  Cenario: Remover uma simulacao
    Dado que o usuario deseja remover uma simulacao que possua o id existente
    Entao o status code ao remover uma solicitacao com sucesso deve ser 200
    #de acordo com o documento deveria ser 204 porem o sistema retorna 200 como sucesso
  
  @esperoErro
  Cenario: Remover usuario inexistente
    Dado que o usuario deseja remover uma simulacao inexistente que possua o id "00"
    Entao o status code ao remover uma solicitacao inexistente deve ser 404 e apresenta a mensagem "Simulação não encontrada"
    #erro esperedo, pois mesmo o id nao existindo a aplicacao retorna sucesso 200
  