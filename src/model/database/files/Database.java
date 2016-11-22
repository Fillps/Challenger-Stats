package model.database.files;

import model.database.data_structure.BTree;
import model.database.data_structure.TrieMapExtended;
import model.database.stats_structure.data.*;
import model.database.stats_structure.entity.*;
import model.database.stats_structure.entity.Mastery;
import model.database.stats_structure.entity.Rune;
import model.database.stats_structure.relationship.*;
import net.rithms.riot.api.endpoints.match.dto.*;
import net.rithms.riot.api.endpoints.static_data.dto.BasicDataStats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by filip on 30/10/2016.
 */
public class Database {

    BinaryFiles files = new BinaryFiles();

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

    private BTree<RelationshipByChampion<ChampEntityBasicInfo>> bTreeChampMasteryPageByChamp;
    private BTree<RelationshipByChampion<ChampEntityBasicInfo>> bTreeChampRunePageByChamp;

    private BTree<RelationshipByChampion<ChampEntityBasicInfo>> bTreeChampItemByChamp;
    private BTree<RelationshipByChampion<ChampSummonerSpellBasicInfo>> bTreeChampSummonerSpellByChamp;

    private OverallStats overallStats;





    public Database(){
        this.trieChampionsNames = WriterAndReader.read(Path.TRIE_CHAMPIONS_NAMES);
        this.overallStats = WriterAndReader.read(Path.OVERALL_STATS);

        this.bTreeChampionRiotID = WriterAndReader.read(Path.BTREE_CHAMPION_RIOT_ID);
        this.bTreeItemRiotID = WriterAndReader.read(Path.BTREE_ITEM_RIOT_ID);
        this.bTreeMasteryRiotID = WriterAndReader.read(Path.BTREE_MASTERY_RIOT_ID);
        this.bTreeMasteryPagebyMasteryID = WriterAndReader.read(Path.BTREE_MASTERY_PAGE);
        this.bTreeRuneRiotID = WriterAndReader.read(Path.BTREE_RUNE_RIOT_ID);
        this.bTreeRunePagebyRuneID = WriterAndReader.read(Path.BTREE_RUNE_PAGE);
        this.bTreeSummonerSpellRiotID = WriterAndReader.read(Path.BTREE_SUMMONER_SPELL_RIOT_ID);

        this.bTreeChampMasteryPage = WriterAndReader.read(Path.BTREE_CHAMPION_MASTERY_PAGE);
        this.bTreeChampItem = WriterAndReader.read(Path.BTREE_CHAMPION_ITEM);
        this.bTreeChampRunePage = WriterAndReader.read(Path.BTREE_CHAMPION_RUNE_PAGE);
        this.bTreeChampSummonerSpell = WriterAndReader.read(Path.BTREE_CHAMPION_SUMMONER_SPELL);

        this.bTreeChampMasteryPageByChamp = WriterAndReader.read(Path.BTREE_CHAMPION_MASTERY_PAGE_BY_CHAMP);
        this.bTreeChampRunePageByChamp = WriterAndReader.read(Path.BTREE_CHAMPION_RUNE_PAGE_BY_CHAMP);
        this.bTreeChampItemByChamp = WriterAndReader.read(Path.BTREE_CHAMPION_ITEM_BY_CHAMP);
        this.bTreeChampSummonerSpellByChamp = WriterAndReader.read(Path.BTREE_CHAMPION_SUMMONER_SPELL_BY_CHAMP);

    }

    public TrieMapExtended<String, Integer> getTrieChampionsNames() {
        return trieChampionsNames;
    }

    public Champion getChampion(int id){
        return (Champion) files.getObj(id, BinaryFiles.CHAMPION);
    }

    public List<Integer> getAllChampionsIds(){
        List<Integer> list = new ArrayList<>();
        for (int i=0; i < 133; i++)//MUDAR ISSO
            list.add(i);
        return list;
    }

    public OverallStats getOverallStats(){
        return overallStats;
    }

