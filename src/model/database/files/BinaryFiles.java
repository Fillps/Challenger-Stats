package model.database.files;

import model.database.stats_structure.entity.*;
import model.database.stats_structure.relationship.Champion_Item;
import model.database.stats_structure.relationship.Champion_MasteryPage;
import model.database.stats_structure.relationship.Champion_RunePage;
import model.database.stats_structure.relationship.Champion_SummonerSpell;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.StandardOpenOption.*;
import static model.database.files.Path.DATA_FOLDER;
import static model.database.files.Path.MAIN_FOLDER;


public class BinaryFiles {


	public static final int CHAMPION = 0;
	public static final int RUNE = 1;
	public static final int RUNE_PAGE = 2;
	public static final int ITEM = 3;
	public static final int MASTERY = 4;
	public static final int MASTERY_PAGE = 5;
	public static final int SUMMONERSPELL = 6;
	public static final int CHAMPION_ITEM = 7;
	public static final int CHAMPION_MASTERY_PAGE = 8;
	public static final int CHAMPION_RUNE_PAGE = 9;
	public static final int CHAMPION_SUMMONER_SPELL = 10;


	private static final String ITEM_FILE = MAIN_FOLDER + DATA_FOLDER + "itens.bin";
	private static final String CHAMPION_FILE = MAIN_FOLDER + DATA_FOLDER + "champions.bin";
	private static final String RUNE_FILE = MAIN_FOLDER + DATA_FOLDER + "runes.bin";
	private static final String RUNEP_FILE = MAIN_FOLDER + DATA_FOLDER + "runesP.bin";
	private static final String MASTERY_FILE = MAIN_FOLDER + DATA_FOLDER + "masteries.bin";
	private static final String MASTERYP_FILE = MAIN_FOLDER + DATA_FOLDER + "masteriesP.bin";
	private static final String SUMMONERSPELL_FILE = MAIN_FOLDER + DATA_FOLDER + "summonerSpell.bin";

	private static final String CHAMPION_SUMMONERSPELL_FILE = MAIN_FOLDER + DATA_FOLDER + "champSummonerSpell.bin";
	private static final String CHAMPION_RUNEPAGE_FILE = MAIN_FOLDER + DATA_FOLDER + "champRuneP.bin";
	private static final String CHAMPION_MASTERYPAGE_FILE = MAIN_FOLDER + DATA_FOLDER + "champMasteryP.bin";
	private static final String CHAMPION_ITEM_FILE = MAIN_FOLDER + DATA_FOLDER + "champItem.bin";
	
	private static final String CHAMPION_MAP = MAIN_FOLDER + DATA_FOLDER + "championMap.bin";
	private static final String RUNE_MAP = MAIN_FOLDER + DATA_FOLDER + "runeMap.bin";
	private static final String RUNEP_MAP = MAIN_FOLDER + DATA_FOLDER + "runePMap.bin";
	private static final String ITEM_MAP = MAIN_FOLDER + DATA_FOLDER + "itemMap.bin";
	private static final String MASTERY_MAP = MAIN_FOLDER + DATA_FOLDER + "masteryMap.bin";
	private static final String MASTERYP_MAP = MAIN_FOLDER + DATA_FOLDER + "masteryPMap.bin";
	private static final String SUMMONERSPELL_MAP = MAIN_FOLDER + DATA_FOLDER + "summonerSpellMap.bin";

	private static final String CHAMPION_SUMMONERSPELL_MAP = MAIN_FOLDER + DATA_FOLDER + "champSummonerSpellMap.bin";
	private static final String CHAMPION_RUNEPAGE_MAP = MAIN_FOLDER + DATA_FOLDER + "champRunePMap.bin";
	private static final String CHAMPION_MASTERYPAGE_MAP = MAIN_FOLDER + DATA_FOLDER + "champMasteryPMap.bin";
	private static final String CHAMPION_ITEM_MAP = MAIN_FOLDER + DATA_FOLDER + "champItemMap.bin";
	
	public static Map <Integer,Integer> mapChampion;
	public static Map <Integer,Integer> mapRune;
	public static Map <Integer,Integer> mapRuneP;
	public static Map <Integer,Integer> mapItem;
	public static Map <Integer,Integer> mapMastery;
	public static Map <Integer,Integer> mapMasteryP;
	public static Map <Integer,Integer> mapSummonerSpell;

	public static Map<Integer,Integer> mapChampItem;
	public static Map<Integer,Integer> mapChampRuneP;
	public static Map<Integer,Integer> mapChampMasteryP;
	public static Map<Integer,Integer> mapChampSummonerSpell;
	
