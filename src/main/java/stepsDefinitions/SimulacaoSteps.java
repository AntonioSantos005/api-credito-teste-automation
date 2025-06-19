package stepsDefinitions;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import model.SimulacaoModel;
import singleton.SimulacaoSingleton;
import testAPI.SimulacaoTest;

import static utils.Utils.*;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

//classe que realiza a comunicacao entre a feature e a classe de tests

public class SimulacaoSteps {
	
	//recebendo a instancia dos atributos 
	SimulacaoModel simulacao = SimulacaoSingleton.getInstance();
	
	//Criando o objeto da classe que executa as requisicoes
	SimulacaoTest simulacaoTest = new SimulacaoTest();
	
	@Dado("^que o usuario cadastre uma simulacao com o CPF valido o nome \"([^\"]*)\" o email \"([^\"]*)\" o valor \"([^\"]*)\" a quantidade de parcelas (\\d+) o seguro \"([^\"]*)\"$")
	public void queOUsuarioCadastreUmaSimulacaoComOCPFValidoONomeOEmailOValorAQuantidadeDeParcelasOSeguro(String nome, String email, String valor, int parcelas, 
			String seguro) throws Throwable {
		
		simulacao.setCpf(gerarCPF());
		simulacao.setNome(nome);
		simulacao.setEmail(email);
		simulacao.setValor(valor);
		simulacao.setParcelas(parcelas);

		if(seguro.equals("sim")) {
			simulacao.setSeguro(true);
		}
		
		if(seguro.equals("nao")) {
			simulacao.setSeguro(false);
		}
	    
	}
	
	@Dado("^que o usuario cadastre uma simulacao com o CPF com mascara o nome \"([^\"]*)\" o email \"([^\"]*)\" o valor \"([^\"]*)\" a quantidade de parcelas (\\d+) o seguro \"([^\"]*)\"$")
	public void queOUsuarioCadastreUmaSimulacaoComOCPFComMascaraONomeOEmailOValorAQuantidadeDeParcelasOSeguro(
			String nome, String email, String valor, int parcelas, String seguro) throws Throwable {
		
		simulacao.setCpf("491.012.040-84");
		simulacao.setNome(nome);
		simulacao.setEmail(email);
		simulacao.setValor(valor);
		simulacao.setParcelas(parcelas);

		if(seguro.equals("sim")) {
			simulacao.setSeguro(true);
		}
		
		if(seguro.equals("nao")) {
			simulacao.setSeguro(false);
		}
		
	}
	
	@Dado("^que o usuario cadastre uma simulacao informando apenas o seguro \"([^\"]*)\"$")
	public void queOUsuarioCadastreUmaSimulacaoInformandoApenasOSeguro(String seguro) throws Throwable {
		if(seguro.equals("sim")) {
			simulacao.setSeguro(true);
		}
		
		if(seguro.equals("nao")) {
			simulacao.setSeguro(false);
		}
	}

	@Dado("^que o usuario cadastre uma simulacao com o CPF com mascara o nome \"([^\"]*)\" o email \"([^\"]*)\" o valor \"([^\"]*)\" a quantidade de parcelas (\\d+)$")
	public void queOUsuarioCadastreUmaSimulacaoComOCPFComMascaraONomeOEmailOValorAQuantidadeDeParcelas(String nome,
			String email, String valor, int parcelas) throws Throwable {
		
		simulacao.setCpf(gerarCPF());
		simulacao.setNome(nome);
		simulacao.setEmail(email);
		simulacao.setValor(valor);
		simulacao.setParcelas(parcelas);
	}

	@Entao("^o status code ao criar uma simulacao com sucesso deve ser (\\d+)$")
	public void oStatusCodeAoCriarUmaSimulacaoComSucessoDeveSer(int statusCode) throws Throwable {
		simulacao.setStatusCode(statusCode);
		
		simulacaoTest.criarUmaSimulacaoSucesso();
	}
	
	@Entao("^o status code ao criar uma simulacao com CPF com mascara deve ser (\\d+)$")
	public void oStatusCodeAoCriarUmaSimulacaoComCPFComMascaraDeveSer(int statusCode) throws Throwable {
		simulacao.setStatusCode(statusCode);
		
		simulacaoTest.criarUmaSimulacaoCPFComMascara();
	}
	