    public void MatchAnalyser(MatchDetail matchDetail){

        for (Team team : matchDetail.getTeams()){
            try{
                for (BannedChampion ban : team.getBans()){
                    BasicInfo champBasicInfo = new BasicInfo(bTreeChampionRiotID.size(), ban.getChampionId());
                    if (!bTreeChampionRiotID.contains(champBasicInfo))
                        continue;
                    int championID = bTreeChampionRiotID.getValue(champBasicInfo).getID();
                    Champion champion = getChampion(championID);
                    champion.addBan();
                    overallStats.addBan();
                    files.saveObj(champion);
                }
            }catch (NullPointerException e){
                continue;
            }
        }

        for(Participant participant : matchDetail.getParticipants()){
            BasicInfo champBasicInfo = new BasicInfo(bTreeChampionRiotID.size(), participant.getChampionId());

            if (!bTreeChampionRiotID.contains(champBasicInfo))
                continue;
            int championID;
            try {
                championID = bTreeChampionRiotID.getValue(champBasicInfo).getID();
            }catch (NullPointerException e) {
                continue;
            }
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
        WriterAndReader.write(overallStats, Path.OVERALL_STATS);
    }

    private void masteryAnalyser(List<net.rithms.riot.api.endpoints.match.dto.Mastery> masteries, int championID, boolean win){

        if (masteries==null){
            return;
        }
        List<MasteryID> masteryIDlist = new ArrayList<>();
        int ferocity = 0;
        int cunning = 0;
        int resolve = 0;
        for(net.rithms.riot.api.endpoints.match.dto.Mastery riotmastery : masteries){
            int masteryID;
            try {
                masteryID = bTreeMasteryRiotID.getValue(new BasicInfo(0, riotmastery.getMasteryId())).getID();
            }catch (NullPointerException e){
                continue;
            }
            String tree = getMastery(masteryID).getMasteryData().getMasteryTree();
            masteryIDlist.add(new MasteryID(masteryID, riotmastery.getRank()));
            if(tree.compareToIgnoreCase("Ferocity") == 0)
                ferocity += riotmastery.getRank();
            else if(tree.compareToIgnoreCase("Cunning") == 0)
                cunning += riotmastery.getRank();
            else
                resolve += +riotmastery.getRank();
        }
        if (masteryIDlist.isEmpty())
            return;
        Collections.sort(masteryIDlist);

        MasteryTree masteryTree = new MasteryTree(ferocity,cunning,resolve);
        MasteryPage masteryPage = new MasteryPage(bTreeMasteryPagebyMasteryID.size(), masteryIDlist, masteryTree);
        if (bTreeMasteryPagebyMasteryID.contains(masteryPage)){
            masteryPage = bTreeMasteryPagebyMasteryID.getValue(masteryPage);
        }
        else{
            bTreeMasteryPagebyMasteryID.add(masteryPage);
            WriterAndReader.write(bTreeMasteryPagebyMasteryID, Path.BTREE_MASTERY_PAGE);
            files.saveObj(masteryPage);
        }

        ChampEntityBasicInfo relation = new ChampEntityBasicInfo(bTreeChampMasteryPage.size(), championID, masteryPage.getID());

        Champion_MasteryPage champMasteryPage= null;

        if (bTreeChampMasteryPage.contains(relation)){
            int relationID = bTreeChampMasteryPage.getValue(relation).getID();
            champMasteryPage = (Champion_MasteryPage) files.getObj(relationID, BinaryFiles.CHAMPION_MASTERY_PAGE);
        }
        else{
            bTreeChampMasteryPage.add(relation);
            WriterAndReader.write(bTreeChampMasteryPage, Path.BTREE_CHAMPION_MASTERY_PAGE);//savebbTreeChampMasteryPage
            champMasteryPage = new Champion_MasteryPage(relation.getID(), relation.getChampion_ID(), relation.getEntity_ID());

            RelationshipByChampion<ChampEntityBasicInfo> champRelation = new RelationshipByChampion<ChampEntityBasicInfo>(championID, relation);

            if (bTreeChampMasteryPageByChamp.contains(champRelation)){
                champRelation = bTreeChampMasteryPageByChamp.getValue(champRelation);
                champRelation.getList().add(relation);
            }
            else {
                bTreeChampMasteryPageByChamp.add(champRelation);
            }
            WriterAndReader.write(bTreeChampMasteryPageByChamp, Path.BTREE_CHAMPION_MASTERY_PAGE_BY_CHAMP);
        }

        champMasteryPage.addGame(win);
        files.saveObj(champMasteryPage);
    }

    private void championStatsAnalyser(int championID, ParticipantStats stats){
        if (stats==null)
            return;
        Champion champion = getChampion(championID);
        champion.addGame(stats.isWinner(), stats.getGoldEarned(),stats.getKills(),stats.getAssists(),stats.getDeaths(),
                stats.getTotalHeal(),stats.getVisionWardsBoughtInGame(),stats.getWardsPlaced(),stats.getWardsKilled(),
                stats.getTotalDamageDealt(),stats.getTotalDamageDealtToChampions(),stats.getTotalDamageTaken(),stats.getMinionsKilled());
        files.saveObj(champion);
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
            champion_summonerSpell = (Champion_SummonerSpell) files.getObj(champSpellID, BinaryFiles.CHAMPION_SUMMONER_SPELL);
        }
        else {
            bTreeChampSummonerSpell.add(champSpellInfo);
            champion_summonerSpell = new Champion_SummonerSpell(champSpellInfo.getID(),
                    champSpellInfo.getSummonerSpell_0_ID(),champSpellInfo.getSummonerSpell_1_ID(), champSpellInfo.getChampion_ID());
            WriterAndReader.write(bTreeChampSummonerSpell, Path.BTREE_CHAMPION_SUMMONER_SPELL);//saveBTree(bTreeChampSummonerSpell);

            RelationshipByChampion<ChampSummonerSpellBasicInfo> champRelation = new RelationshipByChampion<ChampSummonerSpellBasicInfo>(championID, champSpellInfo);

            if (bTreeChampSummonerSpellByChamp.contains(champRelation)){
                champRelation = bTreeChampSummonerSpellByChamp.getValue(champRelation);
                champRelation.getList().add(champSpellInfo);
            }
            else {
                bTreeChampSummonerSpellByChamp.add(champRelation);
            }
            WriterAndReader.write(bTreeChampSummonerSpellByChamp, Path.BTREE_CHAMPION_SUMMONER_SPELL_BY_CHAMP);
        }
        champion_summonerSpell.addGame(win);
        files.saveObj(champion_summonerSpell);//saveChampionSummonerSpell(champion_summonerSpell);
    }

