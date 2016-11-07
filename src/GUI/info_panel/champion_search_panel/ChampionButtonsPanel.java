package GUI.info_panel.champion_search_panel;

import GUI.ButtonIntegerListener;
import GUI.WrapLayout;
import controller.Controller;
import model.database.files.WriterAndReader;
import model.database.stats_structure.entity.Champion;
import model.riot_api.StaticData;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
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
        setBackground(Color.LIGHT_GRAY);

        List<Champion> list = controller.getAllChampions();
        for(Champion c : list){
            Image image = new ImageIcon("resources/images/champion/" + c.getChampionData().getImage().getFull()).getImage();
            ImageIcon icon = new ImageIcon(image.getScaledInstance(78, 78,  java.awt.Image.SCALE_SMOOTH ));
            try {
                buttonList.add(new ChampionButton(c.getChampionData().getName(), icon, c.getID(), buttonIntegerListener));
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
