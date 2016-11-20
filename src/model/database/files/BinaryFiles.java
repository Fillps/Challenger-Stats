package model.database.files;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.database.stats_structure.entity.Champion;
import model.database.stats_structure.entity.Item;
import model.database.stats_structure.entity.Mastery;
import model.database.stats_structure.entity.MasteryPage;
import model.database.stats_structure.entity.Rune;
import model.database.stats_structure.entity.RunePage;
import model.database.stats_structure.entity.SummonerSpell;


public class BinaryFiles {
	
	private static final String ITEM_FILE = "itens.bin";
	public static final String CHAMPION_FILE = "champions.bin";
	private static final String RUNE_FILE = "runes.bin";
	private static final String RUNEP_FILE = "runesP.bin";
	private static final String MASTERY_FILE = "masteries.bin";
	private static final String MASTERYP_FILE = "masteriesP.bin";
	private static final String SUMMONERSPELL_FILE = "summonerSpell.bin";
	
	private static final String CHAMPIONMAP_FILE = "championMap.bin";
	private static final String RUNEMAP_FILE = "runeMap.bin";
	private static final String RUNEPMAP_FILE = "runePMap.bin";
	private static final String ITEMMAP_FILE = "itemMap.bin";
	private static final String MASTERYMAP_FILE = "masteryMap.bin";
	private static final String MASTERYPMAP_FILE = "masteryPMap.bin";
	private static final String SUMMONERSPELLMAP_FILE = "summonerSpellMap.bin";
	
	public static Map <Integer,Integer> mapChampion;
	public static Map <Integer,Integer> mapRune;
	public static Map <Integer,Integer> mapRuneP;
	public static Map <Integer,Integer> mapItem;
	public static Map <Integer,Integer> mapMastery;
	public static Map <Integer,Integer> mapMasteryP;
	public static Map <Integer,Integer> mapSummonerSpell;
	
	public BinaryFiles(){
		File file = null;
		
		mapChampion = new HashMap<Integer,Integer>(); 
		file = new File(CHAMPIONMAP_FILE);		
		if( file.exists()) mapChampion = WriterAndReader.read(CHAMPIONMAP_FILE);
		
		mapRune = new HashMap<Integer,Integer>(); 
		file = new File(RUNEMAP_FILE);		
		if( file.exists()) mapRune = WriterAndReader.read(RUNEMAP_FILE);
		
		mapRuneP = new HashMap<Integer,Integer>(); 
		file = new File(RUNEPMAP_FILE);		
		if( file.exists()) mapRuneP = WriterAndReader.read(RUNEPMAP_FILE);
		
		mapItem = new HashMap<Integer,Integer>(); 
		file = new File(ITEMMAP_FILE);		
		if( file.exists()) mapItem = WriterAndReader.read(ITEMMAP_FILE);
		
		mapMastery = new HashMap<Integer,Integer>(); 
		file = new File(MASTERYMAP_FILE);		
		if( file.exists()) mapMastery = WriterAndReader.read(MASTERYMAP_FILE);
		
		mapMasteryP = new HashMap<Integer,Integer>(); 
		file = new File(MASTERYPMAP_FILE);		
		if( file.exists()) mapMasteryP = WriterAndReader.read(MASTERYPMAP_FILE);
		
		mapSummonerSpell = new HashMap<Integer,Integer>(); 
		file = new File(SUMMONERSPELLMAP_FILE);		
		if( file.exists()) mapSummonerSpell = WriterAndReader.read(SUMMONERSPELLMAP_FILE);
		
	}
	
	private static void log(Object aThing){
	    System.out.println(String.valueOf(aThing));
	}
	
