package model.database.files;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import model.database.stats_structure.entity.Champion;

public class BinaryFiles {
	
	
	private static final String INPUT_FILE_NAME = "highscores.bin";
	private static final String OUTPUT_FILE_NAME = "arqTeste.bin";
	private static final String ITEM_FILE_NAME = "itens.bin";
	private static final String CHAMPION_FILE = "champions.bin";
	private static final String RUNE_FILE_NAME = "runes.bin";
	private static final String MASTERY_FILE_NAME = "masteries.bin";
	
	private Map <Integer,Integer> mapChampion;
	
	
	private static void log(Object aThing){
	    System.out.println(String.valueOf(aThing));
	}
	
	byte [] readAll(String aInputFileName){ //aInputFilePath seria C:\\Users\\workspace\\nome.bin, vou mudar isso depois
		
		log("Lendo em binario aquivo em: " + aInputFileName);
		File file = new File(aInputFileName);
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
	
	byte [] readOnly(String aInputFileName, int pos, int len ){
		log("Lendo em binario aquivo em: " + aInputFileName);
		Path p = Paths.get(aInputFileName);		
		
		
		File file = new File(aInputFileName);
		log("Tamanho do arquivo: " + file.length());
		byte[] resultado = new byte[(int)file.length()];
		
		try{
			InputStream input = null;
			try{
				int BytesLidos = 0;
				input = new BufferedInputStream(Files.newInputStream(p));
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

	void writeAt(byte[] aInput, String aOutputFileName, int pos, int len){
		File file = new File(aOutputFileName);
		
		try (FileOutputStream output = new FileOutputStream(file)){
			output.write(aInput, pos, len);
		}
		catch(IOException ex){
			log(ex);
		}
	}
	
	Object getObj(int id, String fileName, Map<Integer,Integer> dicionario) throws ClassNotFoundException, IOException{
		int pos = dicionario.get(id);
		
		int len= dicionario.get(id+1)-pos; //Caga se n�o existir id+1, usar map.containsKey para a chave do final do arq
		
		byte[] data = readOnly(fileName, pos, len);
		
		return Serializer.deserialize(data);
	}
	
	void saveChampion(Champion champion) throws IOException{
		byte[] data = Serializer.serialize(champion);
		int id=champion.getID();
		//File file = new File(CHAMPION_FILE);
		
		if(mapChampion.containsKey(id))
			writeAt(data, CHAMPION_FILE, mapChampion.get(id), mapChampion.get(-1));
		else{
			//if(!file.exists()) {mapChampion.put(-1,0);
			//file.close();}
			mapChampion.put(champion.getID(), mapChampion.get(-1));//mapChampion(key=-1) � a posicao do final do arquivo
			write(data, CHAMPION_FILE);
			int pos=mapChampion.get(-1);
			mapChampion.put(-1,pos+data.length);
		}
	}
}