    private void runeAnalyser(int championID, List<net.rithms.riot.api.endpoints.match.dto.Rune> runes, boolean win){

        if (runes==null){
            return;
        }
        List<RuneID> runeIDlist = new ArrayList<>();
        double physical = 0;
        double magic = 0;
        double resistance = 0;

        for(net.rithms.riot.api.endpoints.match.dto.Rune riotRune : runes){
            int runeID;
            try {
                runeID = bTreeRuneRiotID.getValue(new BasicInfo(0, riotRune.getRuneId())).getID();
            }catch (NullPointerException e){
                continue;
            }
            runeIDlist.add(new RuneID(runeID, riotRune.getRank()));

            BasicDataStats stats = getRune(runeID).getRuneData().getStats();

            physical+=(stats.getFlatPhysicalDamageMod()+stats.getPercentPhysicalDamageMod()+
                    stats.getrFlatPhysicalDamageModPerLevel()+stats.getFlatPhysicalDamageMod())*riotRune.getRank();

            magic+=(stats.getFlatPhysicalDamageMod()+stats.getFlatMagicDamageMod()+stats.getPercentMagicDamageMod()+
                    stats.getrFlatMagicDamageModPerLevel())*riotRune.getRank();

            resistance+=(stats.getFlatPhysicalDamageMod()+stats.getFlatArmorMod() + stats.getPercentArmorMod() +
                    stats.getrFlatArmorModPerLevel()+stats.getFlatHPPoolMod() + stats.getPercentHPPoolMod() +
                    stats.getrFlatHPModPerLevel() +stats.getPercentSpellBlockMod() + stats.getFlatSpellBlockMod()
                    + stats.getrFlatSpellBlockModPerLevel())*riotRune.getRank();
        }

        if (runeIDlist.isEmpty())
            return;

        Collections.sort(runeIDlist);



        RunePage runePage = new RunePage(bTreeRunePagebyRuneID.size(), runeIDlist, physical, magic, resistance);
        if (bTreeRunePagebyRuneID.contains(runePage)){
            runePage = bTreeRunePagebyRuneID.getValue(runePage);
        }
        else{
            bTreeRunePagebyRuneID.add(runePage);
            WriterAndReader.write(bTreeRunePagebyRuneID, Path.BTREE_RUNE_PAGE);//saveBTreeRunePage();
            files.saveObj(runePage);//saveRunePage(runePage);
        }

        ChampEntityBasicInfo relation = new ChampEntityBasicInfo(bTreeChampRunePage.size(), championID, runePage.getID());

        Champion_RunePage champRunePage= null;

        if (bTreeChampRunePage.contains(relation)){
            int relationID = bTreeChampRunePage.getValue(relation).getID();
            champRunePage = (Champion_RunePage) files.getObj(relationID, BinaryFiles.CHAMPION_RUNE_PAGE);
        }
        else{
            bTreeChampRunePage.add(relation);
            WriterAndReader.write(bTreeChampRunePage, Path.BTREE_CHAMPION_RUNE_PAGE);
            champRunePage = new Champion_RunePage(relation.getID(), relation.getChampion_ID(), relation.getEntity_ID());

            RelationshipByChampion<ChampEntityBasicInfo> champRelation = new RelationshipByChampion<ChampEntityBasicInfo>(championID, relation);

            if (bTreeChampRunePageByChamp.contains(champRelation)){
                champRelation = bTreeChampRunePageByChamp.getValue(champRelation);
                champRelation.getList().add(relation);
            }
            else {
                bTreeChampRunePageByChamp.add(champRelation);
            }
            WriterAndReader.write(bTreeChampRunePageByChamp, Path.BTREE_CHAMPION_RUNE_PAGE_BY_CHAMP);
        }
        champRunePage.addGame(win);
        files.saveObj(champRunePage);
    }

