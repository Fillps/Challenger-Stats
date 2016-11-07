package GUI.info_panel.champion_search_panel;

import model.database.files.WriterAndReader;
import model.riot_api.StaticData;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;

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


    private List<ChampionButton> buttonList = new ArrayList<ChampionButton>();
    private GridBagConstraints gc = new GridBagConstraints();

    public ChampionButtonsPanel() {

        setMaximumSize(new Dimension(500, 400));
        setBackground(Color.LIGHT_GRAY);
        StaticData staticData = WriterAndReader.read("arquivos/StaticData.ser");
        List<net.rithms.riot.api.endpoints.static_data.dto.Champion> list;
        list = new ArrayList<Champion>(staticData.getChampionList().getData().values());

        int i = 0;
        for(net.rithms.riot.api.endpoints.static_data.dto.Champion c : list){
            Image image = new ImageIcon("resources/images/champion/" + c.getImage().getFull()).getImage();
            ImageIcon icon = new ImageIcon(image.getScaledInstance(78, 78,  java.awt.Image.SCALE_SMOOTH ));
            try {
                buttonList.add(new ChampionButton(c.getName(), icon, i));
            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
        }
        setLayout(new GridBagLayout());

        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridy = 0;
        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.NORTHWEST;

    }

    public void addButton(int id){
        gc.insets = new Insets(2, 2, 2, 2);
        add(buttonList.get(id), gc);

        if (getWidth() > gc.gridx * 85 && gc.gridx < 10){
            gc.gridx++;
            //gc.anchor = GridBagConstraints.LINE_START;
        }
        else{
            gc.gridy++;
            gc.gridx = 0;
            //gc.anchor = GridBagConstraints.LINE_END;
        }
    }

    public void removeAllButtons(){
        removeAll();
        gc.gridy = 0;
        gc.gridx = 0;
        //gc.anchor = GridBagConstraints.FIRST_LINE_END;
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

}