	public BinaryFiles(){
		File file = null;
		
		mapChampion = new HashMap<Integer,Integer>(); 
		file = new File(CHAMPION_MAP);
		if( file.exists()) mapChampion = WriterAndReader.read(CHAMPION_MAP);
		
		mapRune = new HashMap<Integer,Integer>(); 
		file = new File(RUNE_MAP);
		if( file.exists()) mapRune = WriterAndReader.read(RUNE_MAP);
		
		mapRuneP = new HashMap<Integer,Integer>(); 
		file = new File(RUNEP_MAP);
		if( file.exists()) mapRuneP = WriterAndReader.read(RUNEP_MAP);
		
		mapItem = new HashMap<Integer,Integer>(); 
		file = new File(ITEM_MAP);
		if( file.exists()) mapItem = WriterAndReader.read(ITEM_MAP);
		
		mapMastery = new HashMap<Integer,Integer>(); 
		file = new File(MASTERY_MAP);
		if( file.exists()) mapMastery = WriterAndReader.read(MASTERY_MAP);
		
		mapMasteryP = new HashMap<Integer,Integer>(); 
		file = new File(MASTERYP_MAP);
		if( file.exists()) mapMasteryP = WriterAndReader.read(MASTERYP_MAP);
		
		mapSummonerSpell = new HashMap<Integer,Integer>(); 
		file = new File(SUMMONERSPELL_MAP);
		if( file.exists()) mapSummonerSpell = WriterAndReader.read(SUMMONERSPELL_MAP);

		mapChampItem = new HashMap<Integer,Integer>();
		file = new File(CHAMPION_ITEM_MAP);
		if( file.exists()) mapChampItem = WriterAndReader.read(CHAMPION_ITEM_MAP);

		mapChampRuneP = new HashMap<Integer,Integer>();
		file = new File(CHAMPION_RUNEPAGE_MAP);
		if( file.exists()) mapChampRuneP = WriterAndReader.read(CHAMPION_RUNEPAGE_MAP);

		mapChampMasteryP = new HashMap<Integer,Integer>();
		file = new File(CHAMPION_MASTERYPAGE_MAP);
		if( file.exists()) mapChampMasteryP = WriterAndReader.read(CHAMPION_MASTERYPAGE_MAP);

		mapChampSummonerSpell = new HashMap<Integer,Integer>();
		file = new File(CHAMPION_SUMMONERSPELL_MAP);
		if( file.exists()) mapChampSummonerSpell = WriterAndReader.read(CHAMPION_SUMMONERSPELL_MAP);


		
	}
	
	private static void log(Object aThing){
	    System.out.println(String.valueOf(aThing));
	}
	
