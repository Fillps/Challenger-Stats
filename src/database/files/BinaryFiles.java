package database.files;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BinaryFiles {
	
	private static void log(Object aThing){
	    System.out.println(String.valueOf(aThing));
	}
	
	byte [] readAll(String aInputFilePath){ //aInputFilePath seria C:\\Users\\workspace\\nome.bin, vou mudar isso depois
		log("Lendo em binario aquivo em: " + aInputFilePath);
		File file = new File(aInputFilePath);
		log("Tamanho do arquivo: " + file.length());
		byte[] resultado = new byte[(int)file.length()];
		
		try{
			InputStream input = null;
			try{
				int totalBytesLidos = 0;
				input = new BufferedInputStream(new FileInputStream(file));
				while(totalBytesLidos < resultado.length){
					int bytesSobrando = resultado.length - totalBytesLidos;
					int BytesLidos = input.read(resultado, totalBytesLidos, bytesSobrando);
					if(BytesLidos > 0){
						totalBytesLidos += BytesLidos; 
					}
				}
				log("Numero de bytes lidos: " + totalBytesLidos);		
			}
			finally{
				log("Fechando input stream");
				input.close();
			}			
		}
		catch(FileNotFoundException ex){
			log("File not found");
		}
		catch(IOException ex){
			log(ex);
		}
		return resultado;
	}
	
	byte [] readOnly(String aInputFilePath, int pos, int len ){
		log("Lendo em binario aquivo em: " + aInputFilePath);
		File file = new File(aInputFilePath);
		log("Tamanho do arquivo: " + file.length());
		byte[] resultado = new byte[(int)file.length()];
		
		try{
			InputStream input = null;
			try{
				int BytesLidos = 0;
				input = new BufferedInputStream(new FileInputStream(file));
				BytesLidos = input.read(resultado, pos, len);					
				if(BytesLidos > len) log("Erro no tamanho da entidade:" + pos);
				log("Numero de bytes lidos: " + BytesLidos);		
			}
			finally{
				log("Fechando input stream");
				input.close();
			}			
		}
		catch(FileNotFoundException ex){
			log("File not found");
		}
		catch(IOException ex){
			log(ex);
		}
		return resultado;
	}

	void write(byte[] aInput, String aOutputFileName){ //aOutputFileName seria "nome.bin"
		log("Escrevendo no arquivo binario...");
		Path p = Paths.get(aOutputFileName);		
			
		try (OutputStream output = new BufferedOutputStream(Files.newOutputStream(p, CREATE, APPEND))){				
			output.write(aInput);
		}	
		catch(IOException ex){
			log(ex);
		}
	}

}