	@Entao("^o status code ao criar uma simulacao sem campos obrigatorio deve ser (\\d+) e apresenta as mensagens \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void oStatusCodeAoCriarUmaSimulacaoSemCamposObrigatorioDeveSerEApresentaAsMensagens(int statusCode, String expectedMessage1, String expectedMessage2, String expectedMessage3, 
			String expectedMessage4, String expectedMessage5) throws Throwable {
		
		simulacao.setStatusCode(statusCode);
		
		simulacaoTest.criarUmaSimulacaoSemCamposObrigatorio(expectedMessage1, expectedMessage2 ,expectedMessage3 ,expectedMessage4 ,expectedMessage5);
	}

	@Entao("^o status code ao criar uma simulacao sem campo seguro deve ser (\\d+) e apresenta as mensagens \"([^\"]*)\"$")
	public void oStatusCodeAoCriarUmaSimulacaoSemCampoSeguroDeveSerEApresentaAsMensagens(int statusCode, String expectedMessage)
			throws Throwable {
		simulacao.setStatusCode(statusCode);
		
		simulacaoTest.criarUmaSimulacaoSemCampoSeguro(expectedMessage);
	}
	
	@Dado("^que o usuario cadastre uma simulacao com o CPF ja cadastrado o nome \"([^\"]*)\" o email \"([^\"]*)\" o valor \"([^\"]*)\" a quantidade de parcelas (\\d+) o seguro \"([^\"]*)\"$")
	public void queOUsuarioCadastreUmaSimulacaoComOCPFJaCadastradoONomeOEmailOValorAQuantidadeDeParcelasOSeguro(
			String nome, String email, String valor, int parcelas, String seguro) throws Throwable {
		
		int tamanhoTexto = lerUltimoRegistroCadastrado(caminhoProjeto()+"\\src\\main\\resources\\Dados\\IDsECPFsCadastrados.txt").length();
		simulacao.setCpf(lerUltimoRegistroCadastrado(caminhoProjeto()+"\\src\\main\\resources\\Dados\\IDsECPFsCadastrados.txt").substring(tamanhoTexto-11, tamanhoTexto)); //busca o ultimo cpf cadastrado que foi gravado no arquivo txt);
		simulacao.setNome(nome);
		simulacao.setEmail(email);
		simulacao.setValor(valor);
		simulacao.setParcelas(parcelas);

		if(seguro.equals("sim")) {
			simulacao.setSeguro(true);
		}
		
		if(seguro.equals("nao")) {
			simulacao.setSeguro(false);
		}
	}

	@Entao("^o status code ao criar uma simulacao com CPF duplicado deve ser (\\d+) e apresentar a mensagem \"([^\"]*)\"$")
	public void oStatusCodeAoCriarUmaSimulacaoComCPFDuplicadoDeveSerEApresentarAMensagem(int statusCode, String espectedMessage) {
		simulacao.setStatusCode(statusCode);
		
		simulacaoTest.criarUmaSimulacaoCPFDuplicado(espectedMessage);
	}
	
	@Entao("^o status code ao criar uma simulacao com valor menor que mil deve ser (\\d+) e apresentar a mensagem \"([^\"]*)\"$")
	public void oStatusCodeAoCriarUmaSimulacaoComValorMenorQueMilDeveSerEApresentarAMensagem(int statusCode, String espectedMessage)
			throws Throwable {
		
		simulacao.setStatusCode(statusCode);
		
		simulacaoTest.criarUmaSimulacaoValorMenorQueMil(espectedMessage);
	}

	@Entao("^o status code ao criar uma simulacao com valor maior que quarenta mil deve ser (\\d+) e apresentar a mensagem \"([^\"]*)\"$")
	public void oStatusCodeAoCriarUmaSimulacaoComValorMaiorQueQuarentaMilDeveSerEApresentarAMensagem(int statusCode, String expectedMessage)
			throws Throwable {
	
		simulacao.setStatusCode(statusCode);
		
		simulacaoTest.criarUmaSimulacaoValorMaior40Mil(expectedMessage);
	}

	@Entao("^o status code ao criar uma simulacao com parcela menor que dois deve ser (\\d+) e apresentar a mensagem \"([^\"]*)\"$")
	public void oStatusCodeAoCriarUmaSimulacaoComParcelaMenorQueDoisDeveSerEApresentarAMensagem(int statusCode, String expectedMessage)
			throws Throwable {
		simulacao.setStatusCode(statusCode);
		
		simulacaoTest.criarUmaSimulacaoParcelaMenorQue2(expectedMessage);
	}