	private byte [] readAll(String aInputFileName){ //aInputFilePath seria C:\\Users\\workspace\\nome.bin, vou mudar isso depois
		
		//log("Lendo em binario aquivo em: " + aInputFileName);
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
				//log("Numero de bytes lidos: " + totalBytesLidos);
			}
			finally{
				//log("Fechando input stream");
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
		//log("Lendo em binario aquivo em: " + aInputFileName);
		Path p = Paths.get(aInputFileName);		
		
		
		File file = new File(aInputFileName);
		//log("Tamanho do arquivo: " + file.length());
		byte[] resultado = new byte[(int)file.length()];
		
		try{
			InputStream input = null;
			try{
				int BytesLidos = 0;
				input = new BufferedInputStream(Files.newInputStream(p));
				input.skip(pos);
				BytesLidos = input.read(resultado, 0, len);					
				if(BytesLidos > len) log("Erro no tamanho da entidade:" + pos);
				//log("Numero de bytes lidos: " + BytesLidos);
			}
			finally{
				//log("Fechando input stream");
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
		//log("Escrevendo no arquivo binario...");
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

	/**
	 *
	 * @param id
	 * @param c - Determina qual arquivo devera abrir.
	 *          ACEITA OS DETERMINADOS VALORES:
	 *
	 *          CHAMPION,
	 *          RUNE,
	 *          RUNE_PAGE,
	 *          ITEM,
	 *          MASTERY,
	 *          MASTERY_PAGE,
	 *          SUMMONER_SPELL,
	 *          CHAMPION_ITEM,
	 *          CHAMPION_MASTERY_PAGE,
	 *          CHAMPION_RUNE_PAGE,
	 *          CHAMPION_SUMMONER_SPELL,
	 *
	 * @return Object
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Object getObj(int id, int c){
		
		String fileName = null;
		Map<Integer,Integer> dicionario = null;
		
		switch(c){
			case 0: fileName = CHAMPION_FILE;
					  dicionario = mapChampion;
					  break;
			case 1: fileName = RUNE_FILE;
			  		  dicionario = mapRune;
			  		  break;
			case 2: fileName = RUNEP_FILE;
	  		  		  dicionario = mapRuneP;
	  		  		  break;
			case 3: fileName = ITEM_FILE;
	  		  		  dicionario = mapItem;
	  		  		  break;
			case 4: fileName = MASTERY_FILE;
	  		  		  dicionario = mapMastery;
	  		  		  break;
			case 5: fileName = MASTERYP_FILE;
	  		  		  dicionario = mapMasteryP;
	  		  		  break;
			case 6: fileName = SUMMONERSPELL_FILE;
	  		  		  dicionario = mapSummonerSpell;
	  		  		  break;
			case 7: fileName = CHAMPION_ITEM_FILE;
				dicionario = mapChampItem;
				break;
			case 8: fileName = CHAMPION_MASTERYPAGE_FILE;
				dicionario = mapChampMasteryP;
				break;
			case 9: fileName = CHAMPION_RUNEPAGE_FILE;
				dicionario = mapChampRuneP;
				break;
			case 10: fileName = CHAMPION_SUMMONERSPELL_FILE;
				dicionario = mapChampSummonerSpell;
				break;
			default:
				return null;
		
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
		try {
			return Serializer.deserialize(data);
		} catch (IOException e) {
			System.out.println("Erro! Nao foi possivel abrir o arquivo!");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Erro! Classe nao encontrada!");
			e.printStackTrace();
		}
		return null;
	}
	
	public void saveObj(Object obj) {
		int id = 0;
		Map <Integer,Integer> mapa = null;
		String objFile = null, mapFile = null;
		
		if(obj instanceof Champion){
			mapa = mapChampion;
			objFile = CHAMPION_FILE;
			mapFile = CHAMPION_MAP;
			Champion c = (Champion) obj;
			id = c.getID();
		}
		else if(obj instanceof Rune){
			mapa = mapRune;
			objFile = RUNE_FILE;
			mapFile = RUNE_MAP;
			Rune r = (Rune) obj;
			id = r.getID();
		}
		else if(obj instanceof Item){
			mapa = mapItem;
			objFile = ITEM_FILE;
			mapFile = ITEM_MAP;
			Item i = (Item) obj;
			id = i.getID();
		}
		else if(obj instanceof Mastery){
			mapa = mapMastery;
			objFile = MASTERY_FILE;
			mapFile = MASTERY_MAP;
			Mastery m = (Mastery) obj;
			id = m.getID();
		}
		else if(obj instanceof MasteryPage){
			mapa = mapMasteryP;
			objFile = MASTERYP_FILE;
			mapFile = MASTERYP_MAP;
			MasteryPage mp = (MasteryPage) obj;
			id = mp.getID();
		}
		else if(obj instanceof RunePage){
			mapa = mapRuneP;
			objFile = RUNEP_FILE;
			mapFile = RUNEP_MAP;
			RunePage rp = (RunePage) obj;
			id = rp.getID();
		}
		else if(obj instanceof SummonerSpell){
			mapa = mapSummonerSpell;
			objFile = SUMMONERSPELL_FILE;
			mapFile = SUMMONERSPELL_MAP;
			SummonerSpell ss = (SummonerSpell) obj;
			id = ss.getID();
		}
		else if(obj instanceof Champion_Item){
			mapa = mapChampItem;
			objFile = CHAMPION_ITEM_FILE;
			mapFile = CHAMPION_ITEM_MAP;
			Champion_Item ci = (Champion_Item) obj;
			id = ci.getID();
		}
		else if(obj instanceof Champion_MasteryPage){
			mapa = mapChampMasteryP;
			objFile = CHAMPION_MASTERYPAGE_FILE;
			mapFile = CHAMPION_MASTERYPAGE_MAP;
			Champion_MasteryPage css = (Champion_MasteryPage) obj;
			id = css.getID();
		}
		else if(obj instanceof Champion_RunePage){
			mapa = mapChampRuneP;
			objFile = CHAMPION_RUNEPAGE_FILE;
			mapFile = CHAMPION_RUNEPAGE_MAP;
			Champion_RunePage css = (Champion_RunePage) obj;
			id = css.getID();
		}
		else if(obj instanceof Champion_SummonerSpell){
			mapa = mapChampSummonerSpell;
			objFile = CHAMPION_SUMMONERSPELL_FILE;
			mapFile = CHAMPION_SUMMONERSPELL_MAP;
			Champion_SummonerSpell css = (Champion_SummonerSpell) obj;
			id = css.getID();
		}


		byte[] data = new byte[0];
		try {
			data = Serializer.serialize(obj);
		} catch (IOException e) {
			System.out.println("Erro! Nao foi possivel salvar!");
			e.printStackTrace();
			return;
		}


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
