package model;

//Classe que contem os atributos utilizados nos tests de Simulacoes
public class SimulacaoModel {

	private String cpf;
	private String nome;
	private String email;
	private String valor;
	private Integer parcelas;
	private Boolean seguro;
	private Integer statusCode;
	private String id;
	private String cpfEID;

	public String getCpfEID() {
		return cpfEID;
	}

	public void setCpfEID(String cpfEID) {
		this.cpfEID = cpfEID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Integer getParcelas() {
		return parcelas;
	}

	public void setParcelas(Integer parcelas) {
		this.parcelas = parcelas;
	}

	public Boolean getSeguro() {
		return seguro;
	}

	public void setSeguro(Boolean seguro) {
		this.seguro = seguro;
	}
	
}
