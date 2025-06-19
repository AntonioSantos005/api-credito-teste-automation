package testAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static utils.Utils.*;

import java.io.IOException;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.SimulacaoModel;
import singleton.SimulacaoSingleton;

public class SimulacaoTest {
	
	SimulacaoModel simulacao = SimulacaoSingleton.getInstance();

	//TESTE
	public void criarUmaSimulacaoSucesso() throws IOException {
		Response response;
		
		do {     //ira repetir ate que seja um CPF valido
			
			simulacao.setCpf(gerarCPF()); // sempre gera um novo CPF

		
			response = given() //pre condicao
					.log().all() // apresenta o log no console
					.contentType(ContentType.JSON) //contentType esperado
					.body("{\r\n"
							+ "  \"nome\": \"" + simulacao.getNome() + "\",\r\n"
							+ "  \"cpf\": " + simulacao.getCpf() + ",\r\n"
							+ "  \"email\": \"" + simulacao.getEmail() + "\",\r\n" //valores a ser inseridos
							+ "  \"valor\": " + simulacao.getValor() + ",\r\n"
							+ "  \"parcelas\": " + simulacao.getParcelas() + ",\r\n"
							+ "  \"seguro\": " + simulacao.getSeguro() + "\r\n"
							+ "}")
					.when()
	                	.post("/api/v1/simulacoes")
	                	.andReturn();
		} while (response.getStatusCode() != simulacao.getStatusCode());
		
		response.then()
			.log().all()
			.statusCode(simulacao.getStatusCode()) //valida o status code de acordo com o esperado que esta sendo passado na feature
			.contentType(ContentType.JSON)
			.body("id", is(notNullValue())) //valida o resultado
			.body("nome", is(simulacao.getNome())) //valida o resultado
			.body("cpf", is(simulacao.getCpf())) //valida o resultado
			.body("email", is(simulacao.getEmail())) //valida o resultado
			.body("parcelas", is(simulacao.getParcelas())) //valida o resultado
			.body("seguro", is(simulacao.getSeguro())); //valida o resultado
		
		gravarCpfEIdCadastrados(response.getBody().asString().replace("{\"id\":", "").replace(",\"nome\":\"Carlos\",\"cpf\":\"" + simulacao.getCpf() +"\",\"email\":\"teste@teste.com\",\"valor\":2000.02,\"parcelas\":36,\"seguro\":true}", "") + " " + simulacao.getCpf()); //metodo que grava o CPF cadastrado em um arquivo para ser utilizado nos testes de consulta e alteracao
		  
	}
	
	public void criarUmaSimulacaoCPFComMascara() {
		
		given()
			.log().all()
			.contentType(ContentType.JSON)
			.body("{\r\n"
					+ "  \"nome\": \"" + simulacao.getNome() + "\",\r\n"
					+ "  \"cpf\": " + simulacao.getCpf() + ",\r\n"
					+ "  \"email\": \"" + simulacao.getEmail() + "\",\r\n"
					+ "  \"valor\": " + simulacao.getValor() + ",\r\n"
					+ "  \"parcelas\": " + simulacao.getParcelas() + ",\r\n"
					+ "  \"seguro\": " + simulacao.getSeguro() + "\r\n"
					+ "}")
		.when()
			.post("/api/v1/simulacoes")
		.then()
			.log().all()
			.statusCode(simulacao.getStatusCode());

	}
	
	public void criarUmaSimulacaoSemCamposObrigatorio(String expectedMessage1, String expectedMessage2, String expectedMessage3, 
			String expectedMessage4, String expectedMessage5) {
		
		given()
			.log().all()
			.contentType(ContentType.JSON)
			.body("{\r\n"
					
					+ "  \"seguro\": " + simulacao.getSeguro() + "\r\n"
					+ "}")
		.when()
			.post("/api/v1/simulacoes")
		.then()
			.log().all()
			.statusCode(simulacao.getStatusCode())
			.body("erros.parcelas", is(expectedMessage1))
			.body("erros.valor", is(expectedMessage2))
			.body("erros.cpf", is(expectedMessage3))
			.body("erros.nome", is(expectedMessage4))
			.body("erros.email", is(expectedMessage5))
			;
	}
	
