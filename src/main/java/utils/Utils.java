package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class Utils {
	
	private static String calcDigVerif(String num) {
		Integer primDig, segDig;
		int soma = 0, peso = 10;
		for (int i = 0; i < num.length(); i++)
		soma += Integer.parseInt(num.substring(i, i + 1)) * peso-1;

		    if (soma % 11 == 0 | soma % 11 == 1)  
		        primDig = new Integer(0);  
		    else  
		        primDig = new Integer(11 - (soma % 11));  

		    soma = 0;  
		    peso = 11;  
		    for (int i = 0; i < num.length(); i++)  
		            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;  
		      
		    soma += primDig.intValue() * 2;  
		    if (soma % 11 == 0 | soma % 11 == 1)  
		        segDig = new Integer(0);  
		    else  
		        segDig = new Integer(11 - (soma % 11));  

		    return primDig.toString() + segDig.toString();  
		}  

		public static String gerarCPF() {  
		    String iniciais = "";  
		    Integer numero;  
		    for (int i = 0; i < 9; i++) {  
		        numero = new Integer((int) (Math.random() * 10));  
		        iniciais += numero.toString();  
		    }  
		    return iniciais + calcDigVerif(iniciais);  
		}  

		public static boolean validaCPF(String cpf) {  
		    if (cpf.length() != 11)  
		        return false;  

		    String numDig = cpf.substring(0, 9);  
		    return calcDigVerif(numDig).equals(cpf.substring(9, 11));  
		} 
		
		public static String caminhoProjeto() {
			String diretorio = System.getProperty("user.dir");
			return diretorio;
		}
		
		public static String lerUltimoRegistroCadastrado(String file) throws IOException {

			String ultimo = "";
			try {
				InputStream is = new FileInputStream(file);
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);

				String line = "";
				while (line != null) {
					line = br.readLine();
					if (line != null) {
						ultimo = line;
					}
				}

				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return ultimo;
		}	
		
		public static void gravarCpfEIdCadastrados(String texto) throws IOException {
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(caminhoProjeto()+"\\src\\main\\resources\\Dados\\IDsECPFsCadastradosGeral.txt", true));
			buffWrite.append(texto + "\n");
			buffWrite.close();
		}
		
		public static void apagarRegistro(String idECPF) throws IOException { //metodo que replica os dados do arquivo principal no tempfile apagando o necessario

			File inputFile = new File(caminhoProjeto()+"\\src\\main\\resources\\Dados\\IDsECPFsCadastradosGeral.txt");
			File tempFile = new File(caminhoProjeto()+"\\src\\main\\resources\\Dados\\IDsECPFsCadastrados.txt");

			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

			String lineToRemove = idECPF;
			String currentLine;

			while((currentLine = reader.readLine()) != null) {
			    // trim newline when comparing with lineToRemove
			    String trimmedLine = currentLine.trim();
			    if(trimmedLine.equals(lineToRemove)) continue;
			    writer.write(currentLine + System.getProperty("line.separator"));
			}
			writer.close(); 
			reader.close(); 

		}
		
}
