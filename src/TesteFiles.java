import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.database.files.BinaryFiles;
import model.database.files.WriterAndReader;
import model.database.stats_structure.entity.Champion;

public class TesteFiles {

	
		public static void main(String[] args){
		
		List<Champion> lista_champion = new ArrayList<Champion>();
		lista_champion = WriterAndReader.read(model.database.files.Path.CHAMPION_LIST);
		BinaryFiles championF = new BinaryFiles();
		int i=0;
		
		do{
			try {
				championF.saveChampion(lista_champion.get(i));
			} catch (IOException e) {
				e.printStackTrace();
			}
			i++;
		}while(i<=lista_champion.size());
	}
}