	public void criarUmaSimulacaoSemCampoSeguro(String expectedMessage) {
		
		given()
			.log().all()
			.contentType(ContentType.JSON)
			.body("{\r\n"
					+ "  \"nome\": \"" + simulacao.getNome() + "\",\r\n"
					+ "  \"cpf\": " + simulacao.getCpf() + ",\r\n"
					+ "  \"email\": \"" + simulacao.getEmail() + "\",\r\n"
					+ "  \"valor\": " + simulacao.getValor() + ",\r\n"
					+ "  \"parcelas\": " + simulacao.getParcelas() + "\r\n"
					+ "}")
		.when()
			.post("/api/v1/simulacoes")
		.then()
			.log().all()
			.statusCode(simulacao.getStatusCode())
			.body("seguro", is(expectedMessage));
	}
	
	public void criarUmaSimulacaoValorMenorQueMil(String expectedMessage) {
		
		given()
			.log().all()
			.contentType(ContentType.JSON)
			.body("{\r\n"
					+ "  \"nome\": \"" + simulacao.getNome() + "\",\r\n"
					+ "  \"cpf\": " + simulacao.getCpf() + ",\r\n"
					+ "  \"email\": \"" + simulacao.getEmail() + "\",\r\n"
					+ "  \"valor\": " + simulacao.getValor() + ",\r\n"
					+ "  \"parcelas\": " + simulacao.getParcelas() + ",\r\n"
					+ "  \"seguro\": " + simulacao.getSeguro() + "\r\n"
					+ "}")
		.when()
			.post("/api/v1/simulacoes")
		.then()
			.log().all()
			.statusCode(simulacao.getStatusCode())
			.body("erros.valor", is(expectedMessage));

	}
	
	public void criarUmaSimulacaoValorMaior40Mil(String expectedMessage) {
		
		given()
			.log().all()
			.contentType(ContentType.JSON)
			.body("{\r\n"
					+ "  \"nome\": \"" + simulacao.getNome() + "\",\r\n"
					+ "  \"cpf\": 76312998037,\r\n"
					+ "  \"email\": \"" + simulacao.getEmail() + "\",\r\n"
					+ "  \"valor\": " + simulacao.getValor() + ",\r\n"
					+ "  \"parcelas\": " + simulacao.getParcelas() + ",\r\n"
					+ "  \"seguro\": " + simulacao.getSeguro() + "\r\n"
					+ "}")
		.when()
			.post("/api/v1/simulacoes")
		.then()
			.log().all()
			.statusCode(simulacao.getStatusCode())
			.contentType(ContentType.JSON)
			.body("erros.valor", is(expectedMessage));

	}
	
	public void criarUmaSimulacaoCPFDuplicado(String espectedMessage) {
		
		given()
			.log().all()
			.contentType(ContentType.JSON)
			.body("{\r\n"
					+ "  \"nome\": \"" + simulacao.getNome() + "\",\r\n"
					+ "  \"cpf\": " + simulacao.getCpf() + ",\r\n"
					+ "  \"email\": \"" + simulacao.getEmail() + "\",\r\n"
					+ "  \"valor\": " + simulacao.getValor() + ",\r\n"
					+ "  \"parcelas\": " + simulacao.getParcelas() + ",\r\n"
					+ "  \"seguro\": " + simulacao.getSeguro() + "\r\n"
					+ "}")
		.when()
			.post("/api/v1/simulacoes")
		.then()
			.log().all()
			.statusCode(simulacao.getStatusCode())
			.body("mensagem", is(espectedMessage));
	}
	
