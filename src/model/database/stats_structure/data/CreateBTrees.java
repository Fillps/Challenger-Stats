package model.database.stats_structure.data;

import model.database.data_structure.BTree;
import model.database.files.BinaryFiles;
import model.database.files.Path;
import model.database.files.WriterAndReader;
import model.database.stats_structure.entity.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by filip on 09/11/2016.
 */
public class CreateBTrees {
    public CreateBTrees(){

        BinaryFiles championF = new BinaryFiles();

        List<Champion> lista_champion = new ArrayList<Champion>();
        lista_champion = WriterAndReader.read(Path.CHAMPION_LIST);

        int i=0;
        do{
            championF.saveObj(lista_champion.get(i));
            i++;
        }while(i<lista_champion.size());

        List<Rune> lista_rune = new ArrayList<Rune>();
        lista_rune = WriterAndReader.read(Path.RUNE_LIST);
        i=0;
        do{
            championF.saveObj(lista_rune.get(i));
            i++;
        }while(i<lista_rune.size());

        List<Mastery> lista_mastery = new ArrayList<Mastery>();
        lista_mastery = WriterAndReader.read(Path.MASTERY_LIST);

        i=0;
        do{
            championF.saveObj(lista_mastery.get(i));
            i++;
        }while(i<lista_mastery.size());

        List<Item> lista_item = new ArrayList<Item>();
        lista_item = WriterAndReader.read(Path.ITEM_LIST);

        i=0;
        do{
            championF.saveObj(lista_item.get(i));
            i++;
        }while(i<lista_item.size());


        List<SummonerSpell> lista_summonerSpell = new ArrayList<SummonerSpell>();
        lista_summonerSpell = WriterAndReader.read(Path.SUMMONERSPEEL_LIST);

        i=0;
        do{
            championF.saveObj(lista_summonerSpell.get(i));
            i++;
        }while(i<lista_summonerSpell.size());


        WriterAndReader.write(new OverallStats(), Path.OVERALL_STATS);

        WriterAndReader.write(championRiotID(WriterAndReader.read(Path.CHAMPION_LIST)), Path.BTREE_CHAMPION_RIOT_ID);

        WriterAndReader.write(itemRiotID(WriterAndReader.read(Path.ITEM_LIST)), Path.BTREE_ITEM_RIOT_ID);

        WriterAndReader.write(masteryRiotID(WriterAndReader.read(Path.MASTERY_LIST)), Path.BTREE_MASTERY_RIOT_ID);

        WriterAndReader.write(runeRiotID(WriterAndReader.read(Path.RUNE_LIST)), Path.BTREE_RUNE_RIOT_ID);

        WriterAndReader.write(summonerSpellRiotID(WriterAndReader.read(Path.SUMMONERSPEEL_LIST)), Path.BTREE_SUMMONER_SPELL_RIOT_ID);

        WriterAndReader.write(runePageRiotID(), Path.BTREE_RUNE_PAGE);

        WriterAndReader.write(masteryPageRiotID(), Path.BTREE_MASTERY_PAGE);

        WriterAndReader.write(champEntity(), Path.BTREE_CHAMPION_RUNE_PAGE);

        WriterAndReader.write(champEntity(), Path.BTREE_CHAMPION_MASTERY_PAGE);

        WriterAndReader.write(champEntity(), Path.BTREE_CHAMPION_ITEM);

        WriterAndReader.write(champSummonerSpell(), Path.BTREE_CHAMPION_SUMMONER_SPELL);

        WriterAndReader.write(champMasteriesByChamp(), Path.BTREE_CHAMPION_MASTERY_PAGE_BY_CHAMP);

        WriterAndReader.write(champRunesByChamp(), Path.BTREE_CHAMPION_RUNE_PAGE_BY_CHAMP);

        WriterAndReader.write(champItemsByChamp(), Path.BTREE_CHAMPION_ITEM_BY_CHAMP);

        WriterAndReader.write(champSummonerSpellsByChamp(), Path.BTREE_CHAMPION_SUMMONER_SPELL_BY_CHAMP);

    }


    public static BTree<BasicInfo> championRiotID(List<Champion> listChampion){

        BTree<BasicInfo> bTree = new BTree<BasicInfo>(2);
        for (Champion champion : listChampion){
            bTree.add(new BasicInfo(champion.getID(), champion.getChampionData().getId()));
        }
        return bTree;
    }

    public static BTree<BasicInfo> summonerSpellRiotID(List<SummonerSpell> listSummonerSpell){

        BTree<BasicInfo> bTree = new BTree<BasicInfo>(2);
        for (SummonerSpell SummonerSpell : listSummonerSpell){
            bTree.add(new BasicInfo(SummonerSpell.getID(), SummonerSpell.getSummonerSpellData().getId())) ;
        }
        return bTree;
    }

    public static BTree<BasicInfo> runeRiotID(List<Rune> listRune){

        BTree<BasicInfo> bTree = new BTree<BasicInfo>(2);
        for (Rune Rune : listRune){
            bTree.add(new BasicInfo(Rune.getID(), Rune.getRuneData().getId()));
        }
        return bTree;
    }

    public static BTree<BasicInfo> masteryRiotID(List<Mastery> listMastery){

        BTree<BasicInfo> bTree = new BTree<BasicInfo>(2);
        for (Mastery Mastery : listMastery){
            bTree.add(new BasicInfo(Mastery.getID(), Mastery.getMasteryData().getId()));
        }
        return bTree;
    }

    public static BTree<BasicInfo> itemRiotID(List<Item> listItem){

        BTree<BasicInfo> bTree = new BTree<BasicInfo>(2);
        for (Item Item : listItem){
            bTree.add(new BasicInfo(Item.getID(), Item.getItemData().getId()));
        }
        return bTree;
    }

    public static BTree<MasteryPage> masteryPageRiotID(){
        BTree<MasteryPage> bTree = new BTree<>(2);
        return bTree;
    }

    public static BTree<RunePage> runePageRiotID(){
        BTree<RunePage> bTree = new BTree<>(2);
        return bTree;
    }

    public static BTree<ChampEntityBasicInfo> champEntity(){
        BTree<ChampEntityBasicInfo> bTree = new BTree<>(2);
        return bTree;
    }

    public static BTree<ChampSummonerSpellBasicInfo> champSummonerSpell(){
        BTree<ChampSummonerSpellBasicInfo> bTree = new BTree<>(2);
        return bTree;
    }

    public static BTree<RelationshipByChampion<ChampEntityBasicInfo>> champMasteriesByChamp(){
        BTree<RelationshipByChampion<ChampEntityBasicInfo>> bTree= new BTree<>(2);
        return bTree;
    }

    public static BTree<RelationshipByChampion<ChampEntityBasicInfo>> champRunesByChamp(){
        BTree<RelationshipByChampion<ChampEntityBasicInfo>> bTree= new BTree<>(2);
        return bTree;
    }
    public static BTree<RelationshipByChampion<ChampEntityBasicInfo>> champItemsByChamp(){
        BTree<RelationshipByChampion<ChampEntityBasicInfo>> bTree= new BTree<>(2);
        return bTree;
    }
    public static BTree<RelationshipByChampion<ChampSummonerSpellBasicInfo>> champSummonerSpellsByChamp(){
        BTree<RelationshipByChampion<ChampSummonerSpellBasicInfo>> bTree= new BTree<>(2);
        return bTree;
    }

}
