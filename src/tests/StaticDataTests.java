package tests;

import model.database.data_structure.TrieExtended;
import model.database.data_structure.TrieMapExtended;
import model.database.files.WriterAndReader;
import model.riot_api.StaticData;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by filip on 17/10/2016.
 */
public class StaticDataTests {
    public static void StaticDataImage() throws RiotApiException, IOException {

        /*ApiConfig config = new ApiConfig();
        config.setKey(WriterAndReader.readTxt("Key/key.txt", Charset.defaultCharset()));
        RiotApi api = new RiotApi(config);
        StaticData staticData = GetStaticData.getStaticData(api);
        WriterAndReader.write(staticData, "arquivos/StaticData.ser");*/
        StaticData staticData = WriterAndReader.read("arquivos/StaticData.ser");
        Champion champion = staticData.getChampionList().getData().get("266");
        String aatroxURL = "http://ddragon.leagueoflegends.com/cdn/5.2.1/img/champion/";
        aatroxURL = aatroxURL + champion.getImage().getFull();

        JFrame frame = new JFrame();
        frame.setTitle("Polygons");
        frame.setSize(140, 150);
        System.out.println(champion.getImage().getX() + " " + champion.getImage().getY());
        System.out.println(champion.getImage().getW() + " " + champion.getImage().getH());
        Container contentPane = frame.getContentPane();
        try {
            JLabel sentenceLabel= new JLabel(new ImageIcon(
                    ImageIO.read(new URL(aatroxURL))));
            contentPane.add(sentenceLabel);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        frame.show();
    }

    public static void TesteIDs(){
        StaticData staticData = WriterAndReader.read("arquivos/StaticData.ser");
        Map<String,Champion> map = staticData.getChampionList().getData();
        for(int i = 0; i < 150; i++){
            String id = String.valueOf(i);
            if (!map.containsKey(id)){
                System.out.println(id);
            }
        }
    }

    public static void salvaNovamente(){
        StaticData staticData = WriterAndReader.read("arquivos/StaticData.ser");
        WriterAndReader.write(staticData, "arquivos/StaticData.ser");
    }

    public static void saveNameOfChampionOnTrie(String local){
        TrieExtended trie = new TrieExtended();
        StaticData staticData = WriterAndReader.read("arquivos/StaticData.ser");
        List<Champion> list = new ArrayList<Champion>(staticData.getChampionList().getData().values());
        for (Champion i : list){
            trie.add(i.getName());
        }
        WriterAndReader.write(trie, local);
    }

    public static void saveNameOfChampionOnTrieMap(String local){
        TrieMapExtended<String, Integer> trieMap = new TrieMapExtended<>();
        StaticData staticData = WriterAndReader.read("arquivos/StaticData.ser");
        List<Champion> list = new ArrayList<Champion>(staticData.getChampionList().getData().values());
        int i = 0;
        for (Champion c : list){
            trieMap.put(c.getName().toLowerCase(), i);
            i++;
        }
        WriterAndReader.write(trieMap, local);
    }


}


