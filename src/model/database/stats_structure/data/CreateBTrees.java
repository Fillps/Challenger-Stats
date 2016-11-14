package model.database.stats_structure.data;

import model.database.data_structure.BTree;
import model.database.stats_structure.entity.*;

import java.util.List;

/**
 * Created by filip on 09/11/2016.
 */
public class CreateBTrees {

    public static BTree<BasicInfo> championRiotID(List<Champion> listChampion){

        BTree<BasicInfo> bTree = new BTree<BasicInfo>(2);
        for (Champion champion : listChampion){
            bTree.add(new BasicInfo(champion.getID(), champion.getChampionData().getId()));
        }
        return bTree;
    }

    public static BTree<BasicInfo> SummonerSpellRiotID(List<SummonerSpell> listSummonerSpell){

        BTree<BasicInfo> bTree = new BTree<BasicInfo>(2);
        for (SummonerSpell SummonerSpell : listSummonerSpell){
            bTree.add(new BasicInfo(SummonerSpell.getID(), SummonerSpell.getSummonerSpellData().getId())) ;
        }
        return bTree;
    }

    public static BTree<BasicInfo> RuneRiotID(List<Rune> listRune){

        BTree<BasicInfo> bTree = new BTree<BasicInfo>(2);
        for (Rune Rune : listRune){
            bTree.add(new BasicInfo(Rune.getID(), Rune.getRuneData().getId()));
        }
        return bTree;
    }

    public static BTree<BasicInfo> MasteryRiotID(List<Mastery> listMastery){

        BTree<BasicInfo> bTree = new BTree<BasicInfo>(2);
        for (Mastery Mastery : listMastery){
            bTree.add(new BasicInfo(Mastery.getID(), Mastery.getMasteryData().getId()));
        }
        return bTree;
    }

    public static BTree<BasicInfo> ItemRiotID(List<Item> listItem){

        BTree<BasicInfo> bTree = new BTree<BasicInfo>(2);
        for (Item Item : listItem){
            bTree.add(new BasicInfo(Item.getID(), Item.getItemData().getId()));
        }
        return bTree;
    }

    public static BTree<MasteryPage> MasteryPageRiotID(){
        BTree<MasteryPage> bTree = new BTree<>(2);
        return bTree;
    }

    public static BTree<RunePage> RunePageRiotID(){
        BTree<RunePage> bTree = new BTree<>(2);
        return bTree;
    }

    public static BTree<ChampEntityBasicInfo> ChampEntity(){
        BTree<ChampEntityBasicInfo> bTree = new BTree<>(2);
        return bTree;
    }

    public static BTree<ChampSummonerSpellBasicInfo> ChampSummonerSpell(){
        BTree<ChampSummonerSpellBasicInfo> bTree = new BTree<>(2);
        return bTree;
    }


}
