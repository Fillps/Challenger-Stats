package GUI.info_panel.champion_search_panel;

import GUI.ButtonIntegerListener;
import GUI.WrapLayout;
import controller.Controller;
import model.database.files.Path;
import model.database.stats_structure.entity.Champion;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by filip on 06/11/2016.
 */
public class ChampionButtonsPanel extends JPanel {

    private Controller controller;
    private List<ChampionButton> buttonList = new ArrayList<ChampionButton>();

    public ChampionButtonsPanel(ButtonIntegerListener buttonIntegerListener, Controller controller) {

        this.controller = controller;
        setLayout(new WrapLayout());
        //setBackground(new Color(238, 229, 113));

        List<Integer> list = controller.getAllChampionsIds();
        for(Integer c : list){
            Champion champion = controller.getChampion(c);
            Image image = new ImageIcon(Path.CHAMPION_ICON +champion.getChampionData().getImage().getFull()).getImage();
            ImageIcon icon = new ImageIcon(image.getScaledInstance(78, 78,  java.awt.Image.SCALE_SMOOTH ));
            try {
                buttonList.add(new ChampionButton(champion.getChampionData().getName(), icon, champion.getID(), buttonIntegerListener));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //setLayout(new GridBagLayout());
        setLayout(new WrapLayout());

    }

    public void addButton(int id){
        add(buttonList.get(id));
    }

    public void removeAllButtons(){
        removeAll();
        validate();
        repaint();
    }

    public void addAllButtons(){
        removeAllButtons();
        for(int i = 0; i < buttonList.size() ; i++){
            addButton(i);
        }
        validate();
    }


    public void setController(Controller controller) {
        this.controller = controller;
    }
}
