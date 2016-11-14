package model.database.files;

import model.database.data_structure.BTree;
import model.database.data_structure.TrieMapExtended;
import model.database.stats_structure.data.*;
import model.database.stats_structure.entity.*;
import model.database.stats_structure.entity.Mastery;
import model.database.stats_structure.entity.Rune;
import model.database.stats_structure.relationship.Champion_Entity;
import model.database.stats_structure.relationship.Champion_SummonerSpell;
import net.rithms.riot.api.endpoints.match.dto.*;
import net.rithms.riot.api.endpoints.static_data.dto.BasicDataStats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by filip on 30/10/2016.
 */
public class Database {

    private TrieMapExtended<String, Integer> trieChampionsNames;

    private BTree<BasicInfo> bTreeChampionRiotID;
    private BTree<BasicInfo> bTreeMasteryRiotID;
    private BTree<BasicInfo> bTreeItemRiotID;
    private BTree<BasicInfo> bTreeRuneRiotID;
    private BTree<BasicInfo> bTreeSummonerSpellRiotID;

    private BTree<MasteryPage> bTreeMasteryPagebyMasteryID;
    private BTree<RunePage> bTreeRunePagebyRuneID;

    private BTree<ChampEntityBasicInfo> bTreeChampMasteryPage;
    private BTree<ChampEntityBasicInfo> bTreeChampRunePage;


    private BTree<ChampEntityBasicInfo> bTreeChampItem;
    private BTree<ChampSummonerSpellBasicInfo> bTreeChampSummonerSpell;

    private OverallStats overallStats;

    private List<Champion> listChampions;
    private List<Mastery> listMastery;
    private List<Item> listItem;
    private List<Rune> listRune;
    private List<SummonerSpell> listSummonerSpell;

    private List<Champion_Entity> listChampion_RunePage;
    private List<Champion_Entity> listChampion_Item;
    private List<Champion_Entity> listChampion_MasteryPage;
    private List<Champion_SummonerSpell> listChampion_summonerSpell;
    private List<MasteryPage> listMasteryPage;
    private List<RunePage> listRunePage;


    public Database(){
        this.trieChampionsNames = WriterAndReader.read(Path.TRIE_CHAMPIONS_NAMES);
        this.overallStats = WriterAndReader.read(Path.OVERALL_STATS);

        /*this.bTreeChampionRiotID = WriterAndReader.read(Path.BTREE_CHAMPION_RIOT_ID);
        this.bTreeItemRiotID = WriterAndReader.read(Path.BTREE_ITEM_RIOT_ID);
        this.bTreeMasteryRiotID = WriterAndReader.read(Path.BTREE_MASTERY_RIOT_ID);
        this.bTreeMasteryPagebyMasteryID = WriterAndReader.read(Path.BTREE_MASTERY_PAGE_RIOT_ID);
        this.bTreeRuneRiotID = WriterAndReader.read(Path.BTREE_RUNE_RIOT_ID);
        this.bTreeRunePagebyRuneID = WriterAndReader.read(Path.BTREE_RUNE_PAGE_RIOT_ID);
        this.bTreeSummonerSpellRiotID = WriterAndReader.read(Path.BTREE_SUMMONER_SPELL_RIOT_ID);

        this.bTreeChampMasteryPage = WriterAndReader.read(Path.BTREE_CHAMP_MASTERY_PAGE);
        this.bTreeChampItem = WriterAndReader.read(Path.BTREE_CHAMP_ITEM);
        this.bTreeChampRunePage = WriterAndReader.read(Path.BTREE_CHAMP_RUNE_PAGE);
        this.bTreeChampSummonerSpell = WriterAndReader.read(Path.BTREE_CHAMP_SUMMONER_SPELL);*/

        this.listChampions = WriterAndReader.read(Path.CHAMPION_LIST);
        this.listItem = WriterAndReader.read(Path.ITEM_LIST);
        this.listMastery = WriterAndReader.read(Path.MASTERY_LIST);
        this.listSummonerSpell = WriterAndReader.read(Path.SUMMONERSPEEL_LIST);
        this.listRune = WriterAndReader.read(Path.RUNE_LIST);

        this.listChampion_summonerSpell = WriterAndReader.read(Path.CHAMPION_SUMMONERSPELL_LIST);
        this.listChampion_Item = WriterAndReader.read(Path.CHAMPION_ITEM_LIST);
        this.listChampion_RunePage = WriterAndReader.read(Path.CHAMPION_RUNE_PAEG_LIST);
        this.listChampion_MasteryPage = WriterAndReader.read(Path.CHAMPION_MASTERY_PAGE_LIST);


    }

