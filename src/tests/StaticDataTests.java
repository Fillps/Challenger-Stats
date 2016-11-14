package tests;

import model.database.data_structure.TrieExtended;
import model.database.data_structure.TrieMapExtended;
import model.database.files.Path;
import model.database.files.WriterAndReader;
import model.database.stats_structure.entity.*;
import model.database.stats_structure.relationship.Champion_Entity;
import model.database.stats_structure.relationship.Champion_SummonerSpell;
import model.riot_api.StaticData;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.List;

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
        //frame.show();
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

        List<model.database.stats_structure.entity.Champion> list = WriterAndReader.read("arquivos/ListChampions.ser");
        System.out.println(list.toString());
        for (model.database.stats_structure.entity.Champion c : list){
            trieMap.put(c.getChampionData().getName().toLowerCase(), c.getID());
        }
        System.out.print(trieMap.toString());
        WriterAndReader.write(trieMap, local);
    }

    public static void saveChampionsList(String local){
        StaticData staticData = WriterAndReader.read("arquivos/StaticData.ser");
        List<net.rithms.riot.api.endpoints.static_data.dto.Champion> list;
        list = new ArrayList<net.rithms.riot.api.endpoints.static_data.dto.Champion>(staticData.getChampionList().getData().values());
        Collections.sort(list, new Comparator<net.rithms.riot.api.endpoints.static_data.dto.Champion>() {
            @Override
            public int compare(net.rithms.riot.api.endpoints.static_data.dto.Champion o1, net.rithms.riot.api.endpoints.static_data.dto.Champion o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        List<model.database.stats_structure.entity.Champion> listChampion = new ArrayList<model.database.stats_structure.entity.Champion>();
        int i = 0;
        for(net.rithms.riot.api.endpoints.static_data.dto.Champion c : list){
            listChampion.add(new model.database.stats_structure.entity.Champion(i,c));
            i++;
        }
        WriterAndReader.write(listChampion, local);
    }

    public static void saveAllEntitys(){
        StaticData staticData = WriterAndReader.read("arquivos/StaticData.ser");
        List<net.rithms.riot.api.endpoints.static_data.dto.Item> listRiotItem = new ArrayList<>(staticData.getItemList().getData().values());
        Collections.sort(listRiotItem, new Comparator<net.rithms.riot.api.endpoints.static_data.dto.Item>() {
            @Override
            public int compare(net.rithms.riot.api.endpoints.static_data.dto.Item o1, net.rithms.riot.api.endpoints.static_data.dto.Item o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        });
        List<Item> listItem = new ArrayList<>();
        int i = 0;
        for(net.rithms.riot.api.endpoints.static_data.dto.Item item : listRiotItem){
            listItem.add(new Item(i,item));
            i++;
        }

        List<net.rithms.riot.api.endpoints.static_data.dto.Mastery> listRiotMastery = new ArrayList<>(staticData.getMasteryList().getData().values());
        Collections.sort(listRiotMastery, (o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
        List<Mastery> listMastery = new ArrayList<>();
        i = 0;
        for(net.rithms.riot.api.endpoints.static_data.dto.Mastery mastery : listRiotMastery){
            listMastery.add(new Mastery(i,mastery));
            i++;
        }

        List<net.rithms.riot.api.endpoints.static_data.dto.Rune> listRiotRune = new ArrayList<>(staticData.getRuneList().getData().values());
        Collections.sort(listRiotRune, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        List<Rune> listRune = new ArrayList<>();
        i = 0;
        for(net.rithms.riot.api.endpoints.static_data.dto.Rune rune : listRiotRune){
            listRune.add(new Rune(i,rune));
            i++;
        }

        List<net.rithms.riot.api.endpoints.static_data.dto.SummonerSpell> listRiotSummonerSpell = new ArrayList<>(staticData.getSummonerSpellList().getData().values());
        Collections.sort(listRiotSummonerSpell, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        List<SummonerSpell> listSummonerSpell = new ArrayList<>();
        i = 0;
        for(net.rithms.riot.api.endpoints.static_data.dto.SummonerSpell summonerSpell : listRiotSummonerSpell){
            listSummonerSpell.add(new SummonerSpell(i,summonerSpell));
            i++;
        }

        List<RunePage> listRunePage = new ArrayList<>();
        List<MasteryPage> listMasteryPage = new ArrayList<>();

        List<Champion_SummonerSpell> listChampion_summonerSpell = new ArrayList<>();
        List<Champion_Entity> listChampion_Item = new ArrayList<>();
        List<Champion_Entity> listChampion_RunePage = new ArrayList<>();
        List<Champion_Entity> listChampion_MasteryPage = new ArrayList<>();


        WriterAndReader.write(listItem, Path.ITEM_LIST);
        WriterAndReader.write(listMastery, Path.MASTERY_LIST);
        WriterAndReader.write(listMasteryPage, Path.MASTERY_PAGE_LIST);
        WriterAndReader.write(listSummonerSpell, Path.SUMMONERSPEEL_LIST);

        WriterAndReader.write(listChampion_summonerSpell, Path.CHAMPION_SUMMONERSPELL_LIST);
        WriterAndReader.write(listChampion_Item, Path.CHAMPION_ITEM_LIST);
        WriterAndReader.write(listChampion_RunePage, Path.CHAMPION_RUNE_PAEG_LIST);
        WriterAndReader.write(listChampion_MasteryPage, Path.CHAMPION_MASTERY_PAGE_LIST);
    }

}


