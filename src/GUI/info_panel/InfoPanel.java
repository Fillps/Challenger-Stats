package GUI.info_panel;

import GUI.ButtonIntegerListener;
import GUI.info_panel.champion_panel.ChampionPanel;
import GUI.info_panel.champion_search_panel.ChampionSearchPanel;
import GUI.info_panel.champions_stats_panel.ChampionStatsPanel;
import controller.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * Created by filip on 06/11/2016.
 */
public class InfoPanel extends JPanel{

    private ChampionStatsPanel championStatsPanel;
    private ChampionSearchPanel championSearchPanel;
    private ItemStatsPanel itemStatsPanel = new ItemStatsPanel();
    private RuneStatsPanel runeStatsPanel = new RuneStatsPanel();
    private MasterysStatsPanel masterysStatsPanel = new MasterysStatsPanel();

    public InfoPanel(Controller controller){
        setLayout(new BorderLayout());

        ButtonIntegerListener buttonIntegerListener = new ButtonIntegerListener() {
            @Override
            public void ButtonIntegerEvent(int buttonIndex) {
                removeAll();
                add(new ChampionPanel(buttonIndex, controller), BorderLayout.CENTER);
                validate();
                repaint();
            }
        };
        championSearchPanel = new ChampionSearchPanel(buttonIntegerListener, controller);
        championStatsPanel = new ChampionStatsPanel(buttonIntegerListener, controller);

        add(championSearchPanel, BorderLayout.CENTER);

    }



    public void addComponent(int index){
        removeAll();
        switch (index){
            case 0:
                add(championSearchPanel, BorderLayout.CENTER);
                break;
            case 1:
                add(championStatsPanel, BorderLayout.CENTER);
                break;
            case 2:
                add(itemStatsPanel, BorderLayout.CENTER);
                break;
            case 3:
                add(runeStatsPanel, BorderLayout.CENTER);
                break;
            case 4:
                add(masterysStatsPanel, BorderLayout.CENTER);
                break;
            default:
                removeAll();
                add(championSearchPanel, BorderLayout.CENTER);
        }
        validate();
        repaint();
    }
}