    public TrieMapExtended<String, Integer> getTrieChampionsNames() {
        return trieChampionsNames;
    }

    public Champion getChampion(int id){
        return  listChampions.get(id);
    }

    public List<Champion> getAllChampions(){
        return listChampions;
    }

    public OverallStats getOverallStats(){
        return overallStats;
    }

    public void MatchAnalyser(MatchDetail matchDetail){

        for (Team team : matchDetail.getTeams()){
            for (BannedChampion ban : team.getBans()){
                BasicInfo champBasicInfo = new BasicInfo(bTreeChampionRiotID.size(), ban.getChampionId());
                if (!bTreeChampionRiotID.contains(champBasicInfo))
                    continue;
                int championID = bTreeChampionRiotID.getValue(champBasicInfo).getID();
                Champion champion = getChampion(championID);
                champion.addBan();
                overallStats.addBan();
                //saveChampion(champion);
            }
        }

        for(Participant participant : matchDetail.getParticipants()){
            BasicInfo champBasicInfo = new BasicInfo(bTreeChampionRiotID.size(), participant.getChampionId());

            if (!bTreeChampionRiotID.contains(champBasicInfo))
                continue;
            int championID = bTreeChampionRiotID.getValue(champBasicInfo).getID();
            boolean win = participant.getStats().isWinner();
            championStatsAnalyser(championID, participant.getStats());
            masteryAnalyser(participant.getMasteries(), championID, win);
            summonerSpellAnalyser(championID, participant.getSpell1Id(),participant.getSpell2Id(), win);
            runeAnalyser(championID, participant.getRunes(), win);

            itemAnalyser(championID, participant.getStats().getItem0(), win);
            itemAnalyser(championID, participant.getStats().getItem1(), win);
            itemAnalyser(championID, participant.getStats().getItem2(), win);
            itemAnalyser(championID, participant.getStats().getItem3(), win);
            itemAnalyser(championID, participant.getStats().getItem4(), win);
            itemAnalyser(championID, participant.getStats().getItem5(), win);
            itemAnalyser(championID, participant.getStats().getItem6(), win);

            overallStats.addChampion();
        }
        overallStats.addMatch();
        //saveOverallStats();
    }

    private void masteryAnalyser(List<net.rithms.riot.api.endpoints.match.dto.Mastery> masterys, int championID, boolean win){

        List<MasteryID> masteryIDlist = new ArrayList<>();
        int ferocity = 0;
        int cunning = 0;
        int resolve = 0;
        for(net.rithms.riot.api.endpoints.match.dto.Mastery riotmastery : masterys){
            int masteryID = bTreeMasteryRiotID.getValue(new BasicInfo(0, riotmastery.getMasteryId())).getID();
            String tree = getMastery(masteryID).getMasteryData().getMasteryTree();
            masteryIDlist.add(new MasteryID(masteryID, riotmastery.getRank()));
            if(tree.compareToIgnoreCase("Ferocity") == 0)
                ferocity =+ riotmastery.getRank();
            else if(tree.compareToIgnoreCase("Cunning") == 0)
                cunning =+ riotmastery.getRank();
            else
                resolve = +riotmastery.getRank();
        }

        Collections.sort(masteryIDlist);

        MasteryPage masteryPage = new MasteryPage(bTreeMasteryPagebyMasteryID.size(), masteryIDlist, new MasteryTree(ferocity,cunning,resolve));
        if (bTreeMasteryPagebyMasteryID.contains(masteryPage)){
            masteryPage = bTreeMasteryPagebyMasteryID.getValue(masteryPage);
        }
        else{
            bTreeMasteryPagebyMasteryID.add(masteryPage);
            //saveBTreeMasteryPage();
            //saveMasteryPage()
        }

        ChampEntityBasicInfo relation = new ChampEntityBasicInfo(bTreeChampMasteryPage.size(), championID, masteryPage.getID());

        Champion_Entity champMasteryPage= null;

        if (bTreeChampMasteryPage.contains(relation)){
            int relationID = bTreeChampMasteryPage.getValue(relation).getID();
            //champMasteryPage = getChampion_MasteryPage(ID)
        }
        else{
            bTreeChampMasteryPage.add(relation);
            //savebbTreeChampMasteryPage
            champMasteryPage = new Champion_Entity(relation.getID(), relation.getChampion_ID(), relation.getEntity_ID());
        }
        champMasteryPage.addGame(win);
        //saveChampion_MasteryPage(champMasteryPage);
    }

