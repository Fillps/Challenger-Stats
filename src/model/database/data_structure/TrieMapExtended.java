package model.database.data_structure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by filip on 06/11/2016.
 */
public class TrieMapExtended<K extends CharSequence, V> extends TrieMap{

    public List<V> getListValuesOnSubTree(K seq){
        List<K> list_K = trie.getListSubTree(seq);
        List<V> list_V = new ArrayList<V>();
        for (K key : list_K){
            V value = (V) get(key);
            list_V.add(value);
        }
        return list_V;
    }
}
