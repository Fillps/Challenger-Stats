package model.database.data_structure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by filip on 05/11/2016.
 */
public class TrieExtended<C extends CharSequence> extends Trie {

    private static final long serialVersionUID = -2241453108817231142L;

    public TrieExtended(){ super(); }
    public TrieExtended(INodeCreator creator){
        super(creator);
    }

    public List<String> getListSubTree(C seq){

        List<String> list = new ArrayList<String>();
        if (getNode(seq)==null)
            return list;

        Node node = getNode(seq);
        if (node.isWord)
            list.add(seq.toString());
        for(int i=0; i < node.getChildrenSize(); i++){
            list.addAll(findWords(node.getChild(i), seq.toString()));
        }
        return list;
    }

    public List<String> findWords(Node node, String seq){
        List<String> list = new ArrayList<String>();
        seq+=node.character;
        if (node.isWord)
            list.add(seq);
        for(int i=0; i < node.getChildrenSize(); i++){
            list.addAll(findWords(node.getChild(i), seq));
        }
        return list;

    }

}