    private void championStatsAnalyser(int championID, ParticipantStats stats){
        Champion champion = getChampion(championID);
        champion.addGame(stats.isWinner(), stats.getGoldEarned(),stats.getKills(),stats.getAssists(),stats.getDeaths(),
                stats.getTotalHeal(),stats.getVisionWardsBoughtInGame(),stats.getWardsPlaced(),stats.getWardsKilled(),
                stats.getTotalDamageDealt(),stats.getTotalDamageDealtToChampions(),stats.getTotalDamageTaken(),stats.getMinionsKilled());
        //saveChampion(champion);
    }

    private void summonerSpellAnalyser(int championID, int spellRiotID_1, int spellRiotID_2, boolean win){

        BasicInfo spell_0 = new BasicInfo(0, spellRiotID_1);
        BasicInfo spell_1 = new BasicInfo(0, spellRiotID_2);

        if (!bTreeSummonerSpellRiotID.contains(spell_0) || !bTreeSummonerSpellRiotID.contains(spell_1)){
            System.out.println("Summoner spell nao encontrado! ids:" + spellRiotID_1 + " e " + spellRiotID_2);
            return;
        }
        int id_0 = bTreeSummonerSpellRiotID.getValue(spell_0).getID();
        int id_1 = bTreeSummonerSpellRiotID.getValue(spell_1).getID();

        int summonerSpell_0_ID = Math.min(id_0, id_1);
        int summonerSpell_1_ID = Math.max(id_0, id_1);

        ChampSummonerSpellBasicInfo champSpellInfo = new ChampSummonerSpellBasicInfo(bTreeChampSummonerSpell.size(),
                championID, summonerSpell_0_ID, summonerSpell_1_ID);

        Champion_SummonerSpell champion_summonerSpell = null;

        if (bTreeChampSummonerSpell.contains(champSpellInfo)){
            int champSpellID = bTreeChampSummonerSpell.getValue(champSpellInfo).getID();
            //champion_summonerSpell = getChampion_SummonerSpell(champSpellID)

        }
        else {
            bTreeChampSummonerSpell.add(champSpellInfo);
            champion_summonerSpell = new Champion_SummonerSpell(champSpellInfo.getID(),
                    champSpellInfo.getSummonerSpell_0_ID(),champSpellInfo.getSummonerSpell_1_ID(), champSpellInfo.getChampion_ID());
            //saveBTree(bTreeChampSummonerSpell);

        }
        champion_summonerSpell.addGame(win);
        //saveChampionSummonerSpell(champion_summonerSpell);
    }