	public void criarUmaSimulacaoParcelaMenorQue2(String expectedMessage) {
		
		given()
			.log().all()
			.contentType(ContentType.JSON)
			.body("{\r\n"
					+ "  \"nome\": \"" + simulacao.getNome()+ "\",\r\n"
					+ "  \"cpf\": 41558790004,\r\n"
					+ "  \"email\": \"" + simulacao.getEmail() + "\",\r\n"
					+ "  \"valor\": " + simulacao.getValor() + ",\r\n"
					+ "  \"parcelas\": " + simulacao.getParcelas() + ",\r\n"
					+ "  \"seguro\": " + simulacao.getSeguro() + "\r\n"
					+ "}")
		.when()
			.post("/api/v1/simulacoes")
		.then()
			.log().all()
			.statusCode(simulacao.getStatusCode())
			.contentType(ContentType.JSON)
			.body("erros.parcelas", is(expectedMessage));

	}

	public void criarUmaSimulacaoParcelaMeaiorQue48(String expectedMessage) {
	
	given()
		.log().all()
		.contentType(ContentType.JSON)
		.body("{\r\n"
				+ "  \"nome\": \""+ simulacao.getNome() +"\",\r\n"
				+ "  \"cpf\": " + simulacao.getCpf() + ",\r\n"
				+ "  \"email\": \"" + simulacao.getEmail() + "\",\r\n"
				+ "  \"valor\": " + simulacao.getValor() + ",\r\n"
				+ "  \"parcelas\": " + simulacao.getParcelas() + ",\r\n"
				+ "  \"seguro\": " + simulacao.getSeguro() + "\r\n"
				+ "}")
	.when()
		.post("/api/v1/simulacoes")
	.then()
		.log().all()
		.statusCode(simulacao.getStatusCode())
		.contentType(ContentType.JSON)
		.body("erros.parcelas", is(expectedMessage));

	}
	
	public void alterarUmaSimulacaoSucesso() throws IOException {
		
		System.out.println("");
		
		given()
			.log().all()
			.contentType(ContentType.JSON)
			.body("{\r\n"
					+ "  \"nome\": \"" + simulacao.getNome() + "\",\r\n"
					+ "  \"cpf\": " + simulacao.getCpf() + ",\r\n"
					+ "  \"email\": \"" + simulacao.getEmail() + "\",\r\n"
					+ "  \"valor\": " + simulacao.getValor() + ",\r\n"
					+ "  \"parcelas\": " + simulacao.getParcelas() + ",\r\n"
					+ "  \"seguro\": " + simulacao.getSeguro() + "\r\n"
					+ "}")
		.when()
			.put("/api/v1/simulacoes/" + simulacao.getCpf())
		.then()
			.log().all()
			.statusCode(simulacao.getStatusCode())
			.body("id", is(notNullValue()))
			.body("nome", is(simulacao.getNome()))
			.body("cpf", is(simulacao.getCpf()))
			.body("email", is(simulacao.getEmail()))
			.body("parcelas", is(simulacao.getParcelas()))
			.body("seguro", is(simulacao.getSeguro()));

	}
	
	public void alterarUmaSimulacaoValorMenorQueMil(String expectedMessage) throws IOException {
		
		given()
			.log().all()
			.contentType(ContentType.JSON)
			.body("{\r\n"
					+ "  \"nome\": \"" + simulacao.getNome() + "\",\r\n"
					+ "  \"cpf\": " + simulacao.getCpf() + ",\r\n"
					+ "  \"email\": \"" + simulacao.getEmail() + "\",\r\n"
					+ "  \"valor\": " + simulacao.getValor() + ",\r\n"
					+ "  \"parcelas\": " + simulacao.getParcelas() + ",\r\n"
					+ "  \"seguro\": " + simulacao.getSeguro() + "\r\n"
					+ "}")
		.when()
			.put("/api/v1/simulacoes/" + simulacao.getCpf())
		.then()
			.log().all()
			.statusCode(simulacao.getStatusCode())
			.body("erros.valor", is(expectedMessage));

	}
	
