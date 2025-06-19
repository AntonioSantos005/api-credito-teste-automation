package singleton;

//Classe responsavel por garantir apenas uma instancia do objeto

import model.RestricaoModel;

public class RestricaoSingleton {
	
	private static RestricaoModel restricao;

	public static RestricaoModel getInstance() {
		if(restricao == null) {
			restricao = new RestricaoModel();
		}
		
		return restricao;
	}
}