    private void runeAnalyser(int championID, List<net.rithms.riot.api.endpoints.match.dto.Rune> runes, boolean win){

        List<RuneID> runeIDlist = new ArrayList<>();
        double physical = 0;
        double magic = 0;
        double resistance = 0;

        for(net.rithms.riot.api.endpoints.match.dto.Rune riotRune : runes){
            int runeID = bTreeRuneRiotID.getValue(new BasicInfo(0, riotRune.getRuneId())).getID();
            runeIDlist.add(new RuneID(runeID, riotRune.getRank()));

            BasicDataStats stats = getRune(runeID).getRuneData().getStats();

            physical=+(stats.getFlatPhysicalDamageMod()+stats.getPercentPhysicalDamageMod()+
                    stats.getrFlatPhysicalDamageModPerLevel()+stats.getFlatPhysicalDamageMod())*riotRune.getRank();

            magic=+(stats.getFlatPhysicalDamageMod()+stats.getFlatMagicDamageMod()+stats.getPercentMagicDamageMod()+
                    stats.getrFlatMagicDamageModPerLevel())*riotRune.getRank();

            resistance=+(stats.getFlatPhysicalDamageMod()+stats.getFlatArmorMod() + stats.getPercentArmorMod() +
                    stats.getrFlatArmorModPerLevel()+stats.getFlatHPPoolMod() + stats.getPercentHPPoolMod() +
                    stats.getrFlatHPModPerLevel() +stats.getPercentSpellBlockMod() + stats.getFlatSpellBlockMod()
                    + stats.getrFlatSpellBlockModPerLevel())*riotRune.getRank();
        }

        Collections.sort(runeIDlist);



        RunePage runePage = new RunePage(bTreeRunePagebyRuneID.size(), runeIDlist, physical, magic, resistance);
        if (bTreeRunePagebyRuneID.contains(runePage)){
            runePage = bTreeRunePagebyRuneID.getValue(runePage);
        }
        else{
            bTreeRunePagebyRuneID.add(runePage);
            //saveBTreeRunePage();
            //saveRunePage(runePage);
        }

        ChampEntityBasicInfo relation = new ChampEntityBasicInfo(bTreeChampRunePage.size(), championID, runePage.getID());

        Champion_Entity champRunePage= null;

        if (bTreeChampRunePage.contains(relation)){
            int relationID = bTreeChampRunePage.getValue(relation).getID();
            //champRunePage = getChampion_RunePage(ID)
        }
        else{
            bTreeChampRunePage.add(relation);
            //savebTreeChampRunePage
            champRunePage = new Champion_Entity(relation.getID(), relation.getChampion_ID(), relation.getEntity_ID());
        }
        champRunePage.addGame(win);
        //saveChampion_RunePage(champRunePage);
    }

    private void itemAnalyser(int championID, int itemRiot_ID, boolean win){
        BasicInfo item = new BasicInfo(0, itemRiot_ID);

        if (!bTreeItemRiotID.contains(item)){
            System.out.println(" item nao encontrado! id: " + itemRiot_ID );
            return;
        }
        int itemID = bTreeItemRiotID.getValue(item).getID();

        Champion_Entity champItem = null;
        ChampEntityBasicInfo champItemInfo = new ChampEntityBasicInfo(bTreeChampItem.size(), championID, itemID);

        if (bTreeChampItem.contains(champItemInfo)){
            int champItemID = bTreeChampItem.getValue(champItemInfo).getID();
            //champItem = getChampion_Item(champItemID)

        }
        else {
            bTreeChampItem.add(champItemInfo);
            champItem = new Champion_Entity(champItemInfo.getID(), champItemInfo.getChampion_ID(), champItemInfo.getEntity_ID());
            //saveBTree(bTreeChampItem);
        }
        champItem.addGame(win);
        //saveChampionItem(champItem);
    }

    public Item getItem(int itemID) {
        return listItem.get(itemID);
    }

    public SummonerSpell getSummonerSpell(int summonerSpellID) {
        return listSummonerSpell.get(summonerSpellID);
    }

    public Mastery getMastery(int masteryID) {
        return listMastery.get(masteryID);
    }

    public Rune getRune(int runeID) {
        return listRune.get(runeID);
    }

    public RunePage getRunePage(int runePageID) {
        return listRunePage.get(runePageID);
    }

    public MasteryPage getMasteryPage(int masteryPageID) {
        return listMasteryPage.get(masteryPageID);
    }
}