	private byte [] readAll(String aInputFileName){ //aInputFilePath seria C:\\Users\\workspace\\nome.bin, vou mudar isso depois
		
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
	
	private byte [] readOnly(String aInputFileName, int pos, int len ){
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
				input.skip(pos);
				BytesLidos = input.read(resultado, 0, len);					
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

	private void write(byte[] aInput, String aOutputFileName){ //aOutputFileName seria "nome.bin"
		log("Escrevendo no arquivo binario...");
		Path p = Paths.get(aOutputFileName);		
			
		try (OutputStream output = new BufferedOutputStream(Files.newOutputStream(p, CREATE, APPEND))){				
			output.write(aInput);
		}	
		catch(IOException ex){
			log(ex);
		}
	}

	private void writeAt(byte[] aInput, String aOutputFileName, int pos){
		
		ByteBuffer out = ByteBuffer.wrap(aInput);
		Path p = Paths.get(aOutputFileName);
		
		try (FileChannel fc = (FileChannel.open(p, WRITE))) {
			fc.position(pos);
		    while (out.hasRemaining())
		        fc.write(out);
		    out.rewind();
			
		} catch (IOException x) {
		    System.out.println("I/O Exception: " + x);
		}
		
		
	}
	
	public Object getObj(int id, char c) throws ClassNotFoundException, IOException{
		
		String fileName = null;
		Map<Integer,Integer> dicionario = null;
		
		switch(c){
			case 'c': fileName = CHAMPION_FILE;
					  dicionario = mapChampion;
					  break;
			case 'r': fileName = RUNE_FILE;
			  		  dicionario = mapRune;
			  		  break;
			case 'p': fileName = RUNEP_FILE;
	  		  		  dicionario = mapRuneP;
	  		  		  break;
			case 'i': fileName = ITEM_FILE;
	  		  		  dicionario = mapItem;
	  		  		  break;
			case 'm': fileName = MASTERY_FILE;
	  		  		  dicionario = mapMastery;
	  		  		  break;
			case 'n': fileName = MASTERYP_FILE;
	  		  		  dicionario = mapMasteryP;
	  		  		  break;
			case 's': fileName = SUMMONERSPELL_FILE;
	  		  		  dicionario = mapSummonerSpell;
	  		  		  break;		
		
		}
		
		int pos = dicionario.get(id);
		int len;
				
		if(dicionario.containsKey(id+1)){
			len = dicionario.get(id+1)-pos; 
		}
		else{
			len = dicionario.get(-1)-pos;
		}//problema se nao existir id+1, usar map.get(-1) para a chave do final do arq
		
		byte[] data = readOnly(fileName, pos, len);
		return Serializer.deserialize(data);
	}
	
	public void saveObj(Object obj) throws IOException{
		int id = 0;
		Map <Integer,Integer> mapa = null;
		String objFile = null, mapFile = null;
		
		if(obj instanceof Champion){
			mapa = mapChampion;
			objFile = CHAMPION_FILE;
			mapFile = CHAMPIONMAP_FILE;
			Champion c = (Champion) obj;
			id = c.getID();
		}
		else if(obj instanceof Rune){
			mapa = mapRune;
			objFile = RUNE_FILE;
			mapFile = RUNEMAP_FILE;
			Rune r = (Rune) obj;
			id = r.getID();
		}
		else if(obj instanceof Item){
			mapa = mapItem;
			objFile = ITEM_FILE;
			mapFile = ITEMMAP_FILE;
			Item i = (Item) obj;
			id = i.getID();
		}
		else if(obj instanceof Mastery){
			mapa = mapMastery;
			objFile = MASTERY_FILE;
			mapFile = MASTERYMAP_FILE;
			Mastery m = (Mastery) obj;
			id = m.getID();
		}
		else if(obj instanceof MasteryPage){
			mapa = mapMasteryP;
			objFile = MASTERYP_FILE;
			mapFile = MASTERYPMAP_FILE;
			MasteryPage mp = (MasteryPage) obj;
			id = mp.getID();
		}
		else if(obj instanceof RunePage){
			mapa = mapRuneP;
			objFile = RUNEP_FILE;
			mapFile = RUNEPMAP_FILE;
			RunePage rp = (RunePage) obj;
			id = rp.getID();
		}
		else if(obj instanceof SummonerSpell){
			mapa = mapSummonerSpell;
			objFile = SUMMONERSPELL_FILE;
			mapFile = SUMMONERSPELLMAP_FILE;
			SummonerSpell ss = (SummonerSpell) obj;
			id = ss.getID();
		}
		
		
		byte[] data = Serializer.serialize(obj);
		
				
		if(mapa.containsKey(id))
			writeAt(data, objFile, mapa.get(id));
		else{
			File file = new File(objFile);
			if(!file.exists()) mapa.put(-1,0); //Conferir se campeao ja foi salvo
			
			mapa.put(id, mapa.get(-1));//mapChampion(key=-1) � a posicao do final do arquivo
			write(data, objFile);
			int pos = mapa.get(-1);
			mapa.put(-1,pos+data.length);
			WriterAndReader.write(mapa, mapFile);
		}
	}
}