	public void alterarUmaSimulacaoValorMaior40Mil(String expectedMessage) throws IOException {
		
		given()
			.log().all()
			.contentType(ContentType.JSON)
			.body("{\r\n"
					+ "  \"nome\": \"" + simulacao.getNome() + "\",\r\n"
					+ "  \"cpf\": " + simulacao.getCpf() + ",\r\n"
					+ "  \"email\": \"" + simulacao.getEmail() + "\",\r\n"
					+ "  \"valor\": " + simulacao.getValor() + ",\r\n"
					+ "  \"parcelas\": " + simulacao.getParcelas() + ",\r\n"
					+ "  \"seguro\": " + simulacao.getSeguro() + "\r\n"
					+ "}")
		.when()
			.put("/api/v1/simulacoes/" + simulacao.getCpf())
		.then()
			.log().all()
			.statusCode(simulacao.getStatusCode())
			.contentType(ContentType.JSON)
			.body("erros.valor", is(expectedMessage));
	}
	
	public void alterarUmaSimulacaoParcelaMenorQue2(String expectedMessage) throws IOException {
		
		given()
			.log().all()
			.contentType(ContentType.JSON)
			.body("{\r\n"
					+ "  \"nome\": \"" + simulacao.getNome()+ "\",\r\n"
					+ "  \"cpf\": " + simulacao.getCpf() + ",\r\n"
					+ "  \"email\": \"" + simulacao.getEmail() + "\",\r\n"
					+ "  \"valor\": " + simulacao.getValor() + ",\r\n"
					+ "  \"parcelas\": " + simulacao.getParcelas() + ",\r\n"
					+ "  \"seguro\": " + simulacao.getSeguro() + "\r\n"
					+ "}")
		.when()
		.put("/api/v1/simulacoes/" + simulacao.getCpf())
		.then()
			.log().all()
			.statusCode(simulacao.getStatusCode())
			.contentType(ContentType.JSON)
			.body("erros.parcelas", is(expectedMessage));

	}
	
	public void alterarUmaSimulacaoParcelaMaiorQue48(String expectedMessage) throws IOException {
		
		given()
			.log().all()
			.contentType(ContentType.JSON)
			.body("{\r\n"
					+ "  \"nome\": \""+ simulacao.getNome() +"\",\r\n"
					+ "  \"cpf\": " + simulacao.getCpf() + ",\r\n"
					+ "  \"email\": \"" + simulacao.getEmail() + "\",\r\n"
					+ "  \"valor\": " + simulacao.getValor() + ",\r\n"
					+ "  \"parcelas\": " + simulacao.getParcelas() + ",\r\n"
					+ "  \"seguro\": " + simulacao.getSeguro() + "\r\n"
					+ "}")
		.when()
			.put("/api/v1/simulacoes/" + simulacao.getCpf())
		.then()
			.log().all()
			.statusCode(simulacao.getStatusCode())
			.contentType(ContentType.JSON)
			.body("erros.parcelas", is(expectedMessage));

	}
	
	public void buscarTodasAsSimulacoes() {
		
		given()
			.log().all()
		.when()
			.get("/api/v1/simulacoes")
		.then()
			.log().all()
			.statusCode(simulacao.getStatusCode())
			.body("id.max()", is(greaterThan(0)));
	}
	
	public void buscarSimulacaoPorCPF() throws IOException {
				
		given()
			.log().all()
		.when()
			.get("/api/v1/simulacoes/" + simulacao.getCpf())
		.then()
			.log().all()
			.statusCode(simulacao.getStatusCode())
			.body("cpf", is(simulacao.getCpf()));
		
	}
	
	public void buscarSimulacaoPorCPFInexistente(String expectedMessage) {
		
		given()
			.log().all()
		.when()
			.get("/api/v1/simulacoes/" + simulacao.getCpf())
		.then()
			.log().all()
			.statusCode(simulacao.getStatusCode())
			.body("mensagem", is(expectedMessage));
	}
	
	public void removerSimulacaoExistente() {
		
		given()
			.log().all()
		.when()
			.delete("/api/v1/simulacoes/" + simulacao.getId())
		.then()
			.log().all()
			.statusCode(simulacao.getStatusCode());
	}
	
public void removerSimulacaoInexistente(String expectedMessage) {
		
		given()
			.log().all()
		.when()
			.delete("/api/v1/simulacoes/" + simulacao.getId())
		.then()
			.log().all()
			.statusCode(simulacao.getStatusCode())
			.body("message", is(expectedMessage));
	}
	
}
