package GUI.info_panel;

import GUI.info_panel.champion_panel.ChampionPanel;
import GUI.info_panel.champion_search_panel.ChampionSearchPanel;
import controller.Controller;
import model.database.data_structure.TrieExtended;
import model.database.data_structure.TrieMapExtended;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by filip on 06/11/2016.
 */
public class InfoPanel extends JPanel{

    private ChampionPanel championPanel = new ChampionPanel();
    private ChampionSearchPanel championSearchPanel = new ChampionSearchPanel();
    private ItemPanel itemPanel = new ItemPanel();
    private RunePanel runePanel = new RunePanel();
    private MasteryPanel masteryPanel = new MasteryPanel();

    public InfoPanel(){
        setLayout(new BorderLayout());

        add(championSearchPanel, BorderLayout.CENTER);

    }

    public void setController(Controller controller){
        championSearchPanel.setController(controller);
    }


    public void addComponent(int index){
        removeAll();
        switch (index){
            case 0:
                add(championSearchPanel, BorderLayout.CENTER);
                break;
            case 1:
                add(championPanel, BorderLayout.CENTER);
                break;
            case 2:
                add(itemPanel, BorderLayout.CENTER);
                break;
            case 3:
                add(runePanel, BorderLayout.CENTER);
                break;
            case 4:
                add(masteryPanel, BorderLayout.CENTER);
                break;
            default:
                removeAll();
                add(championSearchPanel, BorderLayout.CENTER);
        }
        validate();
        repaint();
    }
}
