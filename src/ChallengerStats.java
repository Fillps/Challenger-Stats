import GUI.MainFrame;
import model.database.files.BinaryFiles;
import model.database.files.WriterAndReader;
import model.database.stats_structure.entity.Champion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;



/**
 * Created by filip on 04/11/2016.
 */
public class ChallengerStats {

    /*public static void main(String[] args){

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame("Challenger Stats");

            }
        });
    }*/
	public static void main(String[] args){
		
		List<Champion> lista_champion = new ArrayList<Champion>();
		lista_champion = WriterAndReader.read(model.database.files.Path.CHAMPION_LIST);
		BinaryFiles championF = new BinaryFiles();
		int i=0;
		
		do{
			try {
				championF.saveObj(lista_champion.get(i));
			} catch (IOException e) {
				e.printStackTrace();
			}
			i++;
		}while(i<lista_champion.size());
		
		int len1 = BinaryFiles.mapChampion.get(-1);
		File file = new File(model.database.files.BinaryFiles.CHAMPION_FILE);
		long len2 = file.length();
		System.out.printf("Tamanho do arq no dicionario: %d \n Tamanho do arq pelo arquivo: %d ",len1,len2);
		System.out.printf("Posição Ahri: %d \n Posição Aatrox: %d ",BinaryFiles.mapChampion.get(1),BinaryFiles.mapChampion.get(0));
		try {
			Champion champion0 = null;
			champion0 = (Champion) championF.getObj(132, 'c');
			System.out.println(champion0.toString());
		} catch (ClassNotFoundException | IOException e) {
			
			e.printStackTrace();
		}
	}
}