	@Entao("^o status code ao criar uma simulacao com parcela maior que quarenta e oito deve ser (\\d+) e apresentar a mensagem \"([^\"]*)\"$")
	public void oStatusCodeAoCriarUmaSimulacaoComParcelaMaiorQueQuarentaEOitoDeveSerEApresentarAMensagem(int statusCode,
			String expectedMessage) throws Throwable {
		
		simulacao.setStatusCode(statusCode);
		
		simulacaoTest.criarUmaSimulacaoParcelaMeaiorQue48(expectedMessage);
	}

	@Dado("^que o usuario altere uma simulacao com o CPF valido o nome \"([^\"]*)\" o email \"([^\"]*)\" o valor \"([^\"]*)\" a quantidade de parcelas (\\d+) o seguro \"([^\"]*)\"$")
	public void queOUsuarioAltereUmaSimulacaoComOCPFValidoONomeOEmailOValorAQuantidadeDeParcelasOSeguro(String nome,
			String email, String valor, int parcelas, String seguro) throws Throwable {
		int tamanhoTexto = lerUltimoRegistroCadastrado(caminhoProjeto()+"\\src\\main\\resources\\Dados\\IDsECPFsCadastrados.txt").length();
		simulacao.setCpf(lerUltimoRegistroCadastrado(caminhoProjeto()+"\\src\\main\\resources\\Dados\\IDsECPFsCadastrados.txt").substring(tamanhoTexto-11, tamanhoTexto)); //busca o ultimo cpf cadastrado que foi gravado no arquivo txt);
		simulacao.setNome(nome);
		simulacao.setEmail(email);
		simulacao.setValor(valor);
		simulacao.setParcelas(parcelas);

		if(seguro.equals("sim")) {
			simulacao.setSeguro(true);
		}
		
		if(seguro.equals("nao")) {
			simulacao.setSeguro(false);
		}
	}

	@Entao("^o status code ao alterar uma simulacao com sucesso deve ser (\\d+)$")
	public void oStatusCodeAoAlterarUmaSimulacaoComSucessoDeveSer(int statusCode) throws Throwable {
		simulacao.setStatusCode(statusCode);
		
		simulacaoTest.alterarUmaSimulacaoSucesso();
	}

	@Entao("^o status code ao alterar uma simulacao com valor menor que mil deve ser (\\d+) e apresentar a mensagem \"([^\"]*)\"$")
	public void oStatusCodeAoAlterarUmaSimulacaoComValorMenorQueMilDeveSerEApresentarAMensagem(int statusCode, String expectedMessage)
			throws Throwable {

		simulacao.setStatusCode(statusCode);
		
		simulacaoTest.alterarUmaSimulacaoValorMenorQueMil(expectedMessage);
	}

	@Entao("^o status code ao alterar uma simulacao com valor maior que quarenta mil deve ser (\\d+) e apresentar a mensagem \"([^\"]*)\"$")
	public void oStatusCodeAoAlterarUmaSimulacaoComValorMaiorQueQuarentaMilDeveSerEApresentarAMensagem(int statusCode,
			String expectedMessage) throws Throwable {

		simulacao.setStatusCode(statusCode);
		
		simulacaoTest.alterarUmaSimulacaoValorMaior40Mil(expectedMessage);
			
	}

	@Entao("^o status code ao alterar uma simulacao com parcela menor que dois deve ser (\\d+) e apresentar a mensagem \"([^\"]*)\"$")
	public void oStatusCodeAoAlterarUmaSimulacaoComParcelaMenorQueDoisDeveSerEApresentarAMensagem(int statusCode, String expectedMessage)
			throws Throwable {
		
		simulacao.setStatusCode(statusCode);
		
		simulacaoTest.alterarUmaSimulacaoParcelaMenorQue2(expectedMessage);
	}

	@Entao("^o status code ao alterar uma simulacao com parcela maior que quarenta e oito deve ser (\\d+) e apresentar a mensagem \"([^\"]*)\"$")
	public void oStatusCodeAoAlterarUmaSimulacaoComParcelaMaiorQueQuarentaEOitoDeveSerEApresentarAMensagem(int statusCode,
			String expectedMessage) throws Throwable {

		simulacao.setStatusCode(statusCode);
		
		simulacaoTest.alterarUmaSimulacaoParcelaMaiorQue48(expectedMessage);
	}

	@Dado("^que o usuario deseja consultar todas as simulacoes$")
	public void queOUsuarioDesejaConsultarTodasAsSimulacoes() throws Throwable {
		//Apenas pre condicao
	}

