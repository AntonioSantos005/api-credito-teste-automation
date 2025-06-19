package model;

//Classe que contem os atributos utilizados nos tests de Restricoes
public class RestricaoModel {

	private String cpf;
	private Integer statusCode;

	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
}
