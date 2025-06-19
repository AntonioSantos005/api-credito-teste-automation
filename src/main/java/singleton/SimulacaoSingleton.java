package singleton;

//Classe responsavel por garantir apenas uma instancia do objeto
import model.SimulacaoModel;

public class SimulacaoSingleton {
	
	private static SimulacaoModel simulacao;

	public static SimulacaoModel getInstance() {
		if(simulacao == null) {
			simulacao = new SimulacaoModel();
		}
		
		return simulacao;
	}
}