	@Entao("^o status code ao pesquisar por todas as simulacoes deve ser (\\d+) e retornar a lista com uma ou mais simulacoes cadastradas$")
	public void oStatusCodeAoPesquisarPorTodasAsSimulacoesDeveSerERetornarAListaComUmaOuMaisSimulacoesCadastradas(
			int statusCode) throws Throwable {
		simulacao.setStatusCode(statusCode);
		
		simulacaoTest.buscarTodasAsSimulacoes();
	}

	@Dado("^que o usuario deseja consultar uma simulacao por CPF$")
	public void queOUsuarioDesejaConsultarUmaSimulacaoPorCPF() throws Throwable {
		int tamanhoTexto = lerUltimoRegistroCadastrado(caminhoProjeto()+"\\src\\main\\resources\\Dados\\IDsECPFsCadastrados.txt").length();
		simulacao.setCpf(lerUltimoRegistroCadastrado(caminhoProjeto()+"\\src\\main\\resources\\Dados\\IDsECPFsCadastrados.txt").substring(tamanhoTexto-11, tamanhoTexto)); //busca o ultimo cpf cadastrado que foi gravado no arquivo txt);
	}

	@Entao("^o status code ao pesquisar por um CPF existente deve ser (\\d+) e retornar os dados referentes ao CPF consultado$")
	public void oStatusCodeAoPesquisarPorUmCPFExistenteDeveSerERetornarOsDadosReferentesAoCPFConsultado(int statusCode)
			throws Throwable {
		simulacao.setStatusCode(statusCode);
		
		simulacaoTest.buscarSimulacaoPorCPF();
	}

	@Dado("^que o usuario deseja consultar uma simulacao por um CPF nao cadastrado \"([^\"]*)\"$")
	public void queOUsuarioDesejaConsultarUmaSimulacaoPorUmCPFNaoCadastrado(String cpf) throws Throwable {
		simulacao.setCpf(cpf);
	}

	@Entao("^o status code ao pesquisar por um CPF inexistente deve ser (\\d+) e apresenta a mensagem \"([^\"]*)\"$")
	public void oStatusCodeAoPesquisarPorUmCPFInexistenteDeveSerEApresentaAMensagem(int statusCode, String expectedMessage)
			throws Throwable {
		simulacao.setStatusCode(statusCode);
		
		simulacaoTest.buscarSimulacaoPorCPFInexistente(expectedMessage);
	}

	@Dado("^que o usuario deseja remover uma simulacao que possua o id existente$")
	public void queOUsuarioDesejaRemoverUmaSimulacaoQuePossuaOIdExistente() throws Throwable {
		int tamanhoTexto = lerUltimoRegistroCadastrado(caminhoProjeto()+"\\src\\main\\resources\\Dados\\IDsECPFsCadastrados.txt").length();
		simulacao.setId(lerUltimoRegistroCadastrado(caminhoProjeto()+"\\src\\main\\resources\\Dados\\IDsECPFsCadastrados.txt").substring(0, tamanhoTexto-12)); //busca o ultimo id cadastrado que foi gravado no arquivo txt);
	}

	@Entao("^o status code ao remover uma solicitacao com sucesso deve ser (\\d+)$")
	public void oStatusCodeAoRemoverUmaSolicitacaoComSucessoDeveSer(int statusCode) throws Throwable {
		simulacao.setStatusCode(statusCode);
		
		simulacaoTest.removerSimulacaoExistente();
		apagarRegistro(lerUltimoRegistroCadastrado(caminhoProjeto()+"\\src\\main\\resources\\Dados\\IDsECPFsCadastrados.txt")); //metodo que retira o id dos registros cadastrados
	}

	@Dado("^que o usuario deseja remover uma simulacao inexistente que possua o id \"([^\"]*)\"$")
	public void queOUsuarioDesejaRemoverUmaSimulacaoInexistenteQuePossuaOId(String id) throws Throwable {
		simulacao.setId(id);
	}

	@Entao("^o status code ao remover uma solicitacao inexistente deve ser (\\d+) e apresenta a mensagem \"([^\"]*)\"$")
	public void oStatusCodeAoRemoverUmaSolicitacaoInexistenteDeveSerEApresentaAMensagem(int statusCode, String expectedMessage)
			throws Throwable {
		
		simulacao.setStatusCode(statusCode);
		
		simulacaoTest.removerSimulacaoInexistente(expectedMessage);
	}

}
