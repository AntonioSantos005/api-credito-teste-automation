package testAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import io.restassured.http.ContentType;
import model.RestricaoModel;
import singleton.RestricaoSingleton;

//Classe responsavel pelas requisicoes
public class RestricaoTest {
	
	//recebendo a instancia dos atributos
	RestricaoModel restricao = RestricaoSingleton.getInstance();

	public void consultarRestricaoPeloCPF() {
			
		given() //pre condicao
			.log().all() // apresenta o log no console
			.contentType(ContentType.JSON) //contentType esperado
		.when()
			.get("/api/v1/restricoes/" + restricao.getCpf()) //realiza a requisicao
		.then()
			.statusCode(restricao.getStatusCode()) //vaslida o status code de acordo com o esperado que esta sendo passado na feature
			.body("mensagem", is("O CPF " + restricao.getCpf() + " tem problema")); //valida o resultado

	}
	
	public void consultarCPFSemRestricao() {
		given()
			.log().all()
			.contentType(ContentType.JSON)
		.when()
			.get("/api/v1/restricoes/" + restricao.getCpf())
		.then()
			.statusCode(restricao.getStatusCode());
	}

}
