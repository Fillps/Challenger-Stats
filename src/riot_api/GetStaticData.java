package riot_api;

import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.static_data.constant.*;
import net.rithms.riot.api.endpoints.static_data.dto.*;
import net.rithms.riot.constant.Region;

/**
 * Created by filip on 17/10/2016.
 *
 */
public class GetStaticData {

    public static StaticData getStaticData(RiotApi api) throws RiotApiException {
        ChampionList championList = api.getDataChampionList(Region.NA, null, null, true, ChampData.ALL);
        ItemList itemList = api.getDataItemList(Region.NA, null, null, ItemListData.ALL);
        RuneList runeList = api.getDataRuneList(Region.NA, null, null, RuneListData.ALL);
        MasteryList masteryList = api.getDataMasteryList(Region.NA, null, null, MasteryListData.ALL);
        SummonerSpellList summonerSpellList = api.getDataSummonerSpellList(Region.NA, null, null, true, SpellData.ALL);
        return new StaticData(championList, itemList, runeList, masteryList, summonerSpellList);
    }
}


