package stepsDefinitions;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import model.RestricaoModel;
import singleton.RestricaoSingleton;
import testAPI.RestricaoTest;

//classe que realiza a comunicacao entre a feature e a classe de tests
public class RestricaoSteps {
	
	//recebendo a instancia dos atributos 
	RestricaoModel restricao = RestricaoSingleton.getInstance();
	
	//Criando o objeto da classe que executa as requisicoes
	RestricaoTest restricaoTest = new RestricaoTest();
	
	@Dado("que o CPF que desejo consultar seja {string}")
	public void queOCPFQueDesejoConsultarSeja(String cpf) {
		restricao.setCpf(cpf);
	}

	@Entao("^o status code ao informar um CPF com restricao deve ser (\\d+)$")
	public void oStatusCodeAoInformarUmCPFComRestricaoDeveSer(int statusCode) throws Throwable {
		restricao.setStatusCode(statusCode);
		
		restricaoTest.consultarRestricaoPeloCPF();
	}

	@Entao("^o status code ao informar um CPF sem restricao deve ser (\\d+)$")
	public void oStatusCodeAoInformarUmCPFSemRestricaoDeveSer(int statusCode) throws Throwable {
		restricao.setStatusCode(statusCode);
		
		restricaoTest.consultarCPFSemRestricao();
	}

	
}