    private void itemAnalyser(int championID, int itemRiot_ID, boolean win){
        BasicInfo item = new BasicInfo(0, itemRiot_ID);

        if (!bTreeItemRiotID.contains(item)){
            return;
        }
        int itemID = bTreeItemRiotID.getValue(item).getID();

        Champion_Item champItem = null;
        ChampEntityBasicInfo champItemInfo = new ChampEntityBasicInfo(bTreeChampItem.size(), championID, itemID);

        if (bTreeChampItem.contains(champItemInfo)){
            int champItemID = bTreeChampItem.getValue(champItemInfo).getID();
            champItem = (Champion_Item) files.getObj(champItemID, BinaryFiles.CHAMPION_ITEM);

        }
        else {
            bTreeChampItem.add(champItemInfo);
            champItem = new Champion_Item(champItemInfo.getID(), champItemInfo.getChampion_ID(), champItemInfo.getEntity_ID());
            WriterAndReader.write(bTreeChampItem, Path.BTREE_CHAMPION_ITEM);

            RelationshipByChampion<ChampEntityBasicInfo> champRelation = new RelationshipByChampion<ChampEntityBasicInfo>(championID, champItemInfo);

            if (bTreeChampItemByChamp.contains(champRelation)){
                champRelation = bTreeChampItemByChamp.getValue(champRelation);
                champRelation.getList().add(champItemInfo);
            }
            else {
                bTreeChampItemByChamp.add(champRelation);
            }
            WriterAndReader.write(bTreeChampItemByChamp, Path.BTREE_CHAMPION_ITEM_BY_CHAMP);
        }
        champItem.addGame(win);
        files.saveObj(champItem);
    }

