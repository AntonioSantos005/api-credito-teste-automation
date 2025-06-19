package stepsDefinitions;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

//Gerenciamente do que deve ser feito antes e depois de cada testes
public class Hooks {
	
	//executado sempre antes de inicar cada cenario, definindo url base
	@Before()
	public void setUp() throws Exception {
		RestAssured.baseURI = "http://localhost:8080";		
	}
	
	//executado sempre depois de finalizar cada cenario, apresentando no log nome e resultado do cenario
	@After()
	public void tirarScreenshot(Scenario scenario) throws Exception {
		System.out.println("============== " + scenario.getName() + " ==============");
		System.out.println("====================== " + scenario.getStatus() + " ======================");
	}
	
	//Executa apenas nos cenarios que possuem a tag @esperoErro
	@After(value = "@esperoErro")
	public void erroJaEsperado() throws Exception {
		System.out.println("============== ERRO JA ESPERADO ==============");
	}

}