    public Item getItem(int itemID) {
        return (Item) files.getObj(itemID, BinaryFiles.ITEM);
    }

    public SummonerSpell getSummonerSpell(int summonerSpellID) {
        return (SummonerSpell) files.getObj(summonerSpellID, BinaryFiles.SUMMONERSPELL);
    }

    public Mastery getMastery(int masteryID) {
        return (Mastery) files.getObj(masteryID, BinaryFiles.MASTERY);
    }

    public Rune getRune(int runeID) {
        return (Rune) files.getObj(runeID, BinaryFiles.RUNE);
    }

    public RunePage getRunePage(int runePageID) {
        return (RunePage) files.getObj(runePageID, BinaryFiles.RUNE_PAGE);
    }

    public MasteryPage getMasteryPage(int masteryPageID) {
        return (MasteryPage) files.getObj(masteryPageID, BinaryFiles.MASTERY_PAGE);
    }

    public List<Champion_Entity> getChampionMasteries(int championId){
        List<Champion_Entity> list = new ArrayList<>();
        RelationshipByChampion<ChampEntityBasicInfo> relation = new RelationshipByChampion<ChampEntityBasicInfo>(championId, new ChampEntityBasicInfo(0,0,0));
        if (!bTreeChampMasteryPageByChamp.contains(relation))
            return list;
        List<ChampEntityBasicInfo> champMastery = bTreeChampMasteryPageByChamp.getValue(relation).getList();

        for(ChampEntityBasicInfo basicInfo : champMastery){
            list.add((Champion_Entity)files.getObj(basicInfo.getID(), BinaryFiles.CHAMPION_MASTERY_PAGE));
        }
        return list;
    }

    public List<Champion_Entity> getChampionItems(int championId){
        List<Champion_Entity> list = new ArrayList<>();
        RelationshipByChampion<ChampEntityBasicInfo> relation = new RelationshipByChampion<ChampEntityBasicInfo>(championId, new ChampEntityBasicInfo(0,0,0));
        if (!bTreeChampItemByChamp.contains(relation))
            return list;

        List<ChampEntityBasicInfo> champItem = bTreeChampItemByChamp.getValue(relation).getList();

        for(ChampEntityBasicInfo basicInfo : champItem){
            list.add((Champion_Entity)files.getObj(basicInfo.getID(), BinaryFiles.CHAMPION_ITEM));
        }
        return list;
    }

    public List<Champion_Entity> getChampionRunes(int championId){
        List<Champion_Entity> list = new ArrayList<>();
        RelationshipByChampion<ChampEntityBasicInfo> relation = new RelationshipByChampion<ChampEntityBasicInfo>(championId, new ChampEntityBasicInfo(0,0,0));
        if (!bTreeChampRunePageByChamp.contains(relation))
            return list;
        List<ChampEntityBasicInfo> champRunes = bTreeChampRunePageByChamp.getValue(relation).getList();

        for(ChampEntityBasicInfo basicInfo : champRunes){
            list.add((Champion_Entity)files.getObj(basicInfo.getID(), BinaryFiles.CHAMPION_RUNE_PAGE));
        }
        return list;
    }

    public List<Champion_SummonerSpell> getChampionSummonerSpells(int championId){
        List<Champion_SummonerSpell> list = new ArrayList<>();
        RelationshipByChampion<ChampSummonerSpellBasicInfo> relation = new RelationshipByChampion<ChampSummonerSpellBasicInfo>(championId, new ChampSummonerSpellBasicInfo(0,0,0,0));
        if (!bTreeChampSummonerSpellByChamp.contains(relation))
            return list;
        List<ChampSummonerSpellBasicInfo> champSummonerSpells = bTreeChampSummonerSpellByChamp.getValue(relation).getList();

        for(ChampSummonerSpellBasicInfo basicInfo : champSummonerSpells){
            list.add((Champion_SummonerSpell)files.getObj(basicInfo.getID(), BinaryFiles.CHAMPION_SUMMONER_SPELL));
        }
        return list;
    }



}
